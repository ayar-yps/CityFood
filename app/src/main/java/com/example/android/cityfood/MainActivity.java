package com.example.android.cityfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Menu principal de la aplicación, redirige a apartados de restaurantes
 * @author  Ayar Yuman Paco Sanizo
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Abre activity de Pollos Copacabana
     * @param v
     */
    public void irPollosCopacabana(View v){
        Intent intent = new Intent(this, PollosCopacabanaActivity.class);
        startActivity(intent);
    }

    /**
     * Abre activity de Pizza Nostra
     * @param v
     */
    public void irPizzaNostra(View v){
        Intent intent = new Intent(this, PizzaNostraActivity.class);
        startActivity(intent);
    }

    /**
     * Abre activity de Toby
     * @param v
     */
    public void irToby(View v){
        Intent intent = new Intent(this, TobyActivity.class);
        startActivity(intent);
    }

}
