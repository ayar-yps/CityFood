package com.example.android.cityfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class PollosCopacabanaActivity extends FragmentActivity {

    private PlatoCopacabana[] mPlatos = new PlatoCopacabana[3];
    private Orden mOrden = new Orden();
    private double[] preciosAntojito = new double[3];
    private OrdenFragment mOrdenFragment;
    private TextView mTextOrden;

    /**
     * Crea PollosCopacabanaActivity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pollos_copacabana);

        //Define fragment manager y recupera mOrdenFragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        mOrdenFragment = (OrdenFragment) fragmentManager.findFragmentByTag("orden");

        //Si no hay ningun mOrdenFragment con el tag "orden", lo crea
        //si existe, se guarda su orden en mOrden
        if (mOrdenFragment == null) {
            mOrdenFragment = new OrdenFragment();
            fragmentManager.beginTransaction().add(mOrdenFragment, "orden").commit();
            mOrdenFragment.setOrden(mOrden);
        }else{
            mOrden= mOrdenFragment.getOrden();
        }

        //Definir precios base de platos copacabana
        preciosAntojito[0] = 19.5;
        preciosAntojito[1] = 16.5;
        preciosAntojito[2] = 13.5;

        //Fefinicion de platos copacabana
        mPlatos[0] = new PlatoCopacabana("Antojito", 19.5,
                (TextView) findViewById(R.id.precio_combo_antojito), "Combo Normal",
                (CheckBox) findViewById(R.id.agrandar_combo_a),
                preciosAntojito);

        //Seleccionar el primer radio button de cada radio group
        RadioGroup rgAntojito = (RadioGroup) findViewById(R.id.opciones_antojito);
        ((RadioButton) rgAntojito.getChildAt(0)).setChecked(true);

        //Mostrar precios de platos en cards
        mPlatos[0].verPrecio();

        //Definir texview para el total de la orden y mostrar su valor
        mTextOrden = (TextView) findViewById(R.id.total_orden);
        mOrden.verTotal(mTextOrden);
    }

    /**
     * Guarda orden en mOrdenFragment antes de destruir PollosCopacabanaActivity
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mOrdenFragment.setOrden(mOrden);
    }

    /**
     * Cambia el tipo de cualquier plato al pulsar los radio buttons
     *
     * @param v
     */
    public void cambiarTipoPlato(View v) {
        int plato = Integer.parseInt(v.getTag().toString().split(",")[0]);
        int opcion = Integer.parseInt(v.getTag().toString().split(",")[1]);

        mPlatos[plato].cambiarTipoPlato(opcion);
    }

    /**
     * Cambia el tamaño de cualquier combo al pulsar los checkbox
     *
     * @param v
     */
    public void cambiarTamañoCombo(View v) {
        int plato = Integer.parseInt(v.getTag().toString());
        mPlatos[plato].cambiarTamañoCombo();
    }

    /**
     * Incluye plato al pulsar el boton +
     *
     * @param v
     */
    public void incluirPlato(View v) {
        int plato = Integer.parseInt(v.getTag().toString());
        mOrden.incluirPlato(mPlatos[plato]);
        mOrden.verTotal(mTextOrden);

    }

    /**
     * Quita plato al pulsar el boton -
     *
     * @param v
     */
    public void quitarPlato(View v) {
        int plato = Integer.parseInt(v.getTag().toString());
        double auxTotal=mOrden.getmPrecioTotal();

        mOrden.quitarPlato(mPlatos[plato]);

        if(auxTotal==mOrden.getmPrecioTotal()){
            Toast.makeText(this,"Ese plato no esta en la orden",Toast.LENGTH_SHORT).show();
        }else{
            mOrden.verTotal(mTextOrden);
        }
    }

    /**
     * Abre OrdenActivity si la orden tiene items
     *
     * @param v
     */
    public void verOrden(View v) {
        if (mOrden.getmNroItems()==0){
            Toast.makeText(this,"Ups! La orden esta vacia.",Toast.LENGTH_SHORT).show();
        }else{
            Intent intent = new Intent(this, OrdenActivity.class);
            intent.putExtra("ResumenOrden", mOrden.generaResumen());
            startActivity(intent);
        }
    }

}





