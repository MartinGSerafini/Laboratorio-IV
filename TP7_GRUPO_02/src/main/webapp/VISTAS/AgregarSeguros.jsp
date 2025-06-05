<%@page import="dao.SeguroDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Agregar seguros</title>
</head>
<body>
	
	<a href="Inicio.jsp">Inicio</a><a href="AgregarSeguros.jsp"> Agregar Seguros</a><a href="/TP7_GRUPO_02/ListarSegurosServlet"> Listar Seguros</a>
	
	<form action="servletAgregarSeguros" method="get">
	<h1>Agregar Seguros</h1>
	
	<% SeguroDao segu = new SeguroDao();%>
	
	<table>
	<tr><td>Id Seguro:</td> <td> <%= segu.obtenerUltimoID() %> </td></tr>
	<tr><td>Descripcion:</td> <td><input type="text" name="txtDescripcion"></td></tr>
	<tr><td>Tipo de seguro:</td> <td> <select>  </select> </td> </tr>
	<tr><td>Costo Contratación:</td> <td> <input type="text" name="txtCostoContratacion"> </td> </tr>
	<tr><td>Costo Máximo Asegurado:</td> <td> <input type="text" name="txtCostoMaximo"> </td> </tr>
	<tr><td></td> <td> <input type="submit" name="btnAceptar1" value="Aceptar"> </td></tr>
	</table>
	<input type="submit" name="btnAceptar2" value="Aceptar">
	</form>
	
	
</body>
</html>