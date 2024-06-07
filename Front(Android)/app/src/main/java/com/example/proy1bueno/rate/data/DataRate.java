package com.example.proy1bueno.rate.data;

import com.example.proy1bueno.beans.Product;
import com.example.proy1bueno.beans.Valoracion;

import java.util.ArrayList;

public class DataRate {
    private String message;
    private ArrayList<Valoracion> lstValoraciones;


    public String getMessage() {
        return message;
    }
    public ArrayList<Valoracion> getLstValoraciones() {
        return lstValoraciones;
    }
    public void setLstValoraciones(ArrayList<Valoracion> lstValoraciones) {
        this.lstValoraciones = lstValoraciones;
    }
}
