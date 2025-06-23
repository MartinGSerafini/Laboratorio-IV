<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Agregar Cliente</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../../z-CSS/ABMLClientesCSS/AgregarClientes.css">
</head>
<body>

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
    <h2 class="text-center mb-4 text-danger">Agregar Clientes</h2>
    <form class="row g-4" action=" /TP_INTEGRADOR_GRUPO_02/AgregarClienteServlet" method="post">
    	<div class="col-md-6">
            <label for="usuario" class="form-label">Usuario</label>
            <input type="text" class="form-control" id="usuario" name="usuario">
        </div>
        <div class="col-md-6">
            <label for="contrasena" class="form-label">Contraseña</label>
            <input type="password" class="form-control" id="contrasena" name="contrasena">
        </div>
        <div class="col-md-6">
            <label for="dni" class="form-label">DNI</label>
            <input type="number" class="form-control" id="dni" name="dni">
        </div>
        <div class="col-md-6">
            <label for="cuil" class="form-label">CUIL</label>
            <input type="text" class="form-control" id="cuil" name="cuil">
        </div>
        <div class="col-md-6">
            <label for="nombre" class="form-label">Nombre</label>
            <input type="text" class="form-control" id="nombre" name="nombre">
        </div>
        <div class="col-md-6">
            <label for="apellido" class="form-label">Apellido</label>
            <input type="text" class="form-control" id="apellido" name="apellido">
        </div>
        <div class="col-md-6">
            <label for="sexo" class="form-label">Sexo</label>
            <select class="form-select" id="sexo" name="sexo">
                <option value="">Seleccione</option>
                <option value="Masculino">Masculino</option>
                <option value="Femenino">Femenino</option>
            </select>
        </div>
        <div class="col-md-6">
            <label for="nacionalidad" class="form-label">Nacionalidad</label>
            <input type="text" class="form-control" id="nacionalidad" name="nacionalidad">
        </div>
        <div class="col-md-6">
            <label for="fechaNac" class="form-label">Fecha de Nacimiento</label>
            <input type="date" class="form-control" id="fechaNac" name="fechaNac">
        </div>
        <div class="col-md-6">
            <label for="direccion" class="form-label">Dirección</label>
            <input type="text" class="form-control" id="direccion" name="direccion">
        </div>
        <div class="col-md-6">
            <label for="localidad" class="form-label">Localidad</label>
            <input type="text" class="form-control" id="localidad" name="localidad">
        </div>
        <div class="col-md-6">
            <label for="provincia" class="form-label">Provincia</label>
            <input type="text" class="form-control" id="provincia" name="provincia">
        </div>
        <div class="col-md-6">
            <label for="correo" class="form-label">Correo</label>
            <input type="email" class="form-control" id="correo" name="correo">
        </div>
        <div class="col-md-6">
            <label for="telefono" class="form-label">Teléfono</label>
            <input type="text" class="form-control" id="telefono" name="telefono">
        </div>
        <div class="col-12 text-center mt-4">
            <button type="submit" class="btn btn-custom px-5 py-2">Agregar Cliente</button>
        </div>
    </form>
</div>

<%
    String msg = (String) request.getAttribute("mensaje");
    if ("ok".equals(msg)) {
%>
    <script>
        window.onload = function() {
            let modal = new bootstrap.Modal(document.getElementById('modalExito'));
            modal.show();
        };
    </script>
<%
    } else if ("error".equals(msg)) {
%>
    <script>
        window.onload = function() {
            let modal = new bootstrap.Modal(document.getElementById('modalError'));
            modal.show();
        };
    </script>
<% } %>

<!-- Modal Éxito -->
<div class="modal fade" id="modalExito" tabindex="-1" aria-labelledby="modalExitoLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header bg-success text-white">
        <h5 class="modal-title" id="modalExitoLabel">Éxito</h5>
      </div>
      <div class="modal-body">
        Cliente agregado con éxito.
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-success" data-bs-dismiss="modal">Aceptar</button>
      </div>
    </div>
  </div>
</div>

<!-- Modal Error -->
<div class="modal fade" id="modalError" tabindex="-1" aria-labelledby="modalErrorLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header bg-danger text-white">
        <h5 class="modal-title" id="modalErrorLabel">Error</h5>
      </div>
      <div class="modal-body">
        Hubo un problema al guardar el cliente.
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Cerrar</button>
      </div>
    </div>
  </div>
</div>





<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
