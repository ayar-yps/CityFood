package com.example.android.cityfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class PedidoActivity extends AppCompatActivity {

    private String[] mDetallePedido;

    /**
     * Crea PedidoActivity, capta el detalle del pedido y lo muestra
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orden);

        mDetallePedido = getIntent().getStringArrayExtra("DetallePedido");

        TextView detallePlatos = (TextView) findViewById(R.id.detalle_platos);
        TextView detalleCant = (TextView) findViewById(R.id.detalle_cant);
        TextView detallePrecios = (TextView) findViewById(R.id.detalle_precios);
        TextView cantTotal = (TextView) findViewById(R.id.cant_total);
        TextView precioTotal = (TextView) findViewById(R.id.precio_total);

        detallePlatos.setText(mDetallePedido[0]);
        detalleCant.setText(mDetallePedido[1]);
        detallePrecios.setText(mDetallePedido[2]);
        cantTotal.setText(mDetallePedido[3]);
        precioTotal.setText(mDetallePedido[4]);

    }

    /**
     * Dirige a confirmación de pedido
     *
     * @param v
     */
    public void confirmarPedido(View v) {

        Intent intent = new Intent(this, ConfirmacionActivity.class);
        intent.putExtra("DetallePedido", mDetallePedido);
        startActivity(intent);

    }
}


