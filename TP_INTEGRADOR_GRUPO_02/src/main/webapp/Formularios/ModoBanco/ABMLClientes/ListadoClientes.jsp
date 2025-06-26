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
  <link rel="stylesheet" href="${pageContext.request.contextPath}/Formularios/z-CSS/ABMLClientesCSS/ListadoClientes.css">
</head>
<body>
  <!-- Barra de Navegación -->
  <nav class="navbar navbar-expand-lg navbar-custom px-4">
    <div class="container-fluid">
        <div class="d-flex align-items-center">
            <div class="dropdown me-3">
                <button class="btn dropdown-toggle" type="button" data-bs-toggle="dropdown">☰</button>
                <ul class="dropdown-menu">
                    <li><a class="dropdown-item" href="../ABMLCuentas/AgregarCuentas.jsp">CUENTAS</a></li>
                    <li><a class="dropdown-item" href="../AutorizacionPrestamos.jsp">PRÉSTAMOS</a></li>
                    <li><a class="dropdown-item" href="../Informes.jsp">INFORMES</a></li>
                    <li><a class="dropdown-item" href="../../Login/IngresoAdministrador.jsp">CERRAR SESIÓN</a></li>
                </ul>
            </div>
            <a href="${pageContext.request.contextPath}/AgregarClienteServlet" class="btn btn-custom me-2">Agregar Cliente</a>
            <a href="${pageContext.request.contextPath}/ListadoClientesServlet" class="btn btn-custom">Listar Clientes</a>
        </div>
        <span class="navbar-text text-white">
            <%= session.getAttribute("adminLogueado") != null ? session.getAttribute("adminLogueado") : "INVITADO" %>        
        </span>
    </div>
  </nav>

  <div class="container mt-5">
    <h3 class="text-center mb-4 text-danger">Listado de Clientes</h3>

    <div id="mensajeError" class="alert alert-warning d-none text-center" role="alert">
      Por favor, seleccione una columna para aplicar el filtro.
    </div>

    <!-- Busqueda y Filtros -->
    <form class="d-flex justify-content-center mb-4" method="get"
          action="/TP_INTEGRADOR_GRUPO_02/ListadoClientesServlet"
          onsubmit="return validarFiltro()">
      <input type="text" name="busqueda" class="form-control w-25 me-2" placeholder="Buscar...">
      <select name="filtro" class="form-select w-25 me-2" id="filtroSelect">
        <option value="">Seleccione una columna</option>
        <%
          ArrayList<String> columnas = (ArrayList<String>) request.getAttribute("columnas");
          String filtroSeleccionado = (String) request.getAttribute("filtroSeleccionado"); 
          if (columnas != null) {
              for (String col : columnas) {
        %>
        <option value="<%= col %>" <%= col.equals(filtroSeleccionado) ? "selected" : "" %>>
            <%= col.replace("_cliente", "").toUpperCase() %>
        </option>
        <%  }
          }
        %>
      </select>
      <button type="submit" class="btn btn-custom" name="btnbuscar">Buscar</button>
    </form>

    <script>
      function validarFiltro() {
        const filtro = document.getElementById('filtroSelect').value;
        const mensajeError = document.getElementById('mensajeError');
        if (!filtro) {
          mensajeError.classList.remove('d-none');
          return false;
        } else {
          mensajeError.classList.add('d-none');
          return true;
        }
      }
    </script>

<%
  ArrayList<Cliente> lista = null;
  if(request.getAttribute("ListaCli") != null){
      lista = (ArrayList<Cliente>) request.getAttribute("ListaCli");
  }

  int paginaActual = (request.getAttribute("paginaActual") != null) ? (Integer)request.getAttribute("paginaActual") : 1;
  int totalPaginas = (request.getAttribute("totalPaginas") != null) ? (Integer)request.getAttribute("totalPaginas") : 1;
%>

  <!-- Tabla -->
<div class="table-responsive">
  <table class="table table-striped table-bordered text-center">
    <thead class="table-dark">
      <tr>
        <th style="min-width: 120px; white-space: nowrap;">ID Cliente</th>
        <th>DNI</th>
        <th>CUIL</th>
        <th>Nombre</th>
        <th>Apellido</th>
        <th>Sexo</th>
        <th>Nacionalidad</th>
        <th>Nacimiento</th>
        <th>Dirección</th>
        <th>Provincia</th>
        <th>Localidad</th>
        <th>Correo</th>
        <th>Teléfono</th>
        <th>Contraseña</th>
        <th>Acciones</th>
      </tr>
    </thead>
    <tbody>
      <% if(lista != null)
          for(Cliente cli : lista) { %>
      <tr>
        <td style="white-space: nowrap;"><%= cli.getIdCliente() %></td>
        <td><%= cli.getDniCliente() %></td>
        <td><%= cli.getCuilCliente() %></td>
        <td><%= cli.getNombreCliente() %></td>
        <td><%= cli.getApellidoCliente() %></td>
        <td><%= cli.getSexoCliente() %></td>
        <td><%= cli.getNacionalidadCliente() %></td>
        <td><%= cli.getFechaNacCliente() %></td>
        <td><%= cli.getDireccionCliente() %></td>
        <td><%= cli.getProvinciaCliente() %></td>
        <td><%= cli.getLocalidadCliente() %></td>
        <td><%= cli.getCorreoCliente() %></td>
        <td><%= cli.getTelefonoCliente() %></td>
        <td><%= cli.getContrasenaCliente() %></td>
        <td>
          <button class="btn btn-warning btn-sm">Editar</button>
          <button class="btn btn-danger btn-sm"
                  data-bs-toggle="modal"
                  data-bs-target="#confirmarEliminarModal"
                  data-id="<%= cli.getIdCliente() %>">
            Eliminar
          </button>
        </td>
      </tr>
      <% } %>
    </tbody>
  </table>
</div>

<!-- Paginación -->
<nav class="d-flex justify-content-center mt-4">
  <ul class="pagination">
    <li class="page-item <%= (paginaActual == 1) ? "disabled" : "" %>">
      <a class="page-link" href="ListadoClientesServlet?pagina=<%= paginaActual - 1 %>">Anterior</a>
    </li>
    <% for(int i = 1; i <= totalPaginas; i++) { %>
      <li class="page-item <%= (i == paginaActual) ? "active" : "" %>">
        <a class="page-link" href="ListadoClientesServlet?pagina=<%= i %>"><%= i %></a>
      </li>
    <% } %>
    <li class="page-item <%= (paginaActual == totalPaginas) ? "disabled" : "" %>">
      <a class="page-link" href="ListadoClientesServlet?pagina=<%= paginaActual + 1 %>">Siguiente</a>
    </li>
  </ul>
</nav>

<!-- Modal de confirmación al eliminar un registro-->
<div class="modal fade" id="confirmarEliminarModal" tabindex="-1" aria-labelledby="confirmarEliminarLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header bg-danger text-white">
        <h5 class="modal-title" id="confirmarEliminarLabel">Confirmar Eliminación</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Cerrar"></button>
      </div>
      <div class="modal-body">
        ¿Estás seguro que deseas eliminar este cliente?
      </div>
      <div class="modal-footer">
        <form id="formEliminar" method="post" action="/TP_INTEGRADOR_GRUPO_02/EliminarClienteServlet">
          <input type="hidden" name="idCliente" id="idClienteEliminar">
          <button type="submit" class="btn btn-danger">Sí, eliminar</button>
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
        </form>
      </div>
    </div>
  </div>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<!-- Script para cargar el ID del cliente en el modal -->
<script>
  const modalEliminar = document.getElementById('confirmarEliminarModal');
  modalEliminar.addEventListener('show.bs.modal', function (event) {
    const boton = event.relatedTarget;
    const idCliente = boton.getAttribute('data-id');
    const inputHidden = modalEliminar.querySelector('#idClienteEliminar');
    inputHidden.value = idCliente;
  });
</script>

</body>
</html>
