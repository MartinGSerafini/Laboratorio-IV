<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="entidades.*" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Agregar Cuenta</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Formularios/z-CSS/ABMLCuentasCSS/AgregarCuentas.css">
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
          <li><a class="dropdown-item" href="${pageContext.request.contextPath}/AgregarClienteServlet">CLIENTES</a></li>
    		<li><a class="dropdown-item" href="${pageContext.request.contextPath}/AgregarCuentaServlet">CUENTAS</a></li>
    		<li><a class="dropdown-item" href="${pageContext.request.contextPath}/AutorizacionPrestamosServlet">PRÉSTAMOS</a></li>
    		<a class="dropdown-item" href="${pageContext.request.contextPath}/Formularios/ModoBanco/Informes/Informes.jsp">INFORMES</a>
    		<a class="dropdown-item" href="${pageContext.request.contextPath}/Formularios/Login/IngresoAdministrador.jsp">CERRAR SESIÓN</a>
			</ul>
            </div>
            <a href="${pageContext.request.contextPath}/AgregarCuentaServlet" class="btn btn-custom me-2">Agregar Cuenta</a>
        	<a href="${pageContext.request.contextPath}/ListarCuentas" class="btn btn-custom">Listar Cuentas</a>
        </div>
        <span class="navbar-text text-white">
        <%= session.getAttribute("adminLogueado") != null ? session.getAttribute("adminLogueado") : "INVITADO" %>
      </span>
    </div>
</nav>

<div class="container mt-5">
    <h2 class="text-center mb-4 text-danger">Agregar Cuentas</h2>
    <form class="row g-4" action="/TP_INTEGRADOR_GRUPO_02/AgregarCuentaServlet" method="post">
        <div class="col-md-6">
            <label for="nroCuenta" class="form-label">Numero de Cuenta</label>
            <input type="text" class="form-control" id="nroCuenta" name="nroCuenta" 
                   value="<%= request.getAttribute("nroCuenta") != null ? request.getAttribute("nroCuenta") : "" %>">
        </div>
        <div class="col-md-6">
            <label for="tipo_Cuenta" class="form-label">Tipo de Cuenta</label>
            <select class="form-select" id="tipo_Cuenta" name="tipo_Cuenta">
                <option value="">Seleccione</option>
                <%
                    ArrayList<TipoCuenta> tipoCuentas = (ArrayList<TipoCuenta>) request.getAttribute("listaTipos");
                    String tipoCuentaSeleccionada = (String) request.getAttribute("tipo_Cuenta");
                    if (tipoCuentas != null) {
                        for (TipoCuenta n : tipoCuentas) {
                            boolean selected = tipoCuentaSeleccionada != null && tipoCuentaSeleccionada.equals(String.valueOf(n.getIdtipoCuenta()));
                %>
                    <option value="<%= n.getIdtipoCuenta() %>" <%= selected ? "selected" : "" %>><%= n.getDescripcionTipoCuenta() %></option>
                <%
                        }
                    }
                %>
            </select>
        </div>
        <div class="col-md-6">
            <label for="cuil" class="form-label">CBU</label>
            <input type="text" class="form-control" id="cuil" name="cuil" 
                   value="<%= request.getAttribute("cuil") != null ? request.getAttribute("cuil") : "" %>">
        </div>
        <div class="col-md-6">
            <label for="dniCliente" class="form-label">DNI Cliente</label>
            <input type="text" class="form-control" id="dniCliente" name="dniCliente" 
                   value="<%= request.getAttribute("dniCliente") != null ? request.getAttribute("dniCliente") : "" %>">
        </div>
        <div class="col-12 text-center mt-4">
            <button type="submit" name="accion" value="agregarCuenta" class="btn btn-custom px-5 py-2">Agregar Cuenta</button>
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
        Cuenta agregada con éxito.
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-success" onclick="location.href='${pageContext.request.contextPath}/AgregarCuentaServlet'">Aceptar</button>
        
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
        Hubo un problema al crear la cuenta.
      </div>
      <div class="modal-footer">
       <button type="button" class="btn btn-danger" onclick="location.href='${pageContext.request.contextPath}/AgregarCuentaServlet'">Cerrar</button>
       
      </div>
    </div>
  </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
