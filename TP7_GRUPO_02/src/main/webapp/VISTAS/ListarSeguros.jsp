<%@ page import="java.util.List, java.util.ArrayList, entidades.Seguros, entidades.TipoSeguro, dao.TiposeguroDao" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listar seguros</title>
</head>
<body>
	
	<a href="Inicio.jsp">Inicio</a><a href="AgregarSeguros.jsp"> Agregar Seguros</a><a href="/TP7_GRUPO_02/ListarSegurosServlet"> Listar Seguros</a>
	
	<h2>"Tipo de seguros en la base de datos"</h2>
	
	<form action="/TP7_GRUPO_02/ListarSegurosServlet" method ="get">
		 Busqueda por tipo de seguros: <select name="idTipo"> 
		 
		 <%
		 	List<TipoSeguro> listaTipo = (List<TipoSeguro>) request.getAttribute("listaTipo");
		
		    if (listaTipo != null) {
		        for (TipoSeguro tipo : listaTipo) {
		%>
		            <option value="<%= tipo.getIdTipo() %>"><%= tipo.getDescripcion() %></option>
		<%
		        }
		    }
		%>
		 </select>
		 		 
		
		 <input type="submit" value= "Filtrar" name="Filtrar">
	</form>
	
	<form action="/TP7_GRUPO_02/ListarSegurosServlet" method ="get">
	 
	</form>
	
</body>
</html>