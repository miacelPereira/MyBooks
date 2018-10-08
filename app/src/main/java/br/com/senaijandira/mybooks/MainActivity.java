package br.com.senaijandira.mybooks;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    TabLayout tab_menu;

    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tab_menu = findViewById(R.id.tab_menu);

        tab_menu.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition() == 0){
                    openFragment1();
                }
                if(tab.getPosition() == 1){
                    openFragment2();
                }
                if(tab.getPosition() == 2){
                    openFragment3();
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
    public void abrirCadastro(View v){
        startActivity(new Intent(this, CadastroActivity.class));
    }
    public void openFragment1() {

        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.flRetorno, new FragmentTodosLivros());

        ft.commit();
    }

    public void openFragment2() {
        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.flRetorno, new FragmentLivroLidos());

        ft.commit();

    }

    public void openFragment3() {


    }
}
