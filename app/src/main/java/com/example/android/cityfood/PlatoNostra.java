package com.example.android.cityfood;

import android.widget.CheckBox;
import android.widget.TextView;

/**
 * Created by AYAR on 28/04/2016.
 */
public class PlatoNostra extends Plato {

    private String mTipoPlato;
    private double[] mPrecios = new double[2];
    private CheckBox mCheckBoxIncluirRefresco;

    public PlatoNostra(String nombre, double precioUnit, TextView textoPrecio, String mTipoPlato, double[] mPrecios, CheckBox mCheckBoxIncluirRefresco) {
        super(nombre, precioUnit, textoPrecio);
        this.mTipoPlato = mTipoPlato;
        this.mPrecios = mPrecios;
        this.mCheckBoxIncluirRefresco = mCheckBoxIncluirRefresco;
    }

    /**
     * Cambia el tipo de plato, bloquea/debloquea checkbox
     *
     * @param opcion
     */
    public void cambiarTipoPlato(int opcion) {
        switch (opcion) {
            case 0:
                mTipoPlato = "M.";
                mPrecioUnit = mPrecios[0];
                if (mCheckBoxIncluirRefresco.isChecked()) {
                    mTipoPlato = mTipoPlato + " c/R";
                    mPrecioUnit += 6;
                } else {
                    mTipoPlato = mTipoPlato + " s/R";
                    mPrecioUnit -= 6;
                }
                mCheckBoxIncluirRefresco.setClickable(true);
                break;
            case 1:
                mTipoPlato = "F.";
                mPrecioUnit = mPrecios[1];
                if (mCheckBoxIncluirRefresco.isChecked()) {
                    mTipoPlato = mTipoPlato + " c/R";
                    mPrecioUnit += 6;
                } else {
                    mTipoPlato = mTipoPlato + " s/R";
                    mPrecioUnit -= 6;
                }
                mCheckBoxIncluirRefresco.setClickable(true);
                break;
            case 2:
                mTipoPlato = "S.F. c/R";
                mPrecioUnit = mPrecios[2];
                mCheckBoxIncluirRefresco.setChecked(true);
                mCheckBoxIncluirRefresco.setClickable(false);
                break;
            default:
                break;
        }
        this.verPrecio();
    }

    /**
     * Incluye/No incluye refresco
     */
    public void incluirRefresco() {
        if (mCheckBoxIncluirRefresco.isChecked()) {
            mTipoPlato = mTipoPlato.replace("s/R", "c/R");
            mPrecioUnit += 6;
        } else {
            mTipoPlato = mTipoPlato.replace("c/R", "s/R");
            mPrecioUnit -= 6;
        }
        this.verPrecio();
    }

    /**
     * Genera detalle del plato
     * @return
     */
    @Override
    public String generarDetallePlato() {

        String detallePlato = mNombre + " " + mTipoPlato;
        return detallePlato;
    }

}
