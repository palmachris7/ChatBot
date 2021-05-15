package com.upc.chatbot.Actities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.upc.chatbot.R;

public class NavigationActivity extends AppCompatActivity {
    MeowBottomNavigation meo;

    private static final int ID_CHAT=1;
    private static final int ID_NOTICIAS=2;
    private static final int ID_CUESTIONARIO=3;
    private static final int ID_CONFIGURACION=4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        meo=(MeowBottomNavigation) findViewById(R.id.botton_nav);
        meo.add(new MeowBottomNavigation.Model(1,R.drawable.chat));
        meo.add(new MeowBottomNavigation.Model(2,R.drawable.noticias));
        meo.add(new MeowBottomNavigation.Model(3,R.drawable.cuestionario));
        meo.add(new MeowBottomNavigation.Model(4,R.drawable.configuracion));

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new FragmentChat()).commit();

        meo.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                //Toast.makeText(getApplicationContext(),"Elemento seleccionado"+item.getId(),Toast.LENGTH_SHORT).show();
            }
        });
        meo.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {

            }
        });
        meo.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment_seleccionado=null;
                switch (item.getId())
                {
                    case ID_CHAT:
                        fragment_seleccionado=new FragmentChat();
                        break;
                    case ID_NOTICIAS:
                        fragment_seleccionado=new FragmentNoticias();
                        break;
                    case ID_CUESTIONARIO:
                        fragment_seleccionado=new FragmentCuestionario();
                        break;
                    case ID_CONFIGURACION:
                        fragment_seleccionado=new FragmentConfiguracion();
                        break;

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,fragment_seleccionado).commit();
            }
        });
    }
}