package beans;


public class Product {
    private int idProducto;
    private String nombreProducto;
    private Double precioProducto;
    private String marcaProducto;

    //NO ES STRING ES DATE PERO FALLA EN EL CONSTRUCTOR DEL ACTION
    private String fechaSubidaProducto;
    private String descripcionProducto;
    private String imagenProducto;
    private int idUser;

    private String vendedor;

    private int idCategoria;
    private String nombreCategoria;
    private float numEstrellas;

    public float getNumEstrellas() {
        return numEstrellas;
    }

    public void setNumEstrellas(float numEstrellas) {
        this.numEstrellas = numEstrellas;
    }

    public Product() {
    }

    // Constructor
    public Product(int idProducto, String nombreProducto, Double precioProducto,
                   String marcaProducto, String fechaSubidaProducto, String descripcionProducto,
                   String imagenProducto, int idUser) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.precioProducto = precioProducto;
        this.marcaProducto = marcaProducto;
        this.fechaSubidaProducto = fechaSubidaProducto;
        this.descripcionProducto = descripcionProducto;
        this.imagenProducto = imagenProducto;
        this.idUser = idUser;
    }
    public Product(int idProducto, String nombreProducto, Double precioProducto,
                   String marcaProducto, String nombreCategoria, String vendedor) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.precioProducto = precioProducto;
        this.marcaProducto = marcaProducto;
        this.nombreCategoria = nombreCategoria;
        this.vendedor = vendedor;
    }


    // Getters y setters
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(Double precioProducto) {
        this.precioProducto = precioProducto;
    }

    public String getMarcaProducto() {
        return marcaProducto;
    }

    public void setMarcaProducto(String marcaProducto) {
        this.marcaProducto = marcaProducto;
    }

    public String getFechaSubidaProducto() {
        return fechaSubidaProducto;
    }

    public void setFechaSubidaProducto(String fechaSubidaProducto) {
        this.fechaSubidaProducto = fechaSubidaProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public String getImagenProducto() {
        return imagenProducto;
    }

    public void setImagenProducto(String imagenProducto) {
        this.imagenProducto = imagenProducto;
    }

    public int getIdUser() {
        return idUser;
    }
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    @Override
    public String toString() {
        return "Product{" +
                "idProducto=" + idProducto +
                ", nombreProducto='" + nombreProducto + '\'' +
                ", precioProducto=" + precioProducto +
                ", marcaProducto='" + marcaProducto + '\'' +
                ", fechaSubidaProducto='" + fechaSubidaProducto + '\'' +
                ", descripcionProducto='" + descripcionProducto + '\'' +
                ", imagenProducto='" + imagenProducto + '\'' +
                ", idUser=" + idUser +
                ", vendedor='" + vendedor + '\'' +
                ", idCategoria=" + idCategoria +
                ", nombreCategoria='" + nombreCategoria + '\'' +
                ", numEstrellas=" + numEstrellas +
                '}';
    }
}
