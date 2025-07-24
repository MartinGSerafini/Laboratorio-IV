<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entidades.ProvinciaCantidad" %>
<%@ page import="entidades.GeneroClientes" %>
<%@ page import="entidades.MovimientoReporte" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Informes</title>
  <link href="https://fonts.googleapis.com/css2?family=Montserrat&display=swap" rel="stylesheet">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath}/Formularios/z-CSS/ModoBancoCSS/InformesCSS/Informes.css">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-custom px-4">
  <div class="container-fluid">
    <div class="d-flex align-items-center">
      <div class="dropdown me-3">
        <button class="btn dropdown-toggle" type="button" data-bs-toggle="dropdown">☰</button>
        <ul class="dropdown-menu">
          <li><a class="dropdown-item" href="${pageContext.request.contextPath}/AgregarClienteServlet">CLIENTES</a></li>
    		<li><a class="dropdown-item" href="${pageContext.request.contextPath}/AgregarCuentaServlet">CUENTAS</a></li>
    		<li><a class="dropdown-item" href="${pageContext.request.contextPath}/AutorizacionPrestamosServlet">PRÉSTAMOS</a></li>
    		<a class="dropdown-item" href="${pageContext.request.contextPath}/InformesServlet">INFORMES</a>
    		<a class="dropdown-item" href="${pageContext.request.contextPath}/Formularios/Login/IngresoAdministrador.jsp">CERRAR SESIÓN</a>
			</ul>
      </div>
    </div>
    <span class="navbar-text text-white">
        <%= session.getAttribute("adminLogueado") != null ? session.getAttribute("adminLogueado") : "INVITADO" %>
      </span>
  </div>
</nav>

<div class="container mt-5">
  <h3 class="text-center mb-4 text-danger">Informes</h3>

  <div class="d-flex justify-content-center flex-wrap gap-3 mb-5">
    <button class="btn btn-custom" data-bs-toggle="modal" data-bs-target="#modalInformeN1">Informe N°1</button>
    <button class="btn btn-custom" data-bs-toggle="modal" data-bs-target="#modalInformeN2">Informe N°2</button>
    <button class="btn btn-custom" data-bs-toggle="modal" data-bs-target="#modalInformeN3">Reporte N°3</button>
  </div>
</div>

<!-- Modal Informe 1 -->
<div class="modal fade" id="modalInformeN1" tabindex="-1">
  <div class="modal-dialog modal-lg modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header bg-danger text-white">
        <h5 class="modal-title">Informe N°1 - Provincias con más clientes</h5>
        <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal"></button>
      </div>
      <div class="modal-body">
        <ul class="list-group">
          <%
            ArrayList<ProvinciaCantidad> provinciasTop = (ArrayList<ProvinciaCantidad>) request.getAttribute("topProvincias");
            if (provinciasTop != null && !provinciasTop.isEmpty()) {
              for (ProvinciaCantidad pc : provinciasTop) {
          %>
          <li class="list-group-item">
            <strong><%= pc.getNombreProvincia() %></strong>: <%= pc.getCantidad() %> clientes
          </li>
          <% }} else { %>
          <li class="list-group-item">No se encontraron datos para este informe.</li>
          <% } %>
        </ul>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
      </div>
    </div>
  </div>
</div>

<!-- Modal Informe 2 -->
<div class="modal fade" id="modalInformeN2" tabindex="-1">
  <div class="modal-dialog modal-lg modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header bg-danger text-white">
        <h5 class="modal-title">Informe N°2 - Distribución por género</h5>
        <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal"></button>
      </div>
      <div class="modal-body">
        <ul class="list-group">
          <%
            ArrayList<GeneroClientes> generoClientes = (ArrayList<GeneroClientes>) request.getAttribute("generoClientes");
            if (generoClientes != null && !generoClientes.isEmpty()) {
              for (GeneroClientes g : generoClientes) {
          %>
          <li class="list-group-item">
            <strong><%= g.getGenero() %></strong>: <%= g.getCantidad() %> clientes
          </li>
          <% }} else { %>
          <li class="list-group-item">No se encontraron datos para este informe.</li>
          <% } %>
        </ul>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
      </div>
    </div>
  </div>
</div>

<!-- Modal Informe 3 -->
<div class="modal fade" id="modalInformeN3" tabindex="-1">
  <div class="modal-dialog modal-lg modal-dialog-centered">
    <div class="modal-content">
      <form action="InformesServlet" method="get">
        <div class="modal-header bg-danger text-white">
          <h5 class="modal-title">Reporte N°1 - Movimientos entre fechas</h5>
          <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal"></button>
        </div>
        <div class="modal-body">
          <div class="row mb-3">
            <div class="col-md-6">
              <label for="fechaInicio" class="form-label">Fecha Inicio</label>
              <input type="date" class="form-control" name="fechaInicio" required
                     value="<%= request.getParameter("fechaInicio") != null ? request.getParameter("fechaInicio") : "" %>">
            </div>
            <div class="col-md-6">
              <label for="fechaFin" class="form-label">Fecha Fin</label>
              <input type="date" class="form-control" name="fechaFin" required
                     value="<%= request.getParameter("fechaFin") != null ? request.getParameter("fechaFin") : "" %>">
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <button type="submit" name="accion" value="reporteMovimientos" class="btn btn-danger">Consultar</button>
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
        </div>
      </form>

      <% 
        ArrayList<MovimientoReporte> movimientos = (ArrayList<MovimientoReporte>) request.getAttribute("listaMovimientos");
        if (movimientos != null && !movimientos.isEmpty()) {
      %>
      <div class="px-4 pb-4">
        <h5 class="mt-3">Movimientos encontrados:</h5>
        <div class="table-responsive">
          <table class="table table-striped text-center">
            <thead class="table-dark">
              <tr>
                <th>Detalle</th>
                <th>Importe</th>
                <th>Fecha</th>
              </tr>
            </thead>
            <tbody>
              <% for (MovimientoReporte mov : movimientos) { %>
              <tr>
                <td><%= mov.getDetalleMov() %></td>
                <td><%= mov.getImporteMov() %></td>
                <td><%= mov.getFechaMov() %></td>
              </tr>
              <% } %>
            </tbody>
          </table>
        </div>
      </div>
      <% 
        } else if (request.getAttribute("mostrarModalInforme3") != null && (boolean) request.getAttribute("mostrarModalInforme3")) {
      %>
      <div class="px-4 pb-4">
        <p class="text-warning mt-3">No se encontraron movimientos en el rango seleccionado.</p>
      </div>
      <% } %>

    </div>
  </div>
</div>

<% if (request.getAttribute("mostrarModalInforme3") != null && (boolean) request.getAttribute("mostrarModalInforme3")) { %>
<script>
  window.onload = () => {
    let modal = new bootstrap.Modal(document.getElementById('modalInformeN3'));
    modal.show();
  };
</script>
<% } %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
