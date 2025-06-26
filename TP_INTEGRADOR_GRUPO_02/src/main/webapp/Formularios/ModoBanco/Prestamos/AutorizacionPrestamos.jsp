<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList"%>
<%@ page import="entidades.Prestamo"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Prestamos solicitados</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../z-CSS/ABMLCuentasCSS/ListarCuentas.css">
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
    <form class="d-flex justify-content-center mb-4" method="get" action="ListarCuentas.jsp">
        <input type="text" name="busqueda" class="form-control w-25 me-2" placeholder="Buscar...">
        <select name="filtro" class="form-select w-25 me-2">
            <option selected>Seleccione un filtro</option>
        </select>
        <button type="submit" class="btn btn-custom">Buscar</button>
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
           <!--     <tr>
                    <td>0001</td><td>0001</td><td>11/01/1111</td><td>1.000.000</td><td>2.000.000</td>
                    <td>30 días</td><td>100.000</td><td>20 cuotas</td>
                    <td>
                        <button class="btn btn-warning btn-sm" id=1>Autorizar prestamo</button>
                        <button class="btn btn-danger btn-sm" id=2>Rechazar prestamo</button>
                    </td>
                </tr> --> 
            <%
            if(lista != null){
            	for(Prestamo p : lista){
            		%>
            		<tr>
            		<td><%=p.getIdCuentaDepositoPres() %></td><td><%=p.getIdClientePres() %></td><td><%=p.getFechaSolicitudPres() %></td>
            		<td>$<%=p.getImporteTotalPres() %></td><td>$<%=p.getImporteSolicitadoPres()%></td><td><%=p.getPlazoMesesPres() %></td>
            		<td>$<%=p.getMontoCuotaPres() %></td>
            		<%
            		if(p.getEstadoPres().getId()==3){
            			%>
            			<td>
                        <button class="btn btn-warning btn-sm" id=1>Autorizar prestamo</button>
                        <button class="btn btn-danger btn-sm" id=2>Rechazar prestamo</button>
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
</body>
</html>
