package dao;

import beans.Compra;
import connection.MotorSQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOCompra {
    private MotorSQL motorSQL;

    //PASO ID_USER X ANDROID SHAREDPREFERENCES
    //PASO ID_PRODUCT X INTENT IGUAL QUE PARA RATE
    private String SQL_ADD_COMPRA = "INSERT INTO Compra (Fecha_Compra, Hora_Compra, Id_Usuario, Id_Producto, Id_Direccion_Vendedor) " +
            "VALUES ";
    private String SQL_HISTORICO_COMPRAS = "SELECT " +
            "Compra.Id_Compra, " +
            "Compra.Fecha_Compra, " +
            "Compra.Hora_Compra, " +
            "Comprador.Nombre_Usuario AS Comprador, " +
            "Vendedor.Nombre_Usuario AS Vendedor, " +
            "Producto.Nombre_Producto AS Producto_Comprado, " +
            "Producto.Precio_Producto AS Precio_Producto, " +
            "Producto.Marca_Producto AS Marca_Producto, " +
            "Producto.Imagen_Producto AS Imagen_Producto " +
            "FROM " +
            "Compra " +
            "JOIN " +
            "Usuario AS Comprador ON Compra.Id_Usuario = Comprador.Id_Usuario " +
            "JOIN " +
            "Producto ON Compra.Id_Producto = Producto.Id_Producto " +
            "JOIN " +
            "Usuario AS Vendedor ON Producto.Id_Usuario = Vendedor.Id_Usuario " +
            "WHERE " +
            "Compra.Id_Usuario = ";

    public DAOCompra(){
        motorSQL = MotorSQL.getMotorSQL();
    }

    public String addCompra (Compra compra){
        String sql ="";
        sql = SQL_ADD_COMPRA;
        sql += "(CURRENT_DATE, CURRENT_TIME, " + compra.getIdComprador() + ", " + compra.getIdProductoComprado() +
                ", (SELECT Id_Direccion_Vendedor FROM direccion_vendedor WHERE Id_usuario= " + compra.getIdComprador() +"))";

        System.out.println(sql);
        motorSQL.connect();
        motorSQL.executeUpdate(sql);
        motorSQL.close();
        return "";
    }



    public ArrayList<Compra>historicoCompras(Compra compra){
        String sql = "";
        sql = SQL_HISTORICO_COMPRAS;
        sql += compra.getIdComprador();
        sql += " ORDER BY Compra.Fecha_Compra DESC, Compra.Hora_Compra DESC ";
        System.out.println(sql);
        ArrayList<Compra> lstCompras = new ArrayList<>();
        motorSQL.connect();
        ResultSet resultSet = motorSQL.executeQuery(sql);

        try {
            System.out.println("TRY historico compras");
            while(resultSet.next()){
                Compra compraAux = new Compra();
                compraAux.setFechaCompra(resultSet.getString(2));
                compraAux.setHoraCompra(resultSet.getString(3));
                compraAux.setComprador(resultSet.getString(4));
                compraAux.setVendedor(resultSet.getString(5));
                compraAux.setProductoComprado(resultSet.getString(6));
                compraAux.setPrecioProducto(resultSet.getDouble(7));
                compraAux.setMarcaProducto(resultSet.getString(8));
                compraAux.setImagenProducto(resultSet.getString(9));
                lstCompras.add(compraAux);
                System.out.println("TO STRING TRAS RESULT SET historico(NO le paso ID)\n"+compraAux.toString());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        motorSQL.close();
        System.out.println("LISTA compras: " + lstCompras);
        return lstCompras;
    }


}
