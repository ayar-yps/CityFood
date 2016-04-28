package com.example.android.cityfood;

import android.util.Log;
import android.widget.TextView;

/**
 * Created by AYAR on 25/04/2016.
 */
public class Orden {

    private int mNroItems;
    private ItemOrden[] mItems;
    private double mPrecioTotal;
    private int mCantTotal;

    /**
     * Cosntructor de orden
     *
     */
    public Orden() {
        this.mNroItems = 0;
        this.mItems = new ItemOrden[20];
        this.mPrecioTotal = 0;
        this.mCantTotal=0;
    }

    /**
     * Getters
     * @return
     */
    public double getmPrecioTotal() {
        return mPrecioTotal;
    }

    public int getmNroItems() {
        return mNroItems;
    }

    /**
     * Incluir plato en la orden
     *
     * @param plato
     */
    public void incluirPlato(PlatoCopacabana plato) {

        boolean platoEnOrden = false;
        String detallePlato = plato.generarDetallePlato();

        for (int i = 0; i < mNroItems; i++) {

            if (detallePlato.equals((mItems[i].getmDetallePlato()))) {
                platoEnOrden = true;
                mItems[i].masPlato(plato);
                mPrecioTotal += plato.getmPrecioUnit();
                mCantTotal++;

                break;
            }
        }
        if (!platoEnOrden) {
            mItems[mNroItems] = new ItemOrden(plato);
            mPrecioTotal += plato.getmPrecioUnit();
            mCantTotal++;
            mNroItems++;
        }
    }

    /**
     * Quitar plato de la Orden
     *
     * @param plato
     */
    public void quitarPlato(PlatoCopacabana plato) {

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
                    mItems[mNroItems] = new ItemOrden();
                    mNroItems--;
                }
                break;
            }
        }
    }

    /**
     * Ver precio total de la orden
     */
    public void verTotal(TextView textTotal) {
        textTotal.setText("Bs " + mPrecioTotal);

        //Ver orden en log
        for (int i = 0; i < mNroItems; i++) {
            Log.i("Orden", mItems[i].mostrarItem());
        }
        Log.i("Total", String.valueOf(mPrecioTotal));
    }

    /**
     * Genera resumen de la orden
     * @return
     */
    public String[] generaResumen() {

        String[] resumen=new String[5];

        resumen[0]="";
        resumen[1]="";
        resumen[2]="";
        resumen[3]=String.valueOf(mCantTotal);
        resumen[4]=String.valueOf(mPrecioTotal);

        if(mNroItems!=0){
            for (int i = 0; i < mNroItems-1; i++) {
                resumen[0]+=mItems[i].getmDetallePlato()+"\n";
                resumen[1]+=String.valueOf(mItems[i].getmCantidad())+"\n";
                resumen[2]+=String.valueOf(mItems[i].getmSubTotal())+"\n";
            }

            resumen[0]+=mItems[mNroItems-1].getmDetallePlato();
            resumen[1]+=String.valueOf(mItems[mNroItems-1].getmCantidad());
            resumen[2]+=String.valueOf(mItems[mNroItems-1].getmSubTotal());
        }

        return(resumen);

    }

}
