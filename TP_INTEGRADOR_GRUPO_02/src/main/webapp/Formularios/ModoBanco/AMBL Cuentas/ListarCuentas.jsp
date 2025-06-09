<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Listar Cuentas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Montserrat', sans-serif;
            background: linear-gradient(135deg, #ffffff 0%, #ffe6eb 100%);
            margin: 0;
            padding: 0;
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
        .btn.dropdown-toggle {
            color: #ec0000 !important;
        }
    </style>
</head>
<body>

<!-- NAVBAR -->
<nav class="navbar navbar-expand-lg navbar-custom px-4">
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
            <a href="AgregarCuentas.jsp" class="btn btn-custom me-2">Agregar Cuenta</a>
            <a href="ListarCuentas.jsp" class="btn btn-custom">Listar Cuentas</a>
        </div>
        <span class="navbar-text text-white">
            <%= session.getAttribute("nombreUsuario") != null ? session.getAttribute("nombreUsuario") : "INVITADO" %>
        </span>
    </div>
</nav>

<!-- CONTENIDO -->
<div class="container mt-5">
    <h3 class="text-center mb-4 text-danger">Listado de Cuentas</h3>

    <!-- Busqueda y Filtros -->
    <form class="d-flex justify-content-center mb-4" method="get" action="ListarCuentas.jsp">
        <input type="text" name="busqueda" class="form-control w-25 me-2" placeholder="Buscar...">
        <select name="filtro" class="form-select w-25 me-2">
            <option selected>Seleccione un filtro</option>
        </select>
        <button type="submit" class="btn btn-custom">Buscar</button>
    </form>

    <!-- Tabla de Cuentas -->
    <div class="table-responsive d-flex justify-content-center">
        <table class="table table-striped table-bordered text-center w-auto">
            <thead class="table-dark">
                <tr>
                    <th>ID Cuenta</th><th>ID Cliente</th><th>Nro Cuenta</th><th>Tipo</th>
                    <th>CBU</th><th>Saldo</th><th>Creación</th><th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>0001</td><td>0001</td><td>123321</td><td>Cuenta Corriente</td><td>75847261947</td>
                    <td>$15000</td><td>06/01/2000</td>
                    <td>
                        <button class="btn btn-warning btn-sm">Editar</button>
                        <button class="btn btn-danger btn-sm">Eliminar</button>
                        <button class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#modalAsignarCliente">Asignar</button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>

    <!-- Paginación -->
    <nav class="d-flex justify-content-center mt-4">
        <ul class="pagination">
            <!-- Pagination dinámica futura -->
        </ul>
    </nav>
</div>

<!-- MODAL ASIGNAR CLIENTE -->
<div class="modal fade" id="modalAsignarCliente" tabindex="-1" aria-labelledby="modalAsignarClienteLabel" aria-hidden="true">
  <div class="modal-dialog modal-xl modal-dialog-centered modal-dialog-scrollable">
    <div class="modal-content">
      <div class="modal-header bg-danger text-white">
        <h5 class="modal-title" id="modalAsignarClienteLabel">Seleccionar Cliente</h5>
        <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Cerrar"></button>
      </div>
      <div class="modal-body">
        
        <!-- Busqueda y Filtro -->
        <form class="d-flex justify-content-center mb-4">
          <input type="text" name="busquedaCliente" class="form-control w-25 me-2" placeholder="Buscar cliente...">
          <select name="filtroCliente" class="form-select w-25 me-2">
            <option selected>Seleccione un filtro</option>
          </select>
          <button type="submit" class="btn btn-custom">Buscar</button>
        </form>

        <!-- Tabla de Clientes -->
        <div class="table-responsive">
          <table class="table table-striped table-bordered text-center" style="min-width: 1200px; width: 100%;">
            <thead class="table-dark">
              <tr>
                <th>ID Cliente</th><th>DNI</th><th>CUIL</th><th>Nombre</th><th>Apellido</th><th>Sexo</th>
       			<th>Nacionalidad</th><th>Nacimiento</th><th>Dirección</th><th>Localidad</th><th>Provincia</th>
       			<th>Correo</th><th>Teléfono</th><th>Acciones</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>0001</td><td>42419413</td><td>1234</td><td>Martin</td><td>Serafini</td><td>Masculino</td>
        		<td>Argentino</td><td>06/01/2000</td><td>Matheu</td><td>Tigre</td><td>Buenos Aires</td>
        		<td>Guillermo@mail.com</td><td>06012000</td>
                <td>
                	<button class="btn btn-success btn-sm">Asignar</button>
                </td>
              </tr>
            </tbody>
          </table>
        </div>

      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
      </div>
    </div>
  </div>
</div>

<!-- Bootstrap Bundle -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
