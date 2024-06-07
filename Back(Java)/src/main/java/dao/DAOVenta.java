package dao;


import beans.Venta;
import connection.MotorSQL;

public class DAOVenta {
    private MotorSQL motorSQL;

    //PASO ID_USER X ANDROID SHAREDPREFERENCES
    //PASO ID_PRODUCT X INTENT IGUAL QUE PARA RATE
    private String SQL_ADD_VENTA = "INSERT INTO Venta (Fecha_Venta, Hora_Venta, Id_Usuario, Id_Producto, Id_Direccion_Cliente)" +
    "VALUES " ;


    public DAOVenta(){
        motorSQL = MotorSQL.getMotorSQL();
    }

    public String addVenta (Venta venta){
        System.out.println("ENTRO AL ADD VENTA");
        String sql ="";
        sql = SQL_ADD_VENTA;
        sql += "(CURRENT_DATE, CURRENT_TIME, " + "(SELECT Id_Usuario FROM Producto WHERE Id_Producto =" + venta.getIdProductoVendido() +  "), " +
        venta.getIdProductoVendido() +  ", (SELECT Id_Direccion_Cliente FROM direccion_cliente WHERE Id_usuario= " +
                "(SELECT Id_Usuario FROM Producto WHERE Id_Producto =" + venta.getIdProductoVendido()+")"+"))";
        System.out.println(sql);
        motorSQL.connect();
        motorSQL.executeUpdate(sql);
        motorSQL.close();
        return "";
    }
}
