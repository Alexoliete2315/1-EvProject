package beans;

public class User {
    private int idUser;
    private String username;
    private String password;
    private  int venta;

    public User() {
        this.setventa(0);
    }


    @Override
    public String toString() {
        return "User{" +
                "id='" + idUser + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


    public String toStringventa() {
        return "User{" +
                "idUser=" + idUser +
                ", username='" + username + '\'' +
                ", venta=" + venta +
                '}';
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(int idUser, String username, int venta) {
        this.idUser = idUser;
        this.username = username;
        this.venta = venta;
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

    public int getventa() {
        return venta;
    }

    public void setventa(int venta) {
        this.venta = venta;
    }
}

