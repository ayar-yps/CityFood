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
    public Plato(){
        this.mNombre = "";
        this.mPrecioUnit = 0;
    }

    /**
     * Constructor de plato
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
    public String getmNombre() {
        return mNombre;
    }

    public double getmPrecioUnit() {
        return mPrecioUnit;
    }

    public TextView getmTextoPrecio() {
        return mTextoPrecio;
    }

    /**
     * Setters
     */
    public void setmNombre(String mNombre) {
        this.mNombre = mNombre;
    }

    public void setmPrecioUnit(double mPrecioUnit) {
        this.mPrecioUnit = mPrecioUnit;
    }

    public void setmTextoPrecio(TextView mTextoPrecio) {
        this.mTextoPrecio = mTextoPrecio;
    }

    /**
     * Ver precio del plato en card
     */
    public void verPrecio() {
        mTextoPrecio.setText("Bs " + mPrecioUnit);
    }

    public String generarDetallePlato() {

        String detallePlato = mNombre;
        return detallePlato;
    }

}


