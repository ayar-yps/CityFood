package com.example.android.cityfood;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class PizzaNostraActivity extends AppCompatActivity {

    private Pedido mPedido = new Pedido();
    private PedidoFragment mPedidoFragment;
    private TextView mTextTotalPedido;

    private PlatoNostra[] mPlatos = new PlatoNostra[3];

    private double[] preciosNostra = new double[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_nostra);

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

        // Definicion de precios
        preciosNostra[0] = 65.0;
        preciosNostra[1] = 90.0;
        preciosNostra[2] = 120.0;

        // Definicion de platos
        mPlatos[0] = new PlatoNostra("P. Nostra", 65.0
                , (TextView) findViewById(R.id.precio_nostra), "M. s/R"
                , preciosNostra, (CheckBox) findViewById(R.id.c_s_r_nostra));
        mPlatos[1] = new PlatoNostra("P. Hawaiana", 65.0
                , (TextView) findViewById(R.id.precio_hawaiana), "M. s/R"
                , preciosNostra, (CheckBox) findViewById(R.id.c_s_r_hawaiana));
        mPlatos[2] = new PlatoNostra("P. Napolitana", 65.0
                , (TextView) findViewById(R.id.precio_napolitana), "M. s/R"
                , preciosNostra, (CheckBox) findViewById(R.id.c_s_r_napolitana));

        //Seleccionar el primer radio button de cada radio group
        RadioGroup rgNostra = (RadioGroup) findViewById(R.id.opciones_nostra);
        RadioGroup rgHawaiana = (RadioGroup) findViewById(R.id.opciones_hawaiana);
        RadioGroup rgNapolitana = (RadioGroup) findViewById(R.id.opciones_napolitana);

        ((RadioButton) rgNostra.getChildAt(0)).setChecked(true);
        ((RadioButton) rgHawaiana.getChildAt(0)).setChecked(true);
        ((RadioButton) rgNapolitana.getChildAt(0)).setChecked(true);

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
     * Incluye o quita refrescos al pulsar los checkbox
     *
     * @param v
     */
    public void incluirRefresco(View v) {
        int plato = Integer.parseInt(v.getTag().toString());
        mPlatos[plato].incluirRefresco();
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
