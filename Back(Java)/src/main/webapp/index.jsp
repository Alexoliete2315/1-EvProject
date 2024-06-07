<html>
<body>
<h2>Hello World!</h2>

<h1>Comunicación entre JSP y Servlet</h1>

<form action="MyServlet" method="get">
    <input type="submit" value="Llamar al Servlet">
</form>


<p>Mensaje desde el Servlet: <%= request.getAttribute("message") %></p>
</body>
</html>
