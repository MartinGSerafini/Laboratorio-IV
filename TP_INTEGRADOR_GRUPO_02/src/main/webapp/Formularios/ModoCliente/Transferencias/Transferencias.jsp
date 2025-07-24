<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entidades.*" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Transferencias</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/Formularios/z-CSS/ModoClienteCSS/Transferencias/Transferencias.css">

    <script>
        function toggleSidebar() {
            const sidebar = document.getElementById("sidebar");
            sidebar.style.display = sidebar.style.display === "block" ? "none" : "block";
        }
    </script>
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

<!-- CONTENIDO -->
<div class="container mt-5">
    <h3 class="text-center mb-4 text-danger">Transferencias</h3>
    
      <!-- MENSAJES JSP -->
		<% if (request.getAttribute("mensaje") != null) { %>
		    <div class="alert alert-success text-center mt-3">
		        <%= request.getAttribute("mensaje") %>
		    </div>
		<% } %>
		
		<% if (request.getAttribute("error") != null) { %>
		    <div class="alert alert-danger text-center mt-3">
		        <%= request.getAttribute("error") %>
		    </div>
		<% } %>

    <!-- Busqueda y Filtros -->
    <form class="d-flex justify-content-center mb-4" action="${pageContext.request.contextPath}/TransferenciasClienteServlet" method="get">
        <input type="text" name="cbuInput" class="form-control w-25 me-2" placeholder="Ingrese CBU">

        <button type="submit" class="btn btn-custom">Buscar Cuenta</button>
    </form>

    <!-- Tabla de Cuentas -->
    
    <%
	Cuenta cuenta = (Cuenta)request.getAttribute("cuenta");
	boolean mostrarTabla = (cuenta != null && cuenta.getCbuCuenta() != null);
	%>
   
	
	<%String descTipoCuenta = (String) request.getAttribute("descTipoCuenta");%>
	<%String nombre = (String) request.getAttribute("nombre");%>
	<%String apellido = (String) request.getAttribute("apellido");%>
	
	
	
    
    <% if (mostrarTabla) { %>
    <div class="table-responsive d-flex justify-content-center">
        <table class="table table-striped table-bordered text-center w-auto">
            <thead class="table-dark">
                <tr>
                	<th>Nombre</th><th>Apellido</th><th>Nro Cuenta</th><th>Tipo</th><th>CBU</th><th>Acciones</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td><%= nombre%></td><td><%= apellido%></td><td><%= cuenta.getNumeroCuenta()%></td><td><%= descTipoCuenta %></td><td><%= cuenta.getCbuCuenta()%></td>
                <td>
                	<button class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#modalTransferir"  data-cbu="<%= cuenta.getCbuCuenta() %>">Transferir</button>
                </td>
              </tr>
            </tbody>
        </table>
    </div>
	<% } %>
	
	
    <!-- Paginación -->
    <nav class="d-flex justify-content-center mt-4">
        <ul class="pagination">
        </ul>
    </nav>
</div>



<!-- MODAL TRANSFERIR -->
<div class="modal fade" id="modalTransferir" tabindex="-1" aria-labelledby="modalTransferirLabel" aria-hidden="true">
  <div class="modal-dialog modal-md modal-dialog-centered">
    <div class="modal-content">
      <form action="${pageContext.request.contextPath}/TransferenciasClienteServlet" method="post">
	      <input type="hidden" name="cbuDestino" id="inputCbuDestino">
	      <input type="hidden" name="cbuOrigen" id="inputCbuOrigen">
	      
	      <div class="modal-header encabezado-modal">
	        <h5 class="modal-title" id="modalTransferirLabel">Transferencia</h5>
	        <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Cerrar"></button>
	      </div>
	      <div class="modal-body">
	
	
	        <div class="mb-3">
	          <label for="selectCuentaOrigen" class="form-label fw-bold">Transferir desde:</label>
	          <select class="form-select" id="selectCuentaOrigen" name="cuentaOrigen">
	            <option selected disabled>Seleccionar cuenta...</option>
	            <% 
	              ArrayList<Cuenta> cuentasCliente = (ArrayList<Cuenta>) request.getAttribute("cuentasCliente");
	              if (cuentasCliente != null) {
	                for (Cuenta c : cuentasCliente) {
	            %>
	                <option 
					  value="<%= c.getIdCuenta() %>" 
					  data-saldo="<%= c.getSaldoCuenta() %>" 
					  data-cbuOrigen="<%= c.getCbuCuenta() %>">
					  <%= c.getNumeroCuenta() %>. <%= c.getTipoCuentaCuenta() %>
					</option>
	                
	            <%
	                }
	              }
	            %>
	          </select>
	        </div>
	
	        <!-- Dinero Disponible -->
	        <div class="mb-3">
	          <label class="form-label fw-bold">Dinero disponible:</label>
	          <p class="dinero-disponible">$ <span id="dineroDisponible">--</span></p>
	        </div>
			<script>
			  document.addEventListener('DOMContentLoaded', function () {
			    const select = document.getElementById('selectCuentaOrigen');
			    const saldoSpan = document.getElementById('dineroDisponible');
			
			    function actualizarSaldo() {
			      const selectedOption = select.options[select.selectedIndex];
			      const saldo = selectedOption.getAttribute('data-saldo');
			      saldoSpan.textContent = saldo ? saldo : '--';
			    }
			
			    select.addEventListener('change', actualizarSaldo);
			
			
			    const selectedOption = select.options[select.selectedIndex];
			    const saldoInicial = selectedOption.getAttribute('data-saldo');
			    if (saldoInicial) {
			      saldoSpan.textContent = saldoInicial;
			    }
			  });
			</script>
			
		
	        <!-- Ingreso de Monto -->
	        <div class="mb-3">
	          <label for="montoTransferencia" class="form-label">Ingrese el monto a transferir:</label>
	          <input type="text" class="form-control" id="montoTransferencia" name="monto">
	        </div>
	
	        <!-- Botón Transferir -->
	        <div class="text-center">
	          <button type="submit" class="btn-transferencia">Realizar Transferencia</button>
	        </div>
	
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
	      </div>
	      
      </form>>
    </div>
  </div>
</div>


<script>
  document.addEventListener('DOMContentLoaded', function () {
    const modal = document.getElementById('modalTransferir');
    const inputCbuDestino = document.getElementById('inputCbuDestino');
    const inputCbuOrigen = document.getElementById('inputCbuOrigen');
    const select = document.getElementById('selectCuentaOrigen');
    const saldoSpan = document.getElementById('dineroDisponible');


    const botones = document.querySelectorAll('[data-bs-target="#modalTransferir"]');
    botones.forEach(btn => {
      btn.addEventListener('click', function () {
        const cbuDestino = this.getAttribute('data-cbu');
        inputCbuDestino.value = cbuDestino;
      });
    });


    function actualizarDatosOrigen() {
      const selectedOption = select.options[select.selectedIndex];
      const saldo = selectedOption.getAttribute('data-saldo');
      const cbuOrigen = selectedOption.getAttribute('data-cbuOrigen');

      saldoSpan.textContent = saldo || '--';
      inputCbuOrigen.value = cbuOrigen || '';
    }

    select.addEventListener('change', actualizarDatosOrigen);


    if (select.selectedIndex > 0) {
      actualizarDatosOrigen();
    }
  });
</script>






<!-- Bootstrap Bundle -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
