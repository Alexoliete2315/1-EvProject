package action;


import beans.User;
import beans.Valoracion;
import com.google.gson.Gson;
import dao.DAOUser;
import dao.DAOValoracion;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class ActionValoracion {

    DAOValoracion daoValoracion = new DAOValoracion();
    public String execute(HttpServletRequest request, HttpServletResponse response){
        String answer = "";

        String action = request.getParameter("ACTION");
        String[] method = action.split("\\.");

        switch (method[1]){
            case "ADD":
                System.out.println("llegas al action add valoracion?");
                answer = addValoracion(request, response);
                System.out.println("Answer add valoracion= " + answer);
                break;
            case "FILTER":
                answer = filtro(request, response);
                System.out.println("Answer Filter Valoracion= " + answer);
                break;
            default:
                System.out.println("DEFAULT ACTION");
                break;
        }
        return answer;
    }

    private String addValoracion(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("ACTION: ENTRO AL ADD VALORACION");
        int idUser = Integer.parseInt(request.getParameter("idUser"));
        int idProducto = Integer.parseInt(request.getParameter("idProducto"));
        float numEstrellas = Float.parseFloat(request.getParameter("numEstrellas"));
        Valoracion valoracion = new Valoracion(idUser,idProducto, numEstrellas);
        System.out.println("(Action Method) Producto Add: " + valoracion.toString());
        daoValoracion.addProductRate(valoracion);
        return "";
    }

    private String filtro(HttpServletRequest request, HttpServletResponse response){
        String jsonRates = "";
        Valoracion valoracion = new Valoracion();
        ArrayList<Valoracion> lstValoraciones = new ArrayList<>();
        if (!(request.getParameter("idProducto") == null)){
            valoracion.setIdProduct(Integer.parseInt(request.getParameter("idProducto")));
        }

        if (!(request.getParameter("nombreProducto") == null )){
            valoracion.setNombreProducto(request.getParameter("nombreProducto"));
        }

        /*
        ----FILTROS----
        equals VIENE DE ANDROID
        CUANDO EN EL MODEL HACES LA LLAMDA AL APISERVICE
        DONDE LO PASAS POR PARAMETRO
        este caso (RATE.FILTER, "productBetterRate")
         */
        String method = request.getParameter("FILTER");
        System.out.println("Que parametro le paso?"+request.getParameter("FILTER"));
        if (method.equals("productBetterRate")){
            System.out.println("entro al a mejores ratings");
            lstValoraciones = new DAOValoracion().productBetterRate(valoracion);
            System.out.println("usersList al volver del DAO= " + lstValoraciones.size());
        }
        //json builder
        jsonRates += "{ \"message\": \"Esto es un mensaje de prueba\", " +
                "\"lstValoraciones\": [";
        Gson gson = new Gson();
        for (Valoracion valoracionAux : lstValoraciones) {
            jsonRates += gson.toJson(valoracionAux)+", ";
        }
        jsonRates = jsonRates.substring(0, jsonRates.length()-2);
        jsonRates += "]}";
        System.out.println("THIS IS JSON "+jsonRates);
        return jsonRates;
    }



}
