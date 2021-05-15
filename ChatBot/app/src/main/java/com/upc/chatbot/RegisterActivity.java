package com.upc.chatbot;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    //Para el calendario
    EditText t1;
    private int nYearIn1, nMonthIn1,nDayIn1,sYearIn1, sMonthIn1,sDayIn1;
    static final int DATE_ID = 0;
    Calendar C=Calendar.getInstance();

    //Para validaciones
    private EditText Nombres, Apellidos, Nacimiento, Email, usuario, Contraseña, RepetirContraseña,ErrorContrasena;
    private RadioGroup Genero;

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

        Genero= (RadioGroup) findViewById(R.id.radioGenero);

        usuario=(EditText) findViewById(R.id.editTextUser);
        Contraseña=(EditText) findViewById(R.id.editTextPassword);
        RepetirContraseña=(EditText) findViewById(R.id.editTextRepeatPassword);


    }
    public void onRegisterClick(View view)
    {
        startActivity(new Intent(this,LoginActivity.class));
        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
    }

    private void colocar_fecha() {
        t1.setText((nMonthIn1 + 1) + "-" + nDayIn1 + "-" + nYearIn1+" ");
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
    public void RegisterUser(View view)
    {
        if(Nombres.getText().toString().isEmpty())
        {
            Toast.makeText(this,"Campo nombre vacío",Toast.LENGTH_LONG).show();
        }else{
            if(Apellidos.getText().toString().isEmpty())
            {
                Toast.makeText(this,"Campo apellido vacío",Toast.LENGTH_LONG).show();
            }else{
                if(Nacimiento.getText().toString().isEmpty())
                {
                    Toast.makeText(this,"Campo nacimiento vacío",Toast.LENGTH_LONG).show();
                }else{
                    if(Email.getText().toString().isEmpty())
                    {
                        Toast.makeText(this,"Campo email vacío",Toast.LENGTH_LONG).show();
                    }else{
                        if(Genero.getCheckedRadioButtonId()==-1)
                        {
                            Toast.makeText(this,"No se ha seleccionado un genero",Toast.LENGTH_LONG).show();
                        }else {
                            if(usuario.getText().toString().isEmpty())
                            {
                                Toast.makeText(this,"Campo usuario vacío",Toast.LENGTH_LONG).show();
                            }else
                                {
                                    if(Contraseña.getText().toString().isEmpty())
                                    {
                                        Toast.makeText(this,"Campo contraseña vacío",Toast.LENGTH_LONG).show();
                                    }else
                                        {

                                        }
                                }
                            }
                        }
                    }
                }
            }

        Pattern patronNumeros = Pattern.compile(REGEX_NUMEROS);
        Pattern patronLetras = Pattern.compile(REGEX_LETRAS);
        Pattern patronEmail = Pattern.compile(REGEX_EMAIL);
        Pattern patronContrasena = Pattern.compile(REGEX_CONTRASENA);

        if (!patronLetras.matcher(Nombres.getText().toString().trim()).matches())
        {
            //Toast.makeText(this, "El nombre introducido es incorrecto", Toast.LENGTH_SHORT).show();
            Nombres.setError("El nombre introducido es incorrecto");
        }
        if (!patronLetras.matcher(Apellidos.getText().toString().trim()).matches())
        {
            //Toast.makeText(this, "El apellido introducido es incorrecto", Toast.LENGTH_SHORT).show();
            Apellidos.setError("El apellido introducido es incorrecto");
        }
        if (!patronEmail.matcher(Email.getText().toString().trim()).matches())
        {
            //Toast.makeText(this, "El correo introducido es incorrecto", Toast.LENGTH_SHORT).show();
            Email.setError("El correo introducido es incorrecto");
        }

        if (!patronContrasena.matcher(Contraseña.getText().toString().trim()).matches())
        {
            Toast.makeText(this, "La contraseña debe tener al entre 8 y 16 caracteres, al menos un dígito, al menos una minúscula y al menos una mayúscula.", Toast.LENGTH_SHORT).show();
            //ErrorContrasena.setError("La contraseña debe tener al entre 8 y 16 caracteres, al menos un dígito, al menos una minúscula y al menos una mayúscula.");
        }
        if(!Contraseña.getText().toString().equals(RepetirContraseña.getText().toString()))
        {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            //RepetirContraseña.setError("Las contraseñas no coinciden");
        }

        //validarContraseña();
    }

}
