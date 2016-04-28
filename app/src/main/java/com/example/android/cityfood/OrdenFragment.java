package com.example.android.cityfood;

import android.support.v4.app.Fragment;
import android.os.Bundle;

/**
 * Created by AYAR on 26/04/2016.
 */
public class OrdenFragment extends Fragment {

    private Orden orden;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    /**
     * Getter
     * @return
     */
    public Orden getOrden(){
        return orden;
    }

    /**
     * Setter
     * @param orden
     */
    public void setOrden(Orden orden){
        this.orden = orden;
    }



}
