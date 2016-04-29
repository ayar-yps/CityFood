package com.example.android.cityfood;

import android.widget.TextView;

/**
 * Created by AYAR on 25/04/2016.
 */
public class Plato {

    protected String mNombre;
    protected double mPrecioUnit;
    protected TextView mTextoPrecio;

    /**
     * Constructor de plato vacio
     */
    public Plato() {
        this.mNombre = "";
        this.mPrecioUnit = 0;
    }

    /**
     * Constructor de plato
     *
     * @param nombre
     * @param precioUnit
     * @param textoPrecio
     */
    public Plato(String nombre, double precioUnit, TextView textoPrecio) {
        this.mNombre = nombre;
        this.mPrecioUnit = precioUnit;
        this.mTextoPrecio = textoPrecio;
    }

    /**
     * Getters
     */
    public double getmPrecioUnit() {
        return mPrecioUnit;
    }

    /**
     * Ver precio del plato en TextView
     */
    public void verPrecio() {
        mTextoPrecio.setText("Bs " + mPrecioUnit);
    }

    /**
     * Genera el detalle del plato
     * @return
     */
    public String generarDetallePlato() {

        String detallePlato = mNombre;
        return detallePlato;
    }

}


