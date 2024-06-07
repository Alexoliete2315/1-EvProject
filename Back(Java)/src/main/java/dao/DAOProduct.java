package dao;

import beans.Product;
import beans.User;
import connection.MotorSQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class DAOProduct {

    private MotorSQL motorSQL;

    private final String tableName = "producto";
    private final String SQL_INSERT = "INSERT INTO " + tableName + " (Id_Producto, Nombre_Producto, Precio_Producto, " +
            "Marca_Producto, Fecha_Subida_Producto, Descripcion_Producto, Imagen_Producto, Id_Usuario)  VALUES ";
    private final String SQL_FINDALL = "SELECT * FROM " + tableName + " WHERE 1=1";

    private final String SQL_FILTRO_CATEGORIAS = "SELECT Producto.*, GROUP_CONCAT(Categoria.Nombre_Categoria) AS Categorias, Usuario.Nombre_Usuario\n" +
            "FROM Producto\n" +
            "JOIN Producto_Categoria ON Producto.Id_Producto = Producto_Categoria.Id_Producto\n" +
            "JOIN Categoria ON Producto_Categoria.Id_Categoria = Categoria.Id_Categoria\n" +
            "JOIN Usuario ON Producto.Id_Usuario = Usuario.Id_Usuario\n" +
            "WHERE ";

    private final String SQL_FICHA_TECNICA =
            "SELECT P.Imagen_Producto, P.Nombre_Producto, P.Precio_Producto, " +
            "U.Nombre_Usuario AS Vendedor, P.Marca_Producto, AVG(V.Estrellas) AS Valoracion_Media, " +
            "P.Descripcion_Producto " +
            "FROM Producto AS P " +
            "JOIN Usuario AS U ON P.Id_Usuario = U.Id_Usuario " +
            "LEFT JOIN Valoracion AS V ON P.Id_Producto = V.Id_Producto " +
            "WHERE P.Id_Producto = ";


    public DAOProduct(){
        motorSQL = MotorSQL.getMotorSQL();
    }

    public String add(Product bean){
        String sql = "";
        sql = SQL_INSERT;
        sql += "(" + bean.getIdProducto() + ", '" + bean.getNombreProducto() +
                "', '" + bean.getPrecioProducto() + "', '" + bean.getMarcaProducto() +
                "', '" + bean.getFechaSubidaProducto() + "', '" + bean.getDescripcionProducto() +
                "', '" + bean.getImagenProducto() +  "', " + bean.getIdUser() + ")";
        System.out.println(sql);
        motorSQL.connect();
        motorSQL.executeUpdate(sql);
        motorSQL.close();
        return "";
    }
    public ArrayList<Product> findAll(Product product){
        String sql = "";
        sql = SQL_FINDALL;
        System.out.println(product.getIdUser());
        if (product.getIdUser()>0){
           sql += " AND Id_Usuario = " + product.getIdUser();
        }
        System.out.println(sql);
        ArrayList<Product> lstProducts = new ArrayList<>();
        motorSQL.connect();
        ResultSet resultSet = motorSQL.executeQuery(sql);
        try {
            System.out.println("TRY findall usuario");
            while(resultSet.next()){
                Product productAux = new Product();
                productAux.setIdProducto(resultSet.getInt(1));
//                System.out.println("resultSet.getInt(1)");
//                System.out.println(resultSet.getInt(1));
                productAux.setNombreProducto(resultSet.getString(2));
//                System.out.println("resultSet.getString(2)");
//                System.out.println(resultSet.getString(2));
                productAux.setPrecioProducto((double) resultSet.getInt(3));
//                System.out.println("resultSet.getString(3)");
//                System.out.println(resultSet.getString(3));
                productAux.setMarcaProducto(resultSet.getString(4));
//                System.out.println("resultSet.getString(4)");
//                System.out.println(resultSet.getString(4));
                productAux.setFechaSubidaProducto(resultSet.getString(5));
//                System.out.println("resultSet.getString(5)");
//                System.out.println(resultSet.getString(5));
                productAux.setDescripcionProducto(resultSet.getString(6));
//                System.out.println("resultSet.getString(6)");
//                System.out.println(resultSet.getString(6));
                productAux.setImagenProducto(resultSet.getString(7));
//                System.out.println("resultSet.getString(7)");
//                System.out.println(resultSet.getString(7));
                lstProducts.add(productAux);
                System.out.println(productAux.toString());
//                usuarioAux.setId(resultSet.getInt(1));
//                usuarioAux.setUsername(resultSet.getString(2));
//                usuarioAux.setPassword(resultSet.getString(3));
//                System.out.println("im here 2.0");
//                listUsuario.add(usuarioAux);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        motorSQL.close();
        return lstProducts;
    }

/*
public String toStringCategoria() {
        return "Product{" +
                "idProducto=" + idProducto +
                ", nombreProducto='" + nombreProducto + '\'' +
                ", precioProducto=" + precioProducto +
                ", marcaProducto='" + marcaProducto + '\'' +
                ", idCategoria=" + idCategoria +
                ", nombreCategoria='" + nombreCategoria + '\'' +
                '}';
    }
 */
    public ArrayList<Product>ProductsCategory(Product product){
        ArrayList<Product> lstProducts = new ArrayList<>();
        String sql = "";
        sql = SQL_FILTRO_CATEGORIAS;
//        sql += "Categoria.Nombre_Categoria = '" + product.getNombreCategoria() +
//                "' GROUP BY Producto.Id_Producto;";
        if (product.getNombreCategoria() != null && !product.getNombreCategoria().equals("")){
            String[] categoriesProduct = product.getNombreCategoria().split("\\.");
            sql += "Nombre_Categoria IN (";
            for (int i = 0; i < categoriesProduct.length; i++) {
               sql += "'" + categoriesProduct[i] + "', ";
            }
            sql = sql.substring(0, sql.length()-2);
            sql += ")";

//
            System.out.println("SALGO DEL IF FILTRADO CATEGORIAS\nCON ESTA SQL= " + sql);
        }else{
            sql += "1=1";
        }

        sql += " GROUP BY producto.Id_Producto;";

        System.out.println("SQL EN DAO PRODUCT CATEGORY \n" + sql);
        motorSQL.connect();
        ResultSet resultSet = motorSQL.executeQuery(sql);
        try {
            while(resultSet.next()){
                Product productAux = new Product();
                productAux.setIdProducto(resultSet.getInt(1));
                productAux.setNombreProducto(resultSet.getString(2));
                productAux.setPrecioProducto(resultSet.getDouble(3));
                productAux.setMarcaProducto(resultSet.getString(4));
                productAux.setImagenProducto(resultSet.getString(7));
                productAux.setNombreCategoria(resultSet.getString(9));
                productAux.setVendedor(resultSet.getString(10));
                System.out.println("Estoy en DAOProduct en ProductCategory");
                System.out.println("product = " + productAux.toString());
                lstProducts.add(productAux);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        motorSQL.close();
        System.out.println("SALGO DAO FILTRO CATEGORIA \n" + lstProducts);
        return lstProducts;
    }

public ArrayList<Product> fichaProduct(Product product){
    String sql =SQL_FICHA_TECNICA;
    ArrayList<Product> lstProducts = new ArrayList<>();
    sql +=  product.getIdProducto() ;
    System.out.println("SQL EN DAO PRODUCT FICHA PRODUCT \n" + sql);
    motorSQL.connect();
    ResultSet resultSet = motorSQL.executeQuery(sql);
    try {
        while(resultSet.next()){
            Product productAux = new Product();
            productAux.setImagenProducto(resultSet.getString(1));
            productAux.setNombreProducto(resultSet.getString(2));
            productAux.setPrecioProducto(resultSet.getDouble(3));
            productAux.setVendedor(resultSet.getString(4));
            productAux.setMarcaProducto(resultSet.getString(5));
            productAux.setNumEstrellas(resultSet.getFloat(6));
            productAux.setDescripcionProducto(resultSet.getString(7));
            System.out.println("Estoy en DAOProduct en ProductCategory");
            System.out.println("product = " + productAux.toString());
            lstProducts.add(productAux);

        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    motorSQL.close();
    System.out.println("SALGO DAO  FICHA PRODUCTO \n" + lstProducts);
    return lstProducts;
}


}

