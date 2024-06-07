package beans;

public class Venta {
    private int idVenta;
    private String fechaVenta;
    private String horaVenta;
    private int idVendedor;
    private int idProductoVendido;
    private int idDireccionCliente;

    public Venta() {
    }

    public Venta(int idProductoVendido) {
        this.idProductoVendido = idProductoVendido;
    }

    public Venta(int idVenta, String fechaVenta, String horaVenta, int idVendedor, int idProductoVendido, int idDireccionCliente) {
        this.idVenta = idVenta;
        this.fechaVenta = fechaVenta;
        this.horaVenta = horaVenta;
        this.idVendedor = idVendedor;
        this.idProductoVendido = idProductoVendido;
        this.idDireccionCliente = idDireccionCliente;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public String getHoraVenta() {
        return horaVenta;
    }

    public void setHoraVenta(String horaVenta) {
        this.horaVenta = horaVenta;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public int getIdProductoVendido() {
        return idProductoVendido;
    }

    public void setIdProductoVendido(int idProductoVendido) {
        this.idProductoVendido = idProductoVendido;
    }

    public int getIdDireccionCliente() {
        return idDireccionCliente;
    }

    public void setIdDireccionCliente(int idDireccionCliente) {
        this.idDireccionCliente = idDireccionCliente;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "idVenta=" + idVenta +
                ", fechaVenta='" + fechaVenta + '\'' +
                ", horaVenta='" + horaVenta + '\'' +
                ", idVendedor=" + idVendedor +
                ", idProductoVendido=" + idProductoVendido +
                ", idDireccionCliente=" + idDireccionCliente +
                '}';
    }
}
