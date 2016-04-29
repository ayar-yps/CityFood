package com.example.android.cityfood;

import android.widget.CheckBox;
import android.widget.TextView;

/**
 * Created by AYAR on 24/04/2016.
 */
public class PlatoCopacabana extends Plato {

    private String mTipoPlato;
    private double[] mPrecios = new double[2];
    private CheckBox mCheckBoxAgrandarCombo;

    /**
     * Constructor de plato copacabana
     *
     * @param nombre
     * @param precioUnit
     * @param textoPrecio
     * @param tipoPlato
     * @param checkBoxAgrandarCombo
     * @param precios
     */
    public PlatoCopacabana(String nombre, double precioUnit, TextView textoPrecio, String tipoPlato,
                           CheckBox checkBoxAgrandarCombo, double[] precios) {

        super(nombre, precioUnit, textoPrecio);

        this.mTipoPlato = tipoPlato;
        this.mCheckBoxAgrandarCombo = checkBoxAgrandarCombo;
        this.mPrecios = precios;

    }

    /**
     * Cambia el tipo de plato, bloquea/debloquea checkbox
     *
     * @param opcion
     */
    public void cambiarTipoPlato(int opcion) {
        switch (opcion) {
            case 0:
                mTipoPlato = "Combo Normal";
                mPrecioUnit = mPrecios[0];
                mCheckBoxAgrandarCombo.setEnabled(true);
                break;
            case 1:
                mTipoPlato = "Solo pollo y papas";
                mPrecioUnit = mPrecios[1];
                mCheckBoxAgrandarCombo.setChecked(false);
                mCheckBoxAgrandarCombo.setEnabled(false);
                break;
            case 2:
                mTipoPlato = "Solo pollo";
                mPrecioUnit = mPrecios[2];
                mCheckBoxAgrandarCombo.setChecked(false);
                mCheckBoxAgrandarCombo.setEnabled(false);
                break;
            default:
                break;
        }
        this.verPrecio();
    }

    /**
     * Getters
     * @return
     */
    public String getmTipoPlato() {
        return mTipoPlato;
    }

    public CheckBox getmCheckBoxAgrandarCombo() {
        return mCheckBoxAgrandarCombo;
    }

    /**
     * Agranda/reduce combo
     */
    public void cambiarTamañoCombo() {
        if (mCheckBoxAgrandarCombo.isChecked()) {
            mTipoPlato = "Combo Grande";
            mPrecioUnit += 5;
        } else {
            mTipoPlato = "Combo Normal";
            mPrecioUnit -= 5;
        }
        this.verPrecio();
    }

    /**
     * Genera detalle del plato
     * @return
     */
    @Override
    public String generarDetallePlato() {

        String detallePlato = mNombre + " (" + mTipoPlato + ")";
        return detallePlato;
    }

}
