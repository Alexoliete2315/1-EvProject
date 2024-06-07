package dao;

import connection.MotorSQL;
import beans.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOUser {

    private MotorSQL motorSQL;

    private final String tableName = "usuario";
    private final String SQL_FINDALL = "SELECT * FROM " + tableName + " WHERE 1=1";

    //SELECT
    //    U.Id_Usuario,
    //    U.Nombre_Usuario,
    //    U.Apellido_1,
    //    U.Apellido_2,
    //    U.Email,
    //    U.Telefono,
    //    U.Username,
    //    V.Id_Usuario AS Usuario_Venta,
    //    COUNT(*) AS NumeroVentas
    //FROM Usuario U
    //LEFT JOIN Venta V ON U.Id_Usuario = V.Id_Usuario
    //GROUP BY U.Id_Usuario
    //ORDER BY NumeroVentas DESC;
    private final String SQLMasVentas = "SELECT U.Id_Usuario, U.Username, COUNT(V.Id_Venta) as TotalVentas " +
            "FROM Usuario U " +
            "INNER JOIN Venta V ON U.Id_Usuario = V.Id_Usuario " +
            "GROUP BY U.Id_Usuario " +
            "ORDER BY TotalVentas DESC " +
            "LIMIT 10;";






    public DAOUser(){
        motorSQL = MotorSQL.getMotorSQL();
    }

    public ArrayList<User> findAll(User user){
        String sql = "";
        sql = SQL_FINDALL;
        sql += " AND username = '" + user.getUsername() + "' AND password = '" + user.getPassword()+'\'';
        System.out.println(sql);
        ArrayList<User> usersList = new ArrayList<>();
        motorSQL.connect();
        ResultSet resultSet = motorSQL.executeQuery(sql);
        try {
            System.out.println("TRY findall usuario");
            while(resultSet.next()){
                User userAux = new User();
                userAux.setIdUser(resultSet.getInt(1));
                System.out.println("resultSet.getInt(1)");
                System.out.println(resultSet.getInt(1));
                userAux.setUsername(resultSet.getString(7));
                System.out.println("resultSet.getString(7)");
                System.out.println(resultSet.getString(7));
                userAux.setPassword(resultSet.getString(8));
                System.out.println("resultSet.getString(8)");
                System.out.println(resultSet.getString(8));
                usersList.add(userAux);
                System.out.println(userAux.toString());

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        motorSQL.close();
        System.out.println("LISTA USERS: " + usersList);
        return usersList;
    }

    public ArrayList<User> userMostSells(User user) {
        ArrayList<User> usersList = new ArrayList<>();

        motorSQL.connect();
        ResultSet resultSet = motorSQL.executeQuery(SQLMasVentas);
        try {
            while(resultSet.next()){
                User userAux = new User();
                userAux.setIdUser(resultSet.getInt(1));
                userAux.setUsername(resultSet.getString(2));
                userAux.setventa(resultSet.getInt(3));
                System.out.println("Estoy en DAOuser en userMostSells");
                System.out.println("usuario = " + userAux.toStringventa());
                usersList.add(userAux);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        motorSQL.close();
        return usersList;
    }


}
