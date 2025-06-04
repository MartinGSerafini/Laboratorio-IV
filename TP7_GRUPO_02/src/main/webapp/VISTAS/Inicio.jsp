<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inicio</title>
</head>
<body>

	<a href="<%= request.getContextPath() %>/Inicio">Inicio</a>
	<a href="<%= request.getContextPath() %>/servletAgregarSeguros">Agregar Seguros</a>
	<a href="<%= request.getContextPath() %>/ListarSegurosServlet">Listar Seguros</a>
	
	<h1>Soy la página inicio</h1>

</body>
</html>
