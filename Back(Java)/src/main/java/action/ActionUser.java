package action;


import beans.User;
import com.google.gson.Gson;
import dao.DAOUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class ActionUser {
    DAOUser daoUser;
    public String execute(HttpServletRequest request, HttpServletResponse response){
        String answer = "";

        String action = request.getParameter("ACTION");
        String[] method = action.split("\\.");

        switch (method[1]){
            case "LOGIN":
                answer = login(request, response);
                System.out.println("Answer login= " + answer);
                break;
            case "FILTER":
                answer = filtro(request, response);
                System.out.println("Answer filtro");
                break;
            default:
                System.out.println("DEFAULT ACTION");
                break;
        }



        return answer;
    }

    private String login(HttpServletRequest request, HttpServletResponse response) {
        User user = new User();
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        System.out.println(request.getParameter("username"));
        System.out.println(request.getParameter("password"));
        ArrayList<User> listUser = new DAOUser().findAll(user);
        String jsonUsuario = "";
        Gson gson = new Gson();
        jsonUsuario += "{\"message\": \"Esto es un mensaje de ejemplo\"," +
                "\"usersList\": [";
        if (!listUser.isEmpty()){
            jsonUsuario += gson.toJson(listUser.get(0));
        }
        jsonUsuario += "]}";

        return jsonUsuario;
    }


    private String filtro(HttpServletRequest request, HttpServletResponse response){
        String jsonUsers = "";
        User user = new User();
        ArrayList<User> usersList = new ArrayList<>();



      if (!(request.getParameter("idUser") == null)){
            user.setIdUser(Integer.parseInt(request.getParameter("idUser")));
       }

       if (!(request.getParameter("username") == null )){
           user.setUsername(request.getParameter("username"));
       }

//       if (!(request.getParameter("password") == null)){
//            user.setPassword(request.getParameter("password"));
//       }

        /*
        ----FILTROS----
        equals VIENE DE ANDROID
        CUANDO EN EL MODEL HACES LA LLAMDA AL APISERVICE
        DONDE LO PASAS POR PARAMETRO
        este caso (USER.FILTER, "userMostSells")
         */
        String method = request.getParameter("FILTER");
        System.out.println("Que parametro le paso?"+request.getParameter("FILTER"));
        if (method.equals("userMostSells")){
            System.out.println("entro al filtro de ventas");
            usersList = new DAOUser().userMostSells(user);
            System.out.println("usersList al volver del DAO= " + usersList.size());
        }

        //json builder
        jsonUsers += "{ \"message\": \"Esto es un mensaje de prueba\", " +
                "\"usersList\": [";
        Gson gson = new Gson();
        for (User userAux : usersList) {
            jsonUsers += gson.toJson(userAux)+", ";
        }
        jsonUsers = jsonUsers.substring(0, jsonUsers.length()-2);
        jsonUsers += "]}";
        System.out.println("THIS IS JSON "+jsonUsers);
        return jsonUsers;
    }

}
