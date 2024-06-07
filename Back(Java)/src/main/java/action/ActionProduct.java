package action;

import beans.Product;
import beans.User;
import com.google.gson.Gson;
import dao.DAOProduct;
import dao.DAOUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActionProduct {
    DAOProduct daoProduct;

    public ActionProduct(){
        daoProduct = new DAOProduct();
    }

    public String execute(HttpServletRequest request, HttpServletResponse response){
        String answer = "";

        String action = request.getParameter("ACTION");
        String[] method = action.split("\\.");

        switch(method[1]){
            case "ADD":
                System.out.println("Action: Añadir?");
                answer = addProduct(request, response);
                System.out.println("Answer Añadir Action= " + answer);
                break;
            case "LST":
                System.out.println("Action: Listo Productos");
                answer = listarProductos(request,response);
                System.out.println("Answer listar Action= " + answer);
                break;
            case"FILTER":
                System.out.println("Action: Filtro?");
                answer = filtro(request,response);
                System.out.println("Answer filtro Action= " + answer);
                break;
            case"FILE":
                System.out.println("Action: Ficha?");
                answer = ficha(request,response);
                System.out.println("Answer ficha action= " + answer);
                break;
            default:
                System.out.println("default Action");
                break;
        }
        return answer;
    }

    /**
     * this.idProducto = idProducto;
     *         this.nombreProducto = nombreProducto;
     *         this.precioProducto = precioProducto;
     *         this.marcaProducto = marcaProducto;
     *         this.fechaSubidaProducto = fechaSubidaProducto;
     *         this.descripcionProducto = descripcionProducto;
     *         this.imagenProducto = imagenProducto;
     *         this.idUsuario = idUsuario;
     *
     * @param request
     * @param response
     * @return
     */
    private String listarProductos(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("ActionMethod listo");
        Product product = new Product();
//        product.setNombreProducto("");
//        product.setPrecioProducto((double) -1);
//        product.setMarcaProducto("");
//        product.setFechaSubidaProducto("");
//        product.setDescripcionProducto("");
//        product.setImagenProducto("");
//        product.setIdUser(-1);
//
        if (request.getParameter("IdProducto") != null){
            product.setIdProducto(Integer.parseInt(request.getParameter("IdProducto")));
        }
        if (request.getParameter("nombreProducto") != null) {
            product.setNombreProducto(request.getParameter("nombreProducto"));
        }

        if (request.getParameter("precioProducto") != null) {
            product.setPrecioProducto(Double.parseDouble(request.getParameter("precioProducto")));
        }

        if (request.getParameter("marcaProducto") != null) {
            product.setMarcaProducto(request.getParameter("marcaProducto"));
        }

        if (request.getParameter("fechaSubidaProducto") != null) {
            product.setFechaSubidaProducto(request.getParameter("fechaSubidaProducto"));
        }

        if (request.getParameter("descripcionProducto") != null) {
            product.setDescripcionProducto(request.getParameter("descripcionProducto"));
        }

        if (request.getParameter("imagenProducto") != null) {
            product.setImagenProducto(request.getParameter("imagenProducto"));
        }

        String idUserParameter = request.getParameter("idUser");
        if (idUserParameter != null && !idUserParameter.isEmpty()) {
            System.out.println("\n Entro al if del idUser  El id es: \n" +idUserParameter);
            product.setIdUser(Integer.parseInt(idUserParameter));
        }else{
            System.out.println("NO EXISTE ID");
        }
        System.out.println("Action Lst: " + product.toString());
        ArrayList<Product> lstProducts = new DAOProduct().findAll(product);
        Gson gson = new Gson();
        String json = "";
        json += "{\"message\": \"Esto es un mensaje de prueba\",\"lstProducts\": [";

        for (Product productAux:lstProducts) {
            json += gson.toJson(productAux) + ", ";
        }
        json = json.substring(0, json.length()-2);
        json += "]}";
        return json;
    }

    /**
     * constructor
     *  this.idProducto = idProducto;
     *         this.nombreProducto = nombreProducto;
     *         this.precioProducto = precioProducto;
     *         this.marcaProducto = marcaProducto;
     *         this.fechaSubidaProducto = fechaSubidaProducto;
     *         this.descripcionProducto = descripcionProducto;
     *         this.imagenProducto = imagenProducto;
     *         this.idUsuario = idUsuario;
     *
     *
     * @param request
     * @param response
     * @return
     */
    private String addProduct(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("ENTRO AL ADD");
        int idProducto = Integer.parseInt(request.getParameter("idProducto"));
        String nombreProducto = request.getParameter("nombreProducto");
        double precioProducto = Double.parseDouble(request.getParameter("precioProducto"));
        String marcaProducto = request.getParameter("marcaProducto");
        String fechaSubidaProducto = request.getParameter("fechaSubidaProducto");
        String descripcionProducto = request.getParameter("descripcionProducto");
        String imagenProducto = request.getParameter("imagenProducto");
        int idUser = Integer.parseInt(request.getParameter("idUser"));


        Product product = new Product(idProducto,nombreProducto,precioProducto,marcaProducto,fechaSubidaProducto,descripcionProducto,imagenProducto,idUser);
        System.out.println("(Action Method) Producto Add: " + product.toString());
        daoProduct.add(product);
        return "";
    }

    private String filtro(HttpServletRequest request, HttpServletResponse response){
        String jsonProducts = "";
        Product product = new Product();
        ArrayList<Product> lstProducts = new ArrayList<>();
            System.out.println("CATEGORIA NO ES NULO ES= " + product.getNombreCategoria());
            product.setNombreCategoria(request.getParameter("nombreCategoria"));
        lstProducts = new DAOProduct().ProductsCategory(product);
        System.out.println("lstProducts al volver del DAO= " + lstProducts.size());
        Gson gson = new Gson();
        jsonProducts += "{\"message\": \"Esto es un mensaje de prueba\",\"lstProducts\": [";

        for (Product productAux:lstProducts) {
            jsonProducts += gson.toJson(productAux) + ", ";
        }
        jsonProducts = jsonProducts.substring(0, jsonProducts.length()-2);
        jsonProducts += "]}";
        System.out.println("JSON EN ACTION FILTRO CATEGORIAS "+jsonProducts);
        return jsonProducts;
    }


    private String ficha(HttpServletRequest request, HttpServletResponse response){
        String jsonProducts = "";
        Product product = new Product();
        ArrayList<Product> lstProducts = new ArrayList<>();
        System.out.println("ID PRODUCTO NO ES NULO ES= " + request.getParameter("idProduct"));
        product.setIdProducto(Integer.parseInt(request.getParameter("idProduct")));
        lstProducts = new DAOProduct().fichaProduct(product);
        System.out.println("lista al volver del dao al action= " + lstProducts.size());
        Gson gson = new Gson();
        jsonProducts += "{\"message\": \"Esto es un mensaje de prueba\",\"lstProducts\": [";

        for (Product productAux:lstProducts) {
            jsonProducts += gson.toJson(productAux) + ", ";
        }
        jsonProducts = jsonProducts.substring(0, jsonProducts.length()-2);
        jsonProducts += "]}";
        System.out.println("JSON EN ACTION FICHA "+jsonProducts);
        return jsonProducts;
    }
}
