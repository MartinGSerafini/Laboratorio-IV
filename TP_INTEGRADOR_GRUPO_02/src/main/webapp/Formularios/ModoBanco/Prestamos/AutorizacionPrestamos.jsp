<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="entidades.Prestamo, entidades.EstadoPrestamo"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Prestamos solicitados</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Formularios/z-CSS/ModoBancoCSS\ABMLClientesCSS\ListadoClientes.css">
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
  
  
<!-- CONTENIDO -->

<div class="container mt-5">
    <h3 class="text-center mb-4 text-danger">Prestamos solicitados</h3>

    <!-- Busqueda y Filtros -->
    <%
		String filtroSeleccionado = request.getParameter("filtro") != null ? request.getParameter("filtro") : "0";
	%>
    <form class="d-flex justify-content-center mb-4" method="get" action="${pageContext.request.contextPath}/AutorizacionPrestamosServlet">
        <input type="text" name="busqueda" id="busqueda" class="form-control w-25 me-2" placeholder="Buscar...">
        <select name="filtro" id="filtro" class="form-select w-25 me-2" onchange="mostrarOpciones()">
        	<option value="0" <%= filtroSeleccionado.equals("0") ? "selected" : "" %>>Seleccione un filtro</option>
        	<%
    		ArrayList<String> listaFiltros = (ArrayList<String>) request.getAttribute("ListaFiltros");
        	if(listaFiltros != null){
        		for(String f : listaFiltros){
        		%>
        			<option value="<%=f %>" <%= f.equals(filtroSeleccionado) ? "selected" : "" %>>
        			<%= f.replace("_pres", "").replace("tamo", "").replaceAll("([a-z])([A-Z])", "$1 $2").toUpperCase()
        			%>
        </option>
        		<%}
        	}
        	%>
		</select>
    	<div id="estados" name="filtroEstados" class="me-2" style="display: none;">
    	<select name="estadoSeleccionado" class="form-select">
        	<option value="0" selected>Seleccione estado</option>
        	<%
        	ArrayList<EstadoPrestamo> listaEstados = (ArrayList<EstadoPrestamo>) request.getAttribute("ListaEstados");
        	if(listaEstados != null){
            	for(EstadoPrestamo e : listaEstados){ %>
                	<option value="<%=e.getId()%>"><%=e.getDescripcion()%></option>
        	<% } } %>
    	</select>
	</div>
    	<button type="submit" name="btnBuscar" class="btn btn-custom">Buscar</button>
    	<div id="quitarFiltro" name="quitarFiltro" class="me-2" style="display: none;">
    		<button type="submit" name="btnquitarFiltro" onclick="quitarFiltro()" class="btn btn-custom ms-2">Quitar filtro</button>
    	</div>
    	</form>
    
<%
	ArrayList<Prestamo> lista = null;
	if(request.getAttribute("ListaPrestamos") !=null){
		  lista = (ArrayList<Prestamo>) request.getAttribute("ListaPrestamos");
	}
%>

    <!-- Tabla de Cuentas -->
    <div class="table-responsive d-flex justify-content-center">
        <table class="table table-striped table-bordered text-center w-auto">
            <thead class="table-dark">
                <tr>
                    <th>ID Cuenta</th><th>ID Cliente</th><th>Fecha</th><th>Importe a pagar</th><th>Prestamo solicitado</th>
                    <th>Plazo de pago en meses</th><th>Monto mensual</th>><th>Estado</th>
                </tr>
            </thead>
            <tbody>
            <%
            if(lista != null){
            	for(Prestamo p : lista){
            		%>
            		<tr>
            		<td><%=p.getIdCuentaDepositoPres() %></td>
            		<td><%=p.getIdClientePres() %></td>
            		<td><%=p.getFechaSolicitudPres() %></td>
            		<td>$<%=p.getImporteTotalPres() %></td>
            		<td>$<%=p.getImporteSolicitadoPres()%></td>
            		<td><%=p.getPlazoMesesPres() %></td>
            		<td>$<%=p.getMontoCuotaPres() %></td>
            		<%
            		if(p.getEstadoPres().getId()==3){
            			%>
            			<td>
            			<form method="get" action="${pageContext.request.contextPath}/AutorizacionPrestamosServlet">
            			<input type="hidden" name="idPrestamo" value="<%= p.getIdPrestamo() %>">
                        <button type="submit" name="autorizar" class="btn btn-warning btn-sm">Autorizar</button>
                        <button type="submit" name="rechazar" class="btn btn-danger btn-sm">Rechazar</button>
                    </form>
                    </td>
            		<%}else{%>
            			<td><%=p.getEstadoPres().getDescripcion() %></td>
            		<%}
            		%>
            		</tr>
            	<% }
            }
            else{
            	
            }
            %>
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


<script>
function mostrarOpciones() {
    const filtro = document.getElementById("filtro").value;
    const estados = document.getElementById("estados");
    const txtBusqueda = document.getElementById("busqueda");
    const quitarFiltro = document.getElementById("quitarFiltro");
	
    if (filtro !== "0"){
    	quitarFiltro.style.display = "block";
    	if (filtro === "estado_pres") {
        	estados.style.display = "block";
        	txtBusqueda.placeholder = "Buscar...";
    	} else if (filtro === "plazoMeses_pres") {
        	estados.style.display = "none";
        	txtBusqueda.placeholder = "Ingrese un plazo";
    	} else if (filtro === "idCliente_pres") {
        	estados.style.display = "none";
        	txtBusqueda.placeholder = "Ingrese ID de cliente";
    	} else if (filtro === "idCuentaDeposito_pres") {
        	estados.style.display = "none";
        	txtBusqueda.placeholder = "Ingrese ID de cuenta";
    	} else if (filtro === "importeSolicitado_pres") {
        	estados.style.display = "none";
        	txtBusqueda.placeholder = "Ingrese un monto (sin comas ni puntos)";
    	} else {
        	estados.style.display = "none";
        	txtBusqueda.placeholder = "Buscar...";
    	}
    }
}
</script>

<script>
window.onload = function() {
    mostrarOpciones(); // Ejecuta esto automáticamente al abrir la página
    
 // Mostrar modal solo si hay errores
    <% if (request.getAttribute("errores") != null) { %>
        let modal = new bootstrap.Modal(document.getElementById('modalError'));
        modal.show();
    <% } %>
};
</script>

<script>
function quitarFiltro() {
    const filtro = document.getElementById("filtro");
    const txtBusqueda = document.getElementById("busqueda");
    const estados = document.getElementById("estados");
    const quitarFiltro = document.getElementById("quitarFiltro");

    filtro.value = "0";
    txtBusqueda.value = "";
    txtBusqueda.placeholder = "Buscar...";
    estados.style.display = "none";
    quitarFiltro.style.display = "none";
}
</script>

<div class="modal fade" id="modalError" tabindex="-1" aria-labelledby="modalError" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header bg-danger text-white">
        <h5 class="modal-title" id="modalError">Error</h5>
      </div>
      <div class="modal-body">
        <% 
            String errores = (String) request.getAttribute("errores");
            if (errores != null && !errores.isEmpty()) {
        %>
                    <p>INGRESE DATO VALIDO</p>
        <% 
                
            } 
        %>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-warning" data-bs-dismiss="modal">Cerrar</button>
      </div>
    </div>
  </div>
 </div>

<!-- Bootstrap Bundle -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
