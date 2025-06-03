<%@ page import="java.util.List, java.util.ArrayList, entidades.Seguros, entidades.TipoSeguro, dao.TiposeguroDao" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Agregar seguros</title>
</head>
<body>
	
	<a href="Inicio.jsp">Inicio</a><a href="/TP7_GRUPO_02/servletAgregarSeguros.jsp"> Agregar Seguros</a><a href="/TP7_GRUPO_02/ListarSegurosServlet"> Listar Seguros</a>
	
	<form action="/TP7_GRUPO_02/servletAgregarSeguros" method="get">
	<h1>Agregar Seguros</h1>
	
	
	<table>
	<tr><td>Id Seguro:</td> <td> <%= request.getAttribute("IDsiguiente") %> </td></tr>
	<tr><td>Descripcion:</td> <td><input type="text" name="txtDescripcion"></td></tr>
	<tr><td>Tipo de seguro:</td> <td> <select name="idTipo"> 
		 
		 <%
		 	List<TipoSeguro> listaTipo = (List<TipoSeguro>) request.getAttribute("listaTipo");
		
		    if (listaTipo != null) {
		        for (TipoSeguro tipo : listaTipo) {
		%>
		            <option value="<%= tipo.getIdTipo() %>"><%= tipo.getDescripcion() %></option>
		<%
		        }
		    }
		%> </select> </td> </tr>
	<tr><td>Costo Contratación:</td> <td> <input type="text" name="txtCostoContratacion"> </td> </tr>
	<tr><td>Costo Máximo Asegurado:</td> <td> <input type="text" name="txtCostoMaximo"> </td> </tr>
	<tr><td></td> <td> <input type="submit" name="btnAceptar1" value="Aceptar"> </td></tr>
	</table>
	<input type="submit" name="btnAceptar2" value="Aceptar">
	</form>
	
	
</body>
</html>