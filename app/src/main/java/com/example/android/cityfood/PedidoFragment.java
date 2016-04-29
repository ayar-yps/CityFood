package com.example.android.cityfood;

import android.support.v4.app.Fragment;
import android.os.Bundle;

/**
 * Created by AYAR on 26/04/2016.
 */
public class PedidoFragment extends Fragment {

    private Pedido pedido;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    /**
     * Getter
     * @return
     */
    public Pedido getPedido(){
        return pedido;
    }

    /**
     * Setter
     * @param pedido
     */
    public void setPedido(Pedido pedido){
        this.pedido = pedido;
    }



}
