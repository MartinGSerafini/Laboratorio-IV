<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listar seguros</title>
</head>
<body>
	
	<a href="Inicio.jsp">Inicio</a>  <a href="AgregarSeguros.jsp"> Agregar Seguros</a>  <a href="ListarSeguros.jsp"> Listar Seguros</a>
	<h2>"Tipo de seguros en la base de datos"</h2>
	
	<form action="servletListarSeguros" method="post">
	Busqueda por tipo de seguros:<select></select><input type="submit" name="btnFiltrar" value="Filtrar"> </br> </br>
	<table border="1">
	<tr><th>ID Seguro</th> <th>Descripcion Seguro</th> <th>Descripcion Tipo Seguro</th> <th>Costo Contratacion</th> <th>Costo Maximo Asegurado</th></tr>
	</table>
	</form>

</body>
</html>