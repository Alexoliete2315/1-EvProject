package com.example.proy1bueno.addCompra.data;

import com.example.proy1bueno.beans.Compra;

import java.util.ArrayList;

public class DataAddCompra {
    private String message;
    private ArrayList<Compra> lstCompras;


    public String getMessage() {
        return message;
    }
    public ArrayList<Compra> getLstCompras() {
        return lstCompras;
    }
    public void setLstCompras(ArrayList<Compra> lstCompras) {
        this.lstCompras = lstCompras;
    }
}
