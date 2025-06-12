<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <form class="d-flex justify-content-center mb-4" method="get" action="ListarClientes.jsp">
      <input type="text" name="busqueda" class="form-control w-25 me-2" placeholder="Buscar...">
      <select name="filtro" class="form-select w-25 me-2">
        <option selected>Seleccione un filtro</option>
      </select>
      <button type="submit" class="btn btn-custom">Buscar</button>
    </form>

   <!-- Tabla -->
	<div class="table-responsive d-flex justify-content-center">
  	<table class="table table-striped table-bordered text-center w-auto">
    	<thead class="table-dark">
      	<tr>
        	<th>ID Cliente</th><th>DNI</th><th>CUIL</th><th>Nombre</th><th>Apellido</th><th>Sexo</th>
       		<th>Nacionalidad</th><th>Nacimiento</th><th>Dirección</th>
        	<th>Localidad</th><th>Provincia</th><th>Correo</th>
        	<th>Teléfono</th><th>Contrasena</th><th>Acciones</th>
      	</tr>
    	</thead>
    	<tbody>
      	<tr>
        	<td>0001</td><td>42419413</td><td>1234</td><td>Martin</td><td>Serafini</td><td>Masculino</td>
        	<td>Argentino</td><td>06/01/2000</td><td>Matheu</td>
        	<td>Tigre</td><td>Buenos Aires</td><td>Guillermo@mail.com</td>
        	<td>1123889911</td><td>06012000</td>
        	<td>
          		<button class="btn btn-warning btn-sm">Editar</button>
          		<button class="btn btn-danger btn-sm">Eliminar</button>
        	</td>
      	</tr>
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
