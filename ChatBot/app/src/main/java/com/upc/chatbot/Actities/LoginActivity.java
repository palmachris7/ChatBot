package com.upc.chatbot.Actities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.upc.chatbot.API.InterfaceAPI;
import com.upc.chatbot.API.RetrofitClientInstance;
import com.upc.chatbot.R;
import com.upc.chatbot.models.UserLoginRequest;
import com.upc.chatbot.models.UserLoginResponse;
import com.upc.chatbot.models.UserRegisterResponse;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity{

    private EditText Username;
    private EditText Password;
    private Button button;
    public static final String REGEX_EMAIL="^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$";

    Pattern patronEmail = Pattern.compile(REGEX_EMAIL);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Para cambiar el estado
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
        {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_login);


        Username=(EditText) findViewById(R.id.textInputUserLogin);
        Password=(EditText)findViewById(R.id.textInputPasswordLogin);

        button=(Button) findViewById(R.id.cirLoginButton);
        button.setOnClickListener(new View.OnClickListener() {
          @Override
            public void onClick(View v) {
                if(userLogin(v)==true)
                {
                    login();
                }
            }
        });

    }
    public void login()
    {
        UserLoginRequest loginRequest=new UserLoginRequest();
        loginRequest.setUsername(Username.getText().toString());
        loginRequest.setPassword(Password.getText().toString());

        Call<UserLoginResponse> loginResponseCall= RetrofitClientInstance.getUserService().logIn(loginRequest);

        loginResponseCall.enqueue(new Callback<UserLoginResponse>() {
            @Override
            public void onResponse(Call<UserLoginResponse> call, Response<UserLoginResponse> response) {
                if(response.isSuccessful())
                {
                    Toast.makeText(LoginActivity.this,"login correcto",Toast.LENGTH_LONG).show();
                    inciarNavigation();
                }else
                {
                    Toast.makeText(LoginActivity.this,"login fallido",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserLoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this,"Exeption" + t.getLocalizedMessage(),Toast.LENGTH_LONG).show();

            }
        });

    }

    public void inciarNavigation()
    {
        startActivity(new Intent(this, NavigationActivity.class));
        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
    }

    public void onLoginClick(View view)
    {
        startActivity(new Intent(this, RegisterActivity.class));
        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
    }

    private boolean userLogin(View view) {
        String username= Username.getText().toString().trim();
        String password=Password.getText().toString().trim();

        if(username.isEmpty())
        {
            Username.setError("El correo no puede estar vacío");
            return false;

        }
        if(password.isEmpty())
        {
            Password.setError("La contraseña no puede estar vacío");
            return false;
        }
        return true;

    }
}