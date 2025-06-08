<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Listado de Clientes</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css2?family=Montserrat&display=swap" rel="stylesheet">
  <style>
    body {
        font-family: 'Montserrat', sans-serif;
        background: linear-gradient(135deg, #ffffff 0%, #ffe6eb 100%);
    }
    .navbar-custom {
        background-color: #ec0000;
    }
    .navbar-custom .nav-link, 
    .navbar-custom .navbar-brand, 
    .navbar-custom .dropdown-toggle {
        color: white;
        font-weight: bold;
    }
    .navbar-custom .nav-link:hover, 
    .navbar-custom .dropdown-menu a:hover {
        color: #ec0000;
        background-color: white;
    }
    .btn-custom {
        background-color: white;
        color: #ec0000;
        border: 2px solid #ec0000;
        font-weight: bold;
    }
    .btn-custom:hover {
        background-color: #ec0000;
        color: white;
    }
    .dropdown-toggle::after {
        display: none;
    }
    .dropdown-toggle {
        font-size: 1.2rem;
        padding: 0.375rem 0.75rem;
        color: #ec0000 !important;
        background-color: white !important;
        border: 2px solid #ec0000;
        font-weight: bold;
    }
  </style>
</head>
<body>
  <!-- Barra de Navegacion -->
  <nav class="BarraNavegacion">
    <div class="container-fluid">
        <div class="d-flex align-items-center">
            <div class="dropdown me-3">
                <button class="btn dropdown-toggle" type="button" data-bs-toggle="dropdown">
                    ☰
                </button>
                <ul class="dropdown-menu">
                    <li><a class="dropdown-item" href="#">CLIENTES</a></li>
                    <li><a class="dropdown-item" href="#">CUENTAS</a></li>
                    <li><a class="dropdown-item" href="#">PRÉSTAMOS</a></li>
                    <li><a class="dropdown-item" href="#">INFORMES</a></li>
                    <li><a class="dropdown-item" href="#">CERRAR SESIÓN</a></li>
                </ul>
            </div>
            <a href="AgregarClientes.jsp" class="btn btn-custom me-2">Agregar Cliente</a>
            <a href="ListarClientes.jsp" class="btn btn-custom">Listar Clientes</a>
        </div>
        <span class="navbar-text text-white">
            <%= session.getAttribute("nombreUsuario") != null ? session.getAttribute("nombreUsuario") : "INVITADO" %>
        </span>
    </div>
  </nav>

  <div class="Contenedor">
    <h3 class="mb-4 text-center">Listado de Clientes</h3>

    <!-- Busqueda y Filtros -->
    <form class="d-flex justify-content-center mb-4" method="get" action="ListarClientes.jsp">
      <input type="text" name="busqueda" class="form-control w-25 me-2" placeholder="Buscar...">
      <select name="filtro" class="form-select w-25 me-2">
        <option selected>Seleccione un filtro</option>
      </select>
      <button type="submit" class="btn btn-custom">Buscar</button>
    </form>

    <!-- Tablla -->

    <div class="Tabla">
      <table class="table table-striped table-bordered text-center">
        <thead class="table-dark">
          <tr>
            <th>DNI</th><th>CUIL</th><th>Nombre</th><th>Apellido</th><th>Sexo</th>
            <th>Nacionalidad</th><th>Nacimiento</th><th>Dirección</th>
            <th>Localidad</th><th>Provincia</th><th>Correo</th>
            <th>Teléfono</th><th>Acciones</th>
          </tr>
        </thead>
        <tbody>
        
          <tr>
            <td>42419413</td><td>1234</td><td>Martin</td><td>Serafini</td><td>Masculino</td>
            <td>Argentino</td><td>06/01/2000</td><td>Matheu</td>
            <td>Tigre</td><td>Buenos Aires</td><td>Guillermo@mail.com</td>
            <td>1123889911</td>
            <td>
              <button class="btn btn-warning btn-sm">Editar</button>
              <button class="btn btn-danger btn-sm">Eliminar</button>
            </td>
          </tr>
          
        </tbody>
      </table>
    </div>

    <!-- Paginacion -->
    <nav class="d-flex justify-content-center mt-4">
      <ul class="pagination">
      </ul>
    </nav>
  </div>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
