package action;



import beans.Compra;
import beans.Product;
import beans.Venta;
import com.google.gson.Gson;
import dao.DAOCompra;
import dao.DAOProduct;
import dao.DAOVenta;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class ActionCompra {
    DAOCompra daoCompra = new DAOCompra();
    DAOVenta daoVenta = new DAOVenta();
    public String execute(HttpServletRequest request, HttpServletResponse response){
        String answer = "";

        String action = request.getParameter("ACTION");
        String[] method = action.split("\\.");

        switch (method[1]){
            case "HISTORICO":
                System.out.println("llegas al action historico compra");
                answer = historialCompra(request, response);
                System.out.println("Answer historico compra= " + answer);
                break;
            case "VENTA":
                System.out.println("llegas al action COMPRA-VENTA");
                answer = addCompraVenta(request, response);
                System.out.println("Answer en compra venta= " + answer);
                break;
            default:
                System.out.println("DEFAULT ACTION");
                break;
        }
        return answer;
    }



    private String historialCompra(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("ActionMethod historial");
        Compra compra = new Compra();
        String idUserParameter = request.getParameter("idUser");
        if (idUserParameter != null && !idUserParameter.isEmpty()) {
            System.out.println("\n Entro al if del idUser  El id es: \n" +idUserParameter);
            compra.setIdComprador(Integer.parseInt(idUserParameter));
        }else{
            System.out.println("NO EXISTE ID");
        }
        System.out.println("Action Lst: " + compra.toString());
        ArrayList<Compra> lstCompras = new DAOCompra().historicoCompras(compra);
        Gson gson = new Gson();
        String json = "";
        json += "{\"message\": \"Esto es un mensaje de prueba\",\"lstCompras\": [";

        for (Compra compraAux:lstCompras) {
            json += gson.toJson(compraAux) + ", ";
        }
        json = json.substring(0, json.length()-2);
        json += "]}";
        return json;
    }

    private String addCompraVenta(HttpServletRequest request, HttpServletResponse response){
        System.out.println("ENTRO AL ADD COMPRA-VENTA");
        //construyo la compra y le hago su dao
        int idComprador = Integer.parseInt(request.getParameter("idUser"));
        int idProductoComprado = Integer.parseInt(request.getParameter("idProduct"));
        Compra compra = new Compra(idComprador, idProductoComprado);
        daoCompra.addCompra(compra);

        //construyo la venta
        int idProductoVendido = Integer.parseInt(request.getParameter("idProduct"));
        Venta venta = new Venta(idProductoVendido);
        daoVenta.addVenta(venta);
        return "";
    }
}
