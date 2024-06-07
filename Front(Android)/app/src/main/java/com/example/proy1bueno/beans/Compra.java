package com.example.proy1bueno.beans;

public class Compra {
    private int idCompra;
    private String fechaCompra;
    private String horaCompra;
    private int idComprador;
    private String comprador;
    private int idVendedor;
    private String vendedor;
    private int idProductoComprado;

    public int getIdProductoComprado() {
        return idProductoComprado;
    }

    public void setIdProductoComprado(int idProductoComprado) {
        this.idProductoComprado = idProductoComprado;
    }

    private String productoComprado;
    private double precioProducto;
    private String marcaProducto;

    private String imagenProducto;

    public String getImagenProducto() {
        return imagenProducto;
    }

    public void setImagenProducto(String imagenProducto) {
        this.imagenProducto = imagenProducto;
    }

    public Compra() {
    }

    public Compra(int idCompra, String fechaCompra, String horaCompra, int idComprador, String comprador, int idVendedor, String vendedor, String productoComprado, double precioProducto, String marcaProducto) {
        this.idCompra = idCompra;
        this.fechaCompra = fechaCompra;
        this.horaCompra = horaCompra;
        this.idComprador = idComprador;
        this.comprador = comprador;
        this.idVendedor = idVendedor;
        this.vendedor = vendedor;
        this.productoComprado = productoComprado;
        this.precioProducto = precioProducto;
        this.marcaProducto = marcaProducto;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public String getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public String getHoraCompra() {
        return horaCompra;
    }

    public void setHoraCompra(String horaCompra) {
        this.horaCompra = horaCompra;
    }

    public int getIdComprador() {
        return idComprador;
    }

    public void setIdComprador(int idComprador) {
        this.idComprador = idComprador;
    }

    public String getComprador() {
        return comprador;
    }

    public void setComprador(String comprador) {
        this.comprador = comprador;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public String getProductoComprado() {
        return productoComprado;
    }

    public void setProductoComprado(String productoComprado) {
        this.productoComprado = productoComprado;
    }

    public double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(double precioProducto) {
        this.precioProducto = precioProducto;
    }

    public String getMarcaProducto() {
        return marcaProducto;
    }

    public void setMarcaProducto(String marcaProducto) {
        this.marcaProducto = marcaProducto;
    }

    @Override
    public String toString() {
        return "Compra{" +
                "idCompra=" + idCompra +
                ", fechaCompra='" + fechaCompra + '\'' +
                ", horaCompra='" + horaCompra + '\'' +
                ", idComprador=" + idComprador +
                ", comprador='" + comprador + '\'' +
                ", idVendedor=" + idVendedor +
                ", vendedor='" + vendedor + '\'' +
                ", productoComprado='" + productoComprado + '\'' +
                ", precioProducto=" + precioProducto +
                ", marcaProducto='" + marcaProducto + '\'' +
                '}';
    }
}
