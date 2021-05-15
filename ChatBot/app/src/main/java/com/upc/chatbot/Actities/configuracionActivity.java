package com.upc.chatbot.Actities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import com.upc.chatbot.R;

public class ConfiguracionActivity extends Activity {
    public TextView txtView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_configuracion);


        //  txtView = (TextView)findViewById(R.id.textView1); // TextView

        // Button1
        final Button btn1 = (Button) findViewById(R.id.boton);
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ShowDialog();
            }
        });

    }




    public void ShowDialog()
    {
        final AlertDialog.Builder popDialog = new AlertDialog.Builder(this);
        final RatingBar rating = new RatingBar(this);
        rating.setMax(6);

        popDialog.setIcon(android.R.drawable.btn_star_big_on);
        popDialog.setTitle("Vote!! ");
        popDialog.setView(rating);

        // Button OK
        popDialog.setPositiveButton(android.R.string.ok,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        txtView.setText(String.valueOf(rating.getProgress()));
                        dialog.dismiss();
                    }

                })

                // Button Cancel
                .setNegativeButton("Cancelar",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        popDialog.create();
        popDialog.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // getMenuInflater().inflate(R.menu., menu);
        return true;
    }



    //Salir sesion
    public void onLoginClick(View view)
    {
        startActivity(new Intent(this, LoginActivity.class));
        overridePendingTransition(R.anim.slide_in_right,R.anim.stay);
    }

}