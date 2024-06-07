package com.example.proy1bueno.beans;

public class Valoracion {
    private int idValoracion;
    private int idUser;
    private int idProduct;
    private String nombreProducto;

    private String imagenProducto;
    private float numEstrellas;
    private double promedioValoracion;

    private String resena;

    public String getImagenProducto() {
        return imagenProducto;
    }

    public void setImagenProducto(String imagenProducto) {
        this.imagenProducto = imagenProducto;
    }

    public Valoracion() {
    }

    public Valoracion(int idUser, int idProduct, float numEstrellas) {
        this.idUser = idUser;
        this.idProduct = idProduct;
        this.numEstrellas = numEstrellas;
    }

    public Valoracion(int idProduct, String nombreProducto, double promedioValoracion) {
        this.idProduct = idProduct;
        this.nombreProducto = nombreProducto;
        this.promedioValoracion = promedioValoracion;
    }

    public Valoracion(int idValoracion, int idUser, int idProduct, float numEstrellas, String resena) {
        this.idValoracion = idValoracion;
        this.idUser = idUser;
        this.idProduct = idProduct;
        this.numEstrellas = numEstrellas;
        this.resena = resena;
    }

    public int getIdValoracion() {
        return idValoracion;
    }

    public void setIdValoracion(int idValoracion) {
        this.idValoracion = idValoracion;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public float getNumEstrellas() {
        return numEstrellas;
    }

    public void setNumEstrellas(float numEstrellas) {
        this.numEstrellas = numEstrellas;
    }

    public String getResena() {
        return resena;
    }

    public void setResena(String resena) {
        this.resena = resena;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public double getPromedioValoracion() {
        return promedioValoracion;
    }

    public void setPromedioValoracion(double promedioValoracion) {
        this.promedioValoracion = promedioValoracion;
    }

    @Override
    public String toString() {
        return "Valoracion{" +
                "idUser=" + idUser +
                ", idProduct=" + idProduct +
                ", numEstrellas=" + numEstrellas +
                '}';
    }
}
