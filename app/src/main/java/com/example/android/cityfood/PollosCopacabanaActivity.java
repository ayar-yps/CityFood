package com.example.android.cityfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class PollosCopacabanaActivity extends AppCompatActivity {

    private Pedido mPedido = new Pedido();
    private PedidoFragment mPedidoFragment;
    private TextView mTextTotalPedido;

    private PlatoCopacabana[] mPlatos = new PlatoCopacabana[3];

    private double[] preciosAntojito = new double[3];
    private double[] preciosFiesta = new double[3];
    private double[] preciosTrio = new double[3];


    /**
     * Crea PollosCopacabanaActivity
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pollos_copacabana);

        //Define fragment manager y recupera mPedidoFragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        mPedidoFragment = (PedidoFragment) fragmentManager.findFragmentByTag("pedido");

        //Si no hay ningun mPedidoFragment con el tag "pedido", lo crea
        //si existe, se guarda su orden en mPedido
        if (mPedidoFragment == null) {
            mPedidoFragment = new PedidoFragment();
            fragmentManager.beginTransaction().add(mPedidoFragment, "pedido").commit();
            mPedidoFragment.setPedido(mPedido);
        } else {
            mPedido = mPedidoFragment.getPedido();
        }

        //Definir precios base de platos copacabana
        preciosAntojito[0] = 19.5;
        preciosAntojito[1] = 16.5;
        preciosAntojito[2] = 13.5;

        preciosFiesta[0] = 27.0;
        preciosFiesta[1] = 23.5;
        preciosFiesta[2] = 20.0;

        preciosTrio[0] = 38.5;
        preciosTrio[1] = 33.0;
        preciosTrio[2] = 29.0;

        //Definicion de platos copacabana
        mPlatos[0] = new PlatoCopacabana("Antojito", 19.5,
                (TextView) findViewById(R.id.precio_combo_antojito), "Combo Normal",
                (CheckBox) findViewById(R.id.agrandar_antojito),
                preciosAntojito);
        mPlatos[1] = new PlatoCopacabana("Fiesta", 27.0,
                (TextView) findViewById(R.id.precio_combo_fiesta), "Combo Normal",
                (CheckBox) findViewById(R.id.agrandar_fiesta),
                preciosFiesta);
        mPlatos[2] = new PlatoCopacabana("Trio", 38.5,
                (TextView) findViewById(R.id.precio_combo_trio), "Combo Normal",
                (CheckBox) findViewById(R.id.agrandar_trio),
                preciosTrio);

        //Seleccionar el primer radio button de cada radio group
        RadioGroup rgAntojito = (RadioGroup) findViewById(R.id.opciones_antojito);
        RadioGroup rgFiesta = (RadioGroup) findViewById(R.id.opciones_fiesta);
        RadioGroup rgTrio = (RadioGroup) findViewById(R.id.opciones_trio);

        ((RadioButton) rgAntojito.getChildAt(0)).setChecked(true);
        ((RadioButton) rgFiesta.getChildAt(0)).setChecked(true);
        ((RadioButton) rgTrio.getChildAt(0)).setChecked(true);

        //Mostrar precios de platos en cards
        verPrecios();

        //Definir texview para el total del pedido y mostrar su valor
        mTextTotalPedido = (TextView) findViewById(R.id.total_orden);
        mPedido.verTotal(mTextTotalPedido);
    }

    /**
     * Guarda orden en mPedidoFragment antes de destruir PollosCopacabanaActivity
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPedidoFragment.setPedido(mPedido);
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
        mPedido.incluirPlato(mPlatos[plato]);
        mPedido.verTotal(mTextTotalPedido);
    }

    /**
     * Quita plato al pulsar el boton -
     *
     * @param v
     */
    public void quitarPlato(View v) {
        int plato = Integer.parseInt(v.getTag().toString());
        double auxTotal = mPedido.getmPrecioTotal();

        mPedido.quitarPlato(mPlatos[plato]);

        if (auxTotal == mPedido.getmPrecioTotal()) {
            Toast.makeText(this, "Ese plato no esta en la orden", Toast.LENGTH_SHORT).show();
        } else {
            mPedido.verTotal(mTextTotalPedido);
        }
    }

    /**
     * Ver precios de platos en activity
     */
    public void verPrecios() {
        for (int i = 0; i < mPlatos.length; i++) {
            mPlatos[i].verPrecio();
        }
    }

    /**
     * Abre PedidoActivity si el pedido tiene items
     *
     * @param v
     */
    public void verPedido(View v) {
        if (mPedido.getmNroItems() == 0) {
            Toast.makeText(this, "Ups! El pedido esta vacio.", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, PedidoActivity.class);
            intent.putExtra("DetallePedido", mPedido.generarDetalle());
            startActivity(intent);
        }
    }

}





