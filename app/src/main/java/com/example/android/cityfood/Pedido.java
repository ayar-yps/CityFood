package com.example.android.cityfood;

import android.util.Log;
import android.widget.TextView;

/**
 * Created by AYAR on 25/04/2016.
 */
public class Pedido {

    private int mNroItems;
    private ItemPedido[] mItems;
    private double mPrecioTotal;
    private int mCantTotal;

    /**
     * Constructor de pedido
     */
    public Pedido() {
        this.mNroItems = 0;
        this.mItems = new ItemPedido[20];
        this.mPrecioTotal = 0;
        this.mCantTotal = 0;
    }

    /**
     * Getters
     *
     * @return
     */
    public double getmPrecioTotal() {
        return mPrecioTotal;
    }

    public int getmNroItems() {
        return mNroItems;
    }

    /**
     * Incluir plato en el pedido
     *
     * @param plato
     */
    public void incluirPlato(Plato plato) {

        boolean platoEnPedido = false;
        String detallePlato = plato.generarDetallePlato();

        for (int i = 0; i < mNroItems; i++) {

            if (detallePlato.equals((mItems[i].getmDetallePlato()))) {
                platoEnPedido = true;
                mItems[i].masPlato(plato);
                mPrecioTotal += plato.getmPrecioUnit();
                mCantTotal++;

                break;
            }
        }
        if (!platoEnPedido) {
            mItems[mNroItems] = new ItemPedido(plato);
            mPrecioTotal += plato.getmPrecioUnit();
            mCantTotal++;
            mNroItems++;
        }
    }

    /**
     * Quitar plato del Pedido
     *
     * @param plato
     */
    public void quitarPlato(Plato plato) {

        String detallePlato = plato.generarDetallePlato();

        for (int i = 0; i < mNroItems; i++) {

            if (detallePlato.equals((mItems[i].getmDetallePlato()))) {

                mItems[i].menosPlato(plato);
                mPrecioTotal -= plato.getmPrecioUnit();
                mCantTotal--;

                if (mItems[i].getmCantidad() == 0) {
                    for (int j = i; j < mNroItems; j++) {
                        mItems[j] = mItems[j + 1];
                    }
                    mItems[mNroItems] = new ItemPedido();
                    mNroItems--;
                }
                break;
            }
        }
    }

    /**
     * Ver precio total del pedido
     */
    public void verTotal(TextView textTotal) {
        textTotal.setText("Bs " + mPrecioTotal);

        //Ver pedido en log
        for (int i = 0; i < mNroItems; i++) {
            Log.i("Pedido", mItems[i].mostrarItem());
        }
        Log.i("Total", String.valueOf(mPrecioTotal));
    }

    /**
     * Genera el detalle del pedido
     *
     * @return
     */
    public String[] generarDetalle() {

        String[] detalle = new String[5];

        detalle[0] = "";
        detalle[1] = "";
        detalle[2] = "";
        detalle[3] = String.valueOf(mCantTotal);
        detalle[4] = String.valueOf(mPrecioTotal);

        if (mNroItems != 0) {
            for (int i = 0; i < mNroItems - 1; i++) {
                detalle[0] += mItems[i].getmDetallePlato() + "\n";
                detalle[1] += String.valueOf(mItems[i].getmCantidad()) + "\n";
                detalle[2] += String.valueOf(mItems[i].getmSubTotal()) + "\n";
            }

            detalle[0] += mItems[mNroItems - 1].getmDetallePlato();
            detalle[1] += String.valueOf(mItems[mNroItems - 1].getmCantidad());
            detalle[2] += String.valueOf(mItems[mNroItems - 1].getmSubTotal());
        }

        return (detalle);
    }

}
