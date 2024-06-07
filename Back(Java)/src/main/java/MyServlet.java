

import action.ActionCompra;
import action.ActionProduct;
import action.ActionUser;
import action.ActionValoracion;
//import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/MyServlet")
public class MyServlet  extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Procesa alguna lógica aquí
        String message = "{message: 'Hola desde el Servlet'}";
        response.setContentType("application/json");
        System.out.println("entro al servlet");
        String action = request.getParameter("ACTION");
        PrintWriter out = response.getWriter();
        String[] method = action.split("\\.");
        System.out.println("Action: " + action);
        String answer = "";
        switch(method[0]){
            case "PRODUCT":
                    answer = new ActionProduct().execute(request, response);
                    System.out.println("answer Product Myservlet: " + answer);
                break;
            case "USER":
                answer = new ActionUser().execute(request, response);
                System.out.println("answer User Myservlet: " + answer);
                break;
            case "COMPRA":
                answer= new ActionCompra().execute(request,response);
                System.out.println("answer Compra MyServlet" + answer);
                break;
            case "RATE":
                answer = new ActionValoracion().execute(request, response);
                System.out.println("answer Valoracion Myservlet: " + answer);
                break;

            default:
                System.out.print("default servlet");
                answer = "default servlet";
                break;
        }

        out.print(answer);
//        System.out.println(answer);
    }
}


            //String id= request.getParameter("id");

            //out.print(message);a
            /*String jsonResponse = "{\"message\": \"¡Hola desde el Servlet!_"+ action + "\"}";

            String jsonResponseObject= "{\n" +
                    "    \"message\": \"Este es un mensaje de ejemplo\",\n" +
                    "    \"lstUsers\": [\n" +
                    "        {\n" +
                    "            \"username\": \"username1\",\n" +
                    "            \"token\": \"token1\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"username\": \"username2\",\n" +
                    "            \"token\": \"token2\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"username\": \"username3\",\n" +
                    "            \"token\": \"token3\"\n" +
                    "        }\n" +
                    "    ]\n" +
                    "}";
            User user1 = new User("username1", "token1");
            User user2 = new User("username2", "token2");

            List<User> userList = new ArrayList<>();
            userList.add(user1);
            userList.add(user2);

            String peliculas = "{\n" +
                    "    \"message\": \"Este es un mensaje de ejemplo\",\n" +
                    "    \"lstPeliculas\": [\n" +
                    "        {\n" +
                    "            \"id\": 1,\n" +
                    "            \"titulo\": \"Pelicula 1\",\n" +
                    "            \"descripcion\": \"Descripción de la Pelicula 1\",\n" +
                    "            \"director\": \"Director 1\",\n" +
                    "            \"anyo\": 2022\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": 2,\n" +
                    "            \"titulo\": \"Pelicula 2\",\n" +
                    "            \"descripcion\": \"Descripción de la Pelicula 2\",\n" +
                    "            \"director\": \"Director 2\",\n" +
                    "            \"anyo\": 2023\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"id\": 3,\n" +
                    "            \"titulo\": \"Pelicula 3\",\n" +
                    "            \"descripcion\": \"Descripción de la Pelicula 3\",\n" +
                    "            \"director\": \"Director 3\",\n" +
                    "            \"anyo\": 2021\n" +
                    "        }\n" +
                    "    ]\n" +
                    "}";
            //out.print(convertUsersToJSONString(userList));
            // out.print(jsonResponseObject);

            // Escribir el JSON en el PrintWriter
            //out.print(jsonResponse);
            *//*request.setAttribute("message", message);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
             *//*
            out.print(peliculas);
            out.close();
        }
*/
  /*  public static String convertUsersToJSONString(List<User> users) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[");

        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            jsonBuilder.append("{");
            jsonBuilder.append("\"username\": \"").append(user.getUsername()).append("\", ");
            jsonBuilder.append("\"token\": \"").append(user.getToken()).append("\"");
            jsonBuilder.append("}");

            // Si no es el último elemento, añade una coma
            if (i < users.size() - 1) {
                jsonBuilder.append(", ");
            }
        }

        jsonBuilder.append("]");
        return jsonBuilder.toString();
    }*/


