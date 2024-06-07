package dao;

import beans.Product;
import beans.User;
import beans.Valoracion;
import connection.MotorSQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOValoracion {
    private MotorSQL motorSQL;


    /*
    INSERT INTO Valoracion (Id_Valoracion, Id_Usuario, Id_Producto, Estrellas, Resena)
    VALUES (1, 123, 456, 5, 'Excelente producto, muy satisfecho'),
           (2, 124, 457, 4, 'Buen producto, entrega rápida'),
           (3, 125, 458, 3, 'Producto promedio, esperaba más');

           TRAER DE ANDROID
                ID_USUARIO
                ID_PRODUCTO
                ESTRELLAS
                RESENA(POSIBLE)
     */
    private final  String SQLAddValoracion= "INSERT INTO Valoracion (Id_Usuario, Id_Producto, Estrellas, Resena) VALUES";

    /*
    SELECT
    p.Id_Producto,
    p.Nombre_Producto,
    AVG(v.Estrellas) AS PromedioValoracion
FROM
    Producto p
JOIN
    Valoracion v ON p.Id_Producto = v.Id_Producto
GROUP BY
    p.Id_Producto, p.Nombre_Producto
ORDER BY
    PromedioValoracion DESC
LIMIT 10;

     */
    private final String SQLLstBetterRates = "SELECT " +
            "p.Id_Producto, " +
            "p.Nombre_Producto, " +
            "p.Imagen_Producto, " +
            "AVG(v.Estrellas) AS PromedioValoracion " +
            "FROM " +
            "Producto p " +
            "JOIN " +
            "Valoracion v ON p.Id_Producto = v.Id_Producto " +
            "GROUP BY " +
            "p.Id_Producto, p.Nombre_Producto, p.Imagen_Producto " +
            "ORDER BY " +
            "PromedioValoracion DESC " +
            "LIMIT 10;";

    public DAOValoracion() {motorSQL = MotorSQL.getMotorSQL();}

    public String addProductRate(Valoracion bean){
        System.out.println("ESTOY EN ADD RATE");
        String sql = "";
        sql = SQLAddValoracion;
        sql += "('" + bean.getIdUser() +
                "', '" + bean.getIdProduct() +
                "', '" + bean.getNumEstrellas();

        if (bean.getResena()!=null){
            sql += "', '" + bean.getResena() +
                    ")";
        }else{
            sql += "', '" + "NO ESPECIFICA" + "')";
        }
        System.out.println("SQL en addProductRate \n " + sql);
        motorSQL.connect();
        motorSQL.executeUpdate(sql);
        motorSQL.close();
        return "";
    }
    public ArrayList<Valoracion> productBetterRate(Valoracion valoracion) {
        ArrayList<Valoracion> lstValoraciones = new ArrayList<>();

        motorSQL.connect();
        ResultSet resultSet = motorSQL.executeQuery(SQLLstBetterRates);
        try {
            while(resultSet.next()){
                Valoracion valoracionAux = new Valoracion();
                valoracionAux.setIdProduct(resultSet.getInt(1));
                valoracionAux.setNombreProducto(resultSet.getString(2));
                valoracionAux.setImagenProducto(resultSet.getString(3));
                valoracionAux.setPromedioValoracion(resultSet.getDouble(4));
                System.out.println("Estoy en DAOuser en userMostSells");
                System.out.println("usuario = " + valoracionAux.toStringValoracion());
                lstValoraciones.add(valoracionAux);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        motorSQL.close();
        return lstValoraciones;
    }
}
