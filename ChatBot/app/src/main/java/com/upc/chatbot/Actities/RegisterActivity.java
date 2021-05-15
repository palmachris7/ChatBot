package com.upc.chatbot.Actities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.upc.chatbot.API.RetrofitClientInstance;
import com.upc.chatbot.R;
import com.upc.chatbot.models.UserRegisterRequest;
import com.upc.chatbot.models.UserRegisterResponse;

import java.util.Calendar;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    //Para el calendario
    EditText t1;
    private int nYearIn1, nMonthIn1,nDayIn1,sYearIn1, sMonthIn1,sDayIn1;
    static final int DATE_ID = 0;
    Calendar C=Calendar.getInstance();

    //Para validaciones
    private EditText Nombres, Apellidos, Nacimiento, Email, usuario, Contraseña, RepetirContraseña,ErrorContrasena;
    private EditText cumpleanos;
    private RadioGroup Genero;
    private int RadioGroupId;
    private Button btnRegistro;
    //EXPRESIONES REGULARES PARA VALIDACIÓN
    public static final String REGEX_NUMEROS = "^[0-9]+$";
    public static final String REGEX_LETRAS="[a-zA-Z ]{2,254}";
    public static final String REGEX_EMAIL="^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$";
    public static final String REGEX_CONTRASENA="^(?=\\w*\\d)(?=\\w*[A-Z])(?=\\w*[a-z])\\S{8,16}$";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        changeStatusBarColor();
        sMonthIn1=C.get(Calendar.MONTH);
        sDayIn1=C.get(Calendar.DAY_OF_MONTH);
        sYearIn1=C.get(Calendar.YEAR);
        t1=(EditText)findViewById(R.id.editTextDate);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DATE_ID);
            }
        });

        Nombres=(EditText) findViewById(R.id.editTextName);
        Apellidos=(EditText) findViewById(R.id.editTextLasName);
        Nacimiento=(EditText) findViewById(R.id.editTextDate);
        Email=(EditText) findViewById(R.id.editTextEmail);
        cumpleanos= (EditText) findViewById(R.id.editTextDate);
        Genero= (RadioGroup) findViewById(R.id.radioGenero);

        usuario=(EditText) findViewById(R.id.editTextUser);
        Contraseña=(EditText) findViewById(R.id.editTextPassword);
        RepetirContraseña=(EditText) findViewById(R.id.editTextRepeatPassword);

        btnRegistro=(Button) findViewById(R.id.cirRegisterButton);

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(RegisterUser(v)==true)
                {
                    saveUser(createRequest());
                    Nombres.setText("");
                    Apellidos.setText("");
                    Email.setText("");
                    Nacimiento.setText("");
                    cumpleanos.setText("");
                    usuario.setText("");
                    Contraseña.setText("");
                    RepetirContraseña.setText("");
                }
            }
        });


    }
    public UserRegisterRequest createRequest()
    {
        //Para obtener texto de un radiobutton
        int radioButtonID = Genero.getCheckedRadioButtonId();
        View radioButton = Genero.findViewById(radioButtonID);
        int idx = Genero.indexOfChild(radioButton);
        RadioButton r = (RadioButton) Genero.getChildAt(idx);
        String texto= r.getText().toString();

        UserRegisterRequest userRegisterRequest=new UserRegisterRequest();
        userRegisterRequest.setFirstName(Nombres.getText().toString());
        userRegisterRequest.setLastName(Apellidos.getText().toString());
        userRegisterRequest.setEmail(Email.getText().toString());
        userRegisterRequest.setBirthday(cumpleanos.getText().toString());
        userRegisterRequest.setGender(texto);
        userRegisterRequest.setUsername(usuario.getText().toString());
        userRegisterRequest.setPassword(Contraseña.getText().toString());
        return userRegisterRequest;

    }
    public void saveUser(UserRegisterRequest userRegisterRequest)
    {
        Call<UserRegisterResponse> userRegisterResponseCall= RetrofitClientInstance.getUserService().saveUser(userRegisterRequest);
        userRegisterResponseCall.enqueue(new Callback<UserRegisterResponse>() {
            @Override
            public void onResponse(Call<UserRegisterResponse> call, Response<UserRegisterResponse> response) {
                if(response.isSuccessful())
                {
                    Toast.makeText(RegisterActivity.this,"Guardado correcto",Toast.LENGTH_LONG).show();
                }else
                {
                    Toast.makeText(RegisterActivity.this,"Ha fallado",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserRegisterResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this,"Ha fallado"+t.getLocalizedMessage(),Toast.LENGTH_LONG).show();

            }
        });
    }


    public void onRegisterClick(View view)
    {
        startActivity(new Intent(this, LoginActivity.class));
        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
    }

    private void colocar_fecha() {
        t1.setText(nDayIn1+"/"+(nMonthIn1 + 1)+"/"+nYearIn1);
    }
    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    nYearIn1 = year;
                    nMonthIn1 = monthOfYear;
                    nDayIn1 = dayOfMonth;
                    colocar_fecha();

                }

            };


    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_ID:
                return new DatePickerDialog(this, mDateSetListener, sYearIn1, sMonthIn1, sDayIn1);
        }
        return null;
    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }
    public void onLoginClick(View view){
        startActivity(new Intent(this,LoginActivity.class));
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);

    }

    public void validarContraseña()
    {
            if(!Contraseña.getText().toString().equals(RepetirContraseña.getText().toString()))
            {
                Toast.makeText(this,"Las contraseñas no coinciden",Toast.LENGTH_LONG).show();
            }
    }
    public boolean RegisterUser(View view)
    {
        Pattern patronNumeros = Pattern.compile(REGEX_NUMEROS);
        Pattern patronLetras = Pattern.compile(REGEX_LETRAS);
        Pattern patronEmail = Pattern.compile(REGEX_EMAIL);
        Pattern patronContrasena = Pattern.compile(REGEX_CONTRASENA);
        if(Nombres.getText().toString().isEmpty())
        {
            //Toast.makeText(this,"Campo nombre vacío",Toast.LENGTH_LONG).show();
            Nombres.setError("Campo nombre vacío");
            return false;
        }else{
            if(Apellidos.getText().toString().isEmpty())
            {
                //Toast.makeText(this,"Campo apellido vacío",Toast.LENGTH_LONG).show();
                Apellidos.setError("Campo apellidos vacío");
                return false;
            }else{
                if(Nacimiento.getText().toString().isEmpty())
                {
                    //Toast.makeText(this,"Campo nacimiento vacío",Toast.LENGTH_LONG).show();
                    Nombres.setError("No se ha seleccionado nacimiento");
                    return false;
                }else{
                    if(Email.getText().toString().isEmpty())
                    {
                        //Toast.makeText(this,"Campo email vacío",Toast.LENGTH_LONG).show();
                        Email.setError("Campo email vacío");
                        return false;
                    }else{
                        if(Genero.getCheckedRadioButtonId()==-1)
                        {
                            Toast.makeText(this,"No se ha seleccionado un genero",Toast.LENGTH_LONG).show();
                            return false;
                        }else {
                            if(usuario.getText().toString().isEmpty())
                            {
                                //Toast.makeText(this,"Campo usuario vacío",Toast.LENGTH_LONG).show();
                                usuario.setError("Campo usuario vacío");
                                return false;
                            }else
                                {
                                    if(Contraseña.getText().toString().isEmpty())
                                    {
                                        //Toast.makeText(this,"Campo contraseña vacío",Toast.LENGTH_LONG).show();
                                        Contraseña.setError("Campo contraseña vacío");
                                        return false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        if (!patronLetras.matcher(Nombres.getText().toString().trim()).matches())
        {
            //Toast.makeText(this, "El nombre introducido es incorrecto", Toast.LENGTH_SHORT).show();
            Nombres.setError("El nombre introducido es incorrecto");
            return false;
        }
        if (!patronLetras.matcher(Apellidos.getText().toString().trim()).matches())
        {
            //Toast.makeText(this, "El apellido introducido es incorrecto", Toast.LENGTH_SHORT).show();
            Apellidos.setError("El apellido introducido es incorrecto");
            return false;
        }
        if (!patronEmail.matcher(Email.getText().toString().trim()).matches())
        {
            //Toast.makeText(this, "El correo introducido es incorrecto", Toast.LENGTH_SHORT).show();
            Email.setError("El correo introducido es incorrecto");
            return false;
        }

        if (!patronContrasena.matcher(Contraseña.getText().toString().trim()).matches())
        {
            Toast.makeText(this, "La contraseña debe tener al entre 8 y 16 caracteres, al menos un dígito, al menos una minúscula y al menos una mayúscula.", Toast.LENGTH_SHORT).show();
            //ErrorContrasena.setError("La contraseña debe tener al entre 8 y 16 caracteres, al menos un dígito, al menos una minúscula y al menos una mayúscula.");
            return false;
        }
        if(!Contraseña.getText().toString().equals(RepetirContraseña.getText().toString()))
        {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            //RepetirContraseña.setError("Las contraseñas no coinciden");
            return false;
        }

        //validarContraseña();
        return true;
    }

}
