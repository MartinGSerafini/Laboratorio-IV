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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Formularios/z-CSS/ABMLClientesCSS/ListadoClientes.css">
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
                    <li><a class="dropdown-item" href="ABMLClientes/AgregarClientes.jsp">CLIENTES</a></li>
          			<li><a class="dropdown-item" href="ABMLCuentas/AgregarCuentas.jsp">CUENTAS</a></li>
                    <li><a class="dropdown-item" href="Informes.jsp">INFORMES</a></li>
                    <li><a class="dropdown-item" href="../Login/IngresoAdministrador.jsp">CERRAR SESIÓN</a></li>
                </ul>
            </div>
        </div>
        <span class="navbar-text text-white">
            <%= session.getAttribute("nombreUsuario") != null ? session.getAttribute("nombreUsuario") : "INVITADO" %>
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
    <form class="d-flex justify-content-center mb-4" method="post" action="${pageContext.request.contextPath}/AutorizacionPrestamosServlet">
        <input type="text" name="busqueda" class="form-control w-25 me-2" placeholder="Buscar...">
        <select name="filtro" id="filtro" class="form-select w-25 me-2" onchange="mostrarOpcionesEstado()">
    		<option value="0" <%= filtroSeleccionado.equals("0") ? "selected" : "" %>>Seleccione un filtro</option>
    		<option value="1" <%= filtroSeleccionado.equals("1") ? "selected" : "" %>>Estado</option>
    		<option value="2" <%= filtroSeleccionado.equals("2") ? "selected" : "" %>>Plazo</option>
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

<!-- Bootstrap Bundle -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<script>
function mostrarOpcionesEstado() {
    const filtro = document.getElementById("filtro").value;
    const contenedor = document.getElementById("estados");

    if (filtro === "1") {
        contenedor.style.display = "block";
    } else {
        contenedor.style.display = "none";
    }
}
</script>

<script>
window.onload = function() {
    mostrarOpcionesEstado(); // Ejecuta esto automáticamente al abrir la página
};
</script>

</body>
</html>
