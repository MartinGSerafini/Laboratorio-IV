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
	
	<a href="<%= request.getContextPath() %>/Inicio">Inicio</a>
	<a href="<%= request.getContextPath() %>/servletAgregarSeguros">Agregar Seguros</a>
	<a href="<%= request.getContextPath() %>/ListarSegurosServlet">Listar Seguros</a>	
	
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
	<%  ArrayList<Seguros> listaSeguros = (ArrayList<Seguros>) request.getAttribute("listaSeguros");;
		%>
				
			
	
	 <table border="1">
	<tr><th>ID Seguro</th> <th>Descripcion Seguro</th> <th>Descripcion Tipo Seguro</th> <th>Costo Contratacion</th> <th>Costo Maximo Asegurado</th></tr>
	<% if(listaSeguros != null){
			for(Seguros s : listaSeguros){%>
	<tr>
	<td><%=s.getIdSeguro() %></td>
	<td><%=s.getDescripcion() %></td>
	<td><%=s.getTipoSeguro().getDescripcion() %></td>
	<td><%=s.getCostoContratacion() %></td>
	<td><%=s.getCostoAsegurado() %></td>
	</tr>
	<%}
		}
	%>
	</table>
	</form>
	
	<form action="/TP7_GRUPO_02/ListarSegurosServlet" method ="get">
	</form>
	
</body>
</html>