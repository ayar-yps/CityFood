package com.example.android.cityfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class OrdenActivity extends AppCompatActivity {

    String[] mResumenOrden;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orden);

        mResumenOrden = getIntent().getStringArrayExtra("ResumenOrden");

        TextView detallePlatos = (TextView) findViewById(R.id.detalle_platos);
        TextView detalleCant = (TextView) findViewById(R.id.detalle_cant);
        TextView detallePrecios = (TextView) findViewById(R.id.detalle_precios);
        TextView cantTotal = (TextView) findViewById(R.id.cant_total);
        TextView precioTotal = (TextView) findViewById(R.id.precio_total);

        detallePlatos.setText(mResumenOrden[0]);
        detalleCant.setText(mResumenOrden[1]);
        detallePrecios.setText(mResumenOrden[2]);
        cantTotal.setText(mResumenOrden[3]);
        precioTotal.setText(mResumenOrden[4]);

    }

    public void confirmarOrden(View v) {

        Intent intent = new Intent(this, ConfirmacionActivity.class);
        intent.putExtra("ResumenOrden", mResumenOrden);
        startActivity(intent);

    }
}


