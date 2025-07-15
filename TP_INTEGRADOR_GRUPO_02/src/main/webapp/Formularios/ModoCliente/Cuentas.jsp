<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="entidades.*" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cuentas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Formularios/z-CSS/ModoClienteCSS/Cuentas.css">

    <script>
        function toggleSidebar() {
            const sidebar = document.getElementById("sidebar");
            sidebar.style.display = sidebar.style.display === "block" ? "none" : "block";
        }
    </script>
</head>
<body>
<div class="navbar">
    <div class="menu-toggle" onclick="toggleSidebar()">☰</div>
    <div class="user-info">
        <%= session.getAttribute("nombreUsuario") != null ? session.getAttribute("nombreUsuario") : "INVITADO" %>
    </div>
</div>

<div id="sidebar" class="sidebar">
    <a href="${pageContext.request.contextPath}/InfoPersonalClienteServlet">Informacion Personal</a>
    <a href="${pageContext.request.contextPath}/TransferenciasClienteServlet">Transferir Dinero</a>
    <a href=Prestamos/NuevoPrestamo.jsp>Nuevo Prestamo</a>
    <a href="Prestamos/PagarPrestamo.jsp">Pagar Prestamos</a>
    <a href="${pageContext.request.contextPath}/Formularios/Login/IngresoCliente.jsp">Cerrar Sesion</a>
</div>

<div class="container mt-5">
    <h2 class="text-center text-danger mb-4">Cuentas</h2>
    
    <%
    ArrayList<Cuenta> lista = null;
    lista = (ArrayList<Cuenta>) request.getAttribute("ListaCuentas");
    %>
    
        <!-- Tabla de Cuentas -->
    <div class="table-responsive d-flex justify-content-center">
        <table class="table table-striped table-bordered text-center w-auto">
            <thead class="table-dark">
                <tr>
                    <th>Nro Cuenta</th><th>Tipo</th><th>CBU</th><th>Saldo</th><th>Fecha de Creacion</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
            <%
            if(lista != null){
            	for(Cuenta c : lista){%>
            		<tr>
                    	<td><%=c.getNumeroCuenta() %></td>
                    	<td><%=c.getTipoCuentaCuenta() %></td>
                    	<td><%=c.getCbuCuenta() %></td>
                    	<td>$<%=c.getSaldoCuenta() %></td>
                    	<td><%=c.getFechaCreacionCuenta() %></td>
                    	<td>
   						<form class="row g-4" method="get" action="${pageContext.request.contextPath}/CuentasClienteServlet">
   							<input type="hidden" name="idCuenta" value="<%=c.getIdCuenta()%>">
   							<input type="hidden" name="cbuCuenta" value="<%=c.getCbuCuenta() %>">
                        	<button type="submit" name="btnVerHistorial" class="btn btn-danger btn-sm">Ver Historial</button>
    					</form>
                    	</td>
                	</tr>
            	<%}
            }
            %>
                
            </tbody>
        </table>
    </div>
</div>
<!-- MODAL HISTORIAL DE CUENTA -->
<div class="modal fade" id="modalHistorialCuenta" tabindex="-1" aria-labelledby="modalHistorialCuentaLabel" aria-hidden="true">
  <div class="modal-dialog modal-xl modal-dialog-centered modal-dialog-scrollable">
    <div class="modal-content">
      <div class="modal-body">
      
        <!-- Tabla de Historial -->
        <div class="table-responsive">
          <table class="table table-striped table-bordered text-center" style="min-width: 1200px; width: 100%;">
            <thead class="table-dark">
              <tr>
                <th>ID</th><th>Fecha</th><th>Tipo de Movimiento</th><th>Cuenta Origen</th><th>Cuenta Destino</th><th>Importe</th>
       			<th>Detalle</th>
              </tr>
            </thead>
            <tbody>
              <% ArrayList<Movimiento> listaMov = null;
              listaMov = (ArrayList<Movimiento>) request.getAttribute("ListaMovimientos");
              if(listaMov != null){
            	  for(Movimiento m : listaMov){%>
            		  <tr>
            		  <td><%=m.getIdMov() %></td>
            		  <td><%=m.getFechaMov() %></td>
            		  <td><%=m.getDescTipoMov() %></td>
            		  <%String cbu = (String) request.getAttribute("CbuCuenta");%>
            		  <td><%=cbu %></td>
            		  <%
            		  if(m.getIdCuentaDestinoMov()== null){%>
            			  <td>-</td>
            		  <%}else{%>
            			  <td><%=m.getCbuCuentaDestino() %></td>
            		  <%}%>
            		  <td>$<%=m.getImporteMov() %></td>
            		  <% if(m.getIdTipoMovMov() == 4 && m.getCbuCuentaDestino().equals(cbu)){%>
            			  <td>Depósito de dinero</td>
            		  <%} else if(m.getIdTipoMovMov() == 4 && !m.getCbuCuentaDestino().equals(cbu)){%>
            			  <td>Extracción de dinero</td>
            		  <%} else{%>
            			  <td><%=m.getDetalleMov() %></td>
            		  <%} %>
            		  </tr>
            	  <%}
              }
              %>
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

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<% Boolean abrirModal = (Boolean) request.getAttribute("AbrirModal");%>
<script>
	<%if(abrirModal != null && abrirModal){%>
		const modal = new bootstrap.Modal(document.getElementById('modalHistorialCuenta'));
    	modal.show();
	<%}%>
</script>
</body>
</html>
