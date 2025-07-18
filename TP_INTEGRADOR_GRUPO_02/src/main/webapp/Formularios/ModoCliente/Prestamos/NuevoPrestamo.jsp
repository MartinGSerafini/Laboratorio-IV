<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.LinkedHashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.List" %>
<%@ page import="entidades.Cuenta" %>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <title>Nuevo prestamo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Formularios/z-CSS/ModoClienteCSS/Prestamos/Prestamos.css">
        
    <script>
        function toggleSidebar() {
            const sidebar = document.getElementById("sidebar");
            sidebar.style.display = sidebar.style.display === "block" ? "none" : "block";
        }
    </script>
</head>

<nav class="navbar navbar-expand-lg navbar-custom px-4">
    <div class="container-fluid">
        <div class="d-flex align-items-center">
            <div class="dropdown me-3">
                <button class="btn dropdown-toggle" type="button" data-bs-toggle="dropdown">
                    ☰
                </button>      
                <ul class="dropdown-menu">
           			 <li><a class="dropdown-item" href="${pageContext.request.contextPath}/InfoPersonalClienteServlet">Informacion Personal</a></li>
           			 <li><a class="dropdown-item" href="${pageContext.request.contextPath}/CuentasClienteServlet">Cuentas</a></li>
           			 <li><a class="dropdown-item" href="${pageContext.request.contextPath}/TransferenciasClienteServlet">Transferir Dinero</a></li>
           			 <li><a class="dropdown-item" href="${pageContext.request.contextPath}/NuevoPrestamoServlet">Nuevo Prestamo</a></li>
           			 <li><a class="dropdown-item" href="${pageContext.request.contextPath}/PagarPrestamoServlet">Pagar Prestamo</a></li>
					 <li><a class="dropdown-item" href="${pageContext.request.contextPath}/Formularios/Login/InicioUsuario.jsp">Cerrar Sesion</a></li>
				</ul>
            </div>
        </div>
        <span class="navbar-text text-white">
        <%= session.getAttribute("ClienteLogueado") != null ? session.getAttribute("ClienteLogueado") : "INVITADO" %>
      </span>
    </div>
</nav>


<!-- prestamos disponibles -->
<div class="main-content">
    <h2>Prestamos disponibles</h2>
    <div class="button-grid">
    	<form action="${pageContext.request.contextPath}/NuevoPrestamoServlet" method="get">
            <input type="hidden" name="accion" value="seleccionarPrestamo">
            <input type="hidden" name="monto" value="80000">
            <button type="submit" class="btn">Prestamo 1 por $80.000</button>
        </form>
        <form action="${pageContext.request.contextPath}/NuevoPrestamoServlet" method="get">
            <input type="hidden" name="accion" value="seleccionarPrestamo">
            <input type="hidden" name="monto" value="90000">
            <button type="submit" class="btn">Prestamo 2 por $90.000</button>
        </form>
        <form action="${pageContext.request.contextPath}/NuevoPrestamoServlet" method="get">
            <input type="hidden" name="accion" value="seleccionarPrestamo">
            <input type="hidden" name="monto" value="100000">
            <button type="submit" class="btn">Prestamo 3 por $100.000</button>
        </form>
        <form action="${pageContext.request.contextPath}/NuevoPrestamoServlet" method="get">
            <input type="hidden" name="accion" value="seleccionarPrestamo">
            <input type="hidden" name="monto" value="120000">
            <button type="submit" class="btn">Prestamo 4 por $120.000</button>
        </form>

    </div>
</div>

<!-- cuotas disponibles -->
<div class="main-content" id="cuotasSection" 
    <% if (request.getAttribute("cuotasDisponibles") == null) { %>
        style="display: none;"
    <% } %>
>
    <h2>Cuotas disponibles para el préstamo </h2>
    <div class="button-grid">
        <% 
            LinkedHashMap<Integer, Double> cuotas = (LinkedHashMap<Integer, Double>) request.getAttribute("cuotasDisponibles");
            if (cuotas != null) {
                NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("es", "AR"));
                for (Map.Entry<Integer, Double> entry : cuotas.entrySet()) {
                	 int numeroCuotas = entry.getKey();
                     double valorCuota = entry.getValue();
         %>
                     <a href="#" class="btn" onclick="mostrarCuentas()">Pagar en <%= numeroCuotas %> cuotas de <%= formatter.format(valorCuota) %></a>
         <%
                 }
             }
         %>
      </div>
</div>

<!-- Cuentas Disponibles -->
<div class="container mt-5" id="cuentasSection" style="display: none;">
    <h3 class="text-center mb-4 text-danger">Cuenta a seleccionar</h3>
    <!-- Tabla de Cuentas -->
    <div class="table-responsive d-flex justify-content-center">
        <table class="table table-striped table-bordered text-center w-auto">
            <thead class="table-light">
                <tr>
                    <th>Nro Cuenta</th><th>Tipo</th>
                    <th>CBU</th><th>Saldo</th><th> </th>
                </tr>
            </thead>
            <tbody>
			    <%
			        List<Cuenta> listaCuentas = (List<Cuenta>) request.getAttribute("cuentas");
			        if (listaCuentas != null && !listaCuentas.isEmpty()) {
			            for (Cuenta c : listaCuentas) {
			    %>
			        <tr>
			            <td><%= c.getNumeroCuenta() %></td>
			            <td><%= c.getTipoCuentaCuenta() %></td>
			            <td><%= c.getCbuCuenta() %></td>
			            <td>$<%= c.getSaldoCuenta() %></td>
			            <td><button class="btn btn-success btn-sm">Usar</button></td>
			        </tr>
			    <%
			            }
			        } else {
			    %>
			        <tr>
			            <td colspan="5">No se encontraron cuentas para mostrar.</td>
			        </tr>
			    <%
			        }
			    %>
			</tbody>
        </table>
    </div>

    <!-- Paginación -->
    <nav class="d-flex justify-content-center mt-4">
        <ul class="pagination">
        </ul>
    </nav>
</div>

<!-- Script para manejar la visibilidad de botones y tabla de cuentas -->
<script>
    function mostrarCuotas() {
        document.getElementById("cuotasSection").style.display = "block";
        document.getElementById("cuentasSection").style.display = "none";
    }

    function mostrarCuentas() {
        document.getElementById("cuentasSection").style.display = "block";
    }
</script>

<!--  
<div class="main-content">
        <a href="#" class="btn">Aceptar</a>
        <a href="#" class="btn">Cancelar</a>
</div>
-->

<div class="main-content">
	<form id="datosPrestamo" action="${pageContext.request.contextPath}/NuevoPrestamoServlet" method="post">
	    <input type="hidden" name="accion" value="Aceptar">
	    <input type="hidden" name="cuentaSeleccionada" id="cuentaSeleccionada">
	    <input type="hidden" name="montoPrestamo" value="${monto}">
	    <input type="hidden" name="cuotasSeleccionadas" id="cuotasSeleccionadas">
	    <input type="hidden" name="montoCuotaSeleccionada" id="montoCuotaSeleccionada">
	    
	    <button type="button" class="btn" onclick="abrirModalConfirmacion()" >Aceptar</button>
	    <a href="${pageContext.request.contextPath}/MenuClienteServlet" class="btn">Cancelar</a>
	</form>
</div>

<!-- Modal Confirmación -->
<div class="modal fade" id="modalConfirmacion" tabindex="-1" aria-labelledby="modalConfirmacionLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header bg-danger text-white">
        <h5 class="modal-title" id="modalErrorLabel">Confirmar Prestamo</h5>
      </div>
      <div class="modal-body">
			¿Desea confirmar la solicitud del préstmao?
      </div>
      <div class="modal-footer">
			<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
        	<button type="button" class="btn btn-danger" onclick="confirmarPrestamo()">Aceptar</button>
      </div>
    </div>
  </div>
</div>

<!-- Modal Éxito -->
<div class="modal fade" id="modalExito" tabindex="-1" aria-labelledby="modalExitoLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header bg-success text-white">
        <h5 class="modal-title" id="modalExitoLabel">Éxito</h5>
      </div>
      <div class="modal-body">
			Prestamo solicitado con éxito.
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-success" data-bs-dismiss="modal" onclick="document.getElementById('formReset').submit();">Aceptar</button>
      </div>
    </div>
  </div>
</div>

<!-- Script para manejar el modal de confirmacion -->
<script>
    function abrirModalConfirmacion() {
        document.getElementById("modalConfirmacion").style.display = "block";
    }

    function confrimarPrestamo() {
        document.getElementById("datosPrestamo").submit();
    }
</script>


<!-- Bootstrap Bundle -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<body>
</html>