package com.example.android.cityfood;

/**
 * Created by AYAR on 25/04/2016.
 */
public class ItemOrden {

    String mDetallePlato;
    int mCantidad;
    double mSubTotal;

    /**
     * Constructor de item vacio
     */
    public ItemOrden() {
        this.mDetallePlato="";
        this.mCantidad = 0;
        this.mSubTotal = 0;
    }

    /**
     * Constructor de item
     *
     * @param plato
     */
    public ItemOrden(Plato plato) {
        this.mDetallePlato=plato.generarDetallePlato();
        this.mCantidad = 1;
        this.mSubTotal = plato.getmPrecioUnit();
    }

    /**
     * Getters
     * @return
     */
    public int getmCantidad() {
        return mCantidad;
    }

    public double getmSubTotal() {
        return mSubTotal;
    }

    public String getmDetallePlato() {
        return mDetallePlato;
    }

    /**
     * Incrementa la cantidad y subtotal del item
     */
    public void masPlato(Plato plato) {
        mCantidad++;
        mSubTotal += plato.getmPrecioUnit();
    }

    /**
     * Reduce la cantidad y subtotal del item
     */
    public void menosPlato(Plato plato) {
        mCantidad--;
        mSubTotal -= plato.getmPrecioUnit();
    }

    public String mostrarItem(){
        return (mDetallePlato + " " + mCantidad + " " + mSubTotal);
    }

}
