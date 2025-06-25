<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="entidades.Cliente"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Listado de Clientes</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css2?family=Montserrat&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="../../z-CSS/ABMLClientesCSS/ListadoClientes.css">
</head>
<body>
  <!-- Barra de Navegacion -->
  <nav class="navbar navbar-expand-lg navbar-custom px-4">
    <div class="container-fluid">
        <div class="d-flex align-items-center">
            <div class="dropdown me-3">
                <button class="btn dropdown-toggle" type="button" data-bs-toggle="dropdown">
                    ☰
                </button>
                <ul class="dropdown-menu">
                    <li><a class="dropdown-item" href="../ABMLCuentas/AgregarCuentas.jsp">CUENTAS</a></li>
                    <li><a class="dropdown-item" href="../AutorizacionPrestamos.jsp">PRÉSTAMOS</a></li>
                    <li><a class="dropdown-item" href="../Informes.jsp">INFORMES</a></li>
                    <li><a class="dropdown-item" href="../../Login/IngresoAdministrador.jsp">CERRAR SESIÓN</a></li>
                </ul>
            </div>
            <a href="AgregarClientes.jsp" class="btn btn-custom me-2">Agregar Cliente</a>
            <a href="ListadoClientes.jsp" class="btn btn-custom">Listar Clientes</a>
        </div>
        <span class="navbar-text text-white">
            <%= session.getAttribute("nombreUsuario") != null ? session.getAttribute("nombreUsuario") : "INVITADO" %>
        </span>
    </div>
  </nav>

  <div class="container mt-5">
    <h3 class="text-center mb-4 text-danger">Listado de Clientes</h3>

    <!-- Busqueda y Filtros -->
    <form class="d-flex justify-content-center mb-4" method="get" action="/TP_INTEGRADOR_GRUPO_02/ListadoClientesServlet">
      <input type="text" name="busqueda" class="form-control w-25 me-2" placeholder="Buscar...">
      <select name="filtro" class="form-select w-25 me-2">
        <option selected>Seleccione un filtro</option>
      </select>
      <button type="submit" class="btn btn-custom" name="btnbuscar" >Buscar</button>
    </form>

<%
  ArrayList<Cliente> lista = null;
  if(request.getAttribute("ListaCli") !=null){
	  
	  lista = (ArrayList<Cliente>) request.getAttribute("ListaCli");
	  
  }
%>

   <!-- Tabla -->
	<div class="table-responsive d-flex justify-content-center">
  	<table class="table table-striped table-bordered text-center w-auto">
    	<thead class="table-dark">
      	<tr>
        	<th>ID Cliente</th><th>DNI</th><th>CUIL</th><th>Nombre</th><th>Apellido</th><th>Sexo</th>
       		<th>Nacionalidad</th><th>Nacimiento</th><th>Dirección</th>
        	<th>Provincia</th><th>Localidad</th><th>Correo</th>
        	<th>Teléfono</th><th>Contrasena</th><th>Acciones</th>
      	</tr>
    	</thead>
    	<tbody>
    	
    	<%if(lista != null)
    	for(Cliente cli : lista){ %>
    	
      	<tr>
        	<td><%= cli.getIdCliente() %></td><td><%= cli.getDniCliente() %></td><td><%= cli.getCuilCliente() %></td><td><%= cli.getNombreCliente() %></td>
        	<td><%= cli.getApellidoCliente() %></td><td><%= cli.getSexoCliente() %></td><td><%= cli.getNacionalidadCliente() %></td>
        	<td><%= cli.getFechaNacCliente() %></td><td><%= cli.getDireccionCliente() %></td><td><%= cli.getProvinciaCliente() %></td>
        	<td><%= cli.getLocalidadCliente() %></td><td><%= cli.getCorreoCliente() %></td><td><%= cli.getTelefonoCliente() %></td>
        	<td><%= cli.getContrasenaCliente() %></td>
        	<td>
          		<button class="btn btn-warning btn-sm">Editar</button>
          		<button class="btn btn-danger btn-sm">Eliminar</button>
        	</td>
      	</tr>
      	
      	<% } %>
      	
    	</tbody>
  	</table>
	</div>

    <!-- Paginacion de la tabla -->
    <nav class="d-flex justify-content-center mt-4">
      <ul class="pagination">
      </ul>
    </nav>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
