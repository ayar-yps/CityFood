package com.example.android.cityfood;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.Random;

public class ConfirmacionActivity extends AppCompatActivity {

    String[] mResumenPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacion);

        mResumenPedido = getIntent().getStringArrayExtra("ResumenOrden");

    }

    public String generarOrden() {

        String resumenOrden = "";

        resumenOrden = resumenOrden
                + "----------------------------------------------------------------\n"
                + "DATOS FACTURA\n"
                + "----------------------------------------------------------------\n"
                + "Nombre:\t" + ((EditText) findViewById(R.id.nombre_factura)).getText() + "\n"
                + "NIT:\t" + ((EditText) findViewById(R.id.nit_factura)).getText() + "\n"
                + "----------------------------------------------------------------\n"
                + "DATOS ENTREGA\n"
                + "----------------------------------------------------------------\n"
                + "Teléfono:\t\t" + ((EditText) findViewById(R.id.telefono)).getText() + "\n"
                + "Dirección:\t" + ((EditText) findViewById(R.id.calle_o_avenida_domicilio)).getText() + "\n"
                + "Nro.:\t\t" + ((EditText) findViewById(R.id.nro_domicilio)).getText() + "\n"
                + "Zona:\t\t" + ((EditText) findViewById(R.id.zona_domicilio)).getText() + "\n"
                + "Referencia:\t" + ((EditText) findViewById(R.id.referencia_adicional_domicilio)).getText() + "\n"
                + "----------------------------------------------------------------\n"
                + "PEDIDO\n"
                + "----------------------------------------------------------------\n";

        String[] items = mResumenPedido[0].split("\\n");
        String[] cantidades = mResumenPedido[1].split("\\n");

        for (int i = 0; i < items.length; i++) {
            resumenOrden += "- " + cantidades[i] + " " + items[i] + "\n";
        }
        resumenOrden = resumenOrden
                + "----------------------------------------------------------------\n"
                + "Total a pagar: " + "Bs " + mResumenPedido[4] + "\n"
                + "----------------------------------------------------------------\n";

        return resumenOrden;
    }

    public void enviarOrden(View v) {

        Log.i("Orden", generarOrden());

        Random rand = new Random();
        int codPedido = rand.nextInt(999) + 100;

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"ayar.yps@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Pedido " + String.valueOf(codPedido));
        intent.putExtra(Intent.EXTRA_TEXT, generarOrden());

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }


}
