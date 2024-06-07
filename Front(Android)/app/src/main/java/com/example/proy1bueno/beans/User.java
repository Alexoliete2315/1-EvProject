package com.example.proy1bueno.beans;

public class User {
    private int idUser;
    private String username;
    private String password; // Puedes agregar otros campos segÃºn tus necesidades
    private int venta;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public User() {
        this.setVenta(0);
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getVenta() {
        return venta;
    }

    public void setVenta(int venta) {
        this.venta = venta;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
