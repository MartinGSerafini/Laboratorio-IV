<%@ page import="java.util.ArrayList" %>
<%@ page import="entidades.*" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Agregar Cliente</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Formularios/z-CSS/ABMLClientesCSS/AgregarClientes.css">
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
    		<a class="dropdown-item" href="${pageContext.request.contextPath}//Formularios/ModoBanco/Informes/Informes.jsp">INFORMES</a>
    		<a class="dropdown-item" href="${pageContext.request.contextPath}/Formularios/Login/IngresoAdministrador.jsp">CERRAR SESIÓN</a>
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
    <h2 class="text-center mb-4 text-danger">Agregar Clientes</h2>
        <form class="row g-4" action="${pageContext.request.contextPath}/AgregarClienteServlet" method="post">
    	<div class="col-md-6">
            <label for="usuario" class="form-label">Usuario</label> 
            <input type="text" class="form-control" id="idUsuario" name="idUsuario"
          		 value="<%= request.getAttribute("idUsuario") %>" readonly>        		 
				
        </div>
        
        <div class="col-md-6">
            <label for="contrasena" class="form-label">Contraseña</label>
                  <input type="password" class="form-control" id="contrasena" name="contrasena"
                   value="<%= request.getParameter("contrasena") != null ? request.getParameter("contrasena") : "" %>">
        </div>
        <div class="col-md-6">
            <label for="dni" class="form-label">DNI</label>
                        <input type="text" class="form-control" id="dni" name="dni"
                   value="<%= request.getParameter("dni") != null ? request.getParameter("dni") : "" %>">
        </div>
        <div class="col-md-6">
            <label for="cuil" class="form-label">CUIL</label>
             <input type="text" class="form-control" id="cuil" name="cuil"
                   value="<%= request.getParameter("cuil") != null ? request.getParameter("cuil") : "" %>">
        </div>
        <div class="col-md-6">
            <label for="nombre" class="form-label">Nombre</label>
                        <input type="text" class="form-control" id="nombre" name="nombre"
                   value="<%= request.getParameter("nombre") != null ? request.getParameter("nombre") : "" %>">
        </div>
        <div class="col-md-6">
            <label for="apellido" class="form-label">Apellido</label>
              <input type="text" class="form-control" id="apellido" name="apellido"
                   value="<%= request.getParameter("apellido") != null ? request.getParameter("apellido") : "" %>">
        </div>
        <div class="col-md-6">
            <label for="sexo" class="form-label">Sexo</label>
            <select class="form-select" id="sexo" name="sexo">
                <option value="" <%= (request.getParameter("sexo") == null || request.getParameter("sexo").isEmpty()) ? "selected" : "" %>>Seleccione</option>
                <option value="Masculino" <%= "Masculino".equals(request.getParameter("sexo")) ? "selected" : "" %>>Masculino</option>
                <option value="Femenino" <%= "Femenino".equals(request.getParameter("sexo")) ? "selected" : "" %>>Femenino</option>
            </select>
        </div>
        <div class="col-md-6">
            <label for="nacionalidad" class="form-label">Nacionalidad</label>
            <select id="nacionalidad" name="nacionalidad" class="form-control">
        <%
	        String nacionalidadSeleccionada = request.getParameter("nacionalidad");
	        ArrayList<Nacionalidad> nacionalidades = (ArrayList<Nacionalidad>) request.getAttribute("nacionalidades");
	        if (nacionalidades != null) {
	            for (Nacionalidad n : nacionalidades) {
	                boolean selected = nacionalidadSeleccionada != null && nacionalidadSeleccionada.equals(String.valueOf(n.getId()));
	    %>
	        <option value="<%= n.getId() %>" <%= selected ? "selected" : "" %>><%= n.getNacionalidad() %></option>
	    <%
	            }
	        }
	    %>
    </select>
        </div>
        <div class="col-md-6">
            <label for="fechaNac" class="form-label">Fecha de Nacimiento</label>
               <input type="date" class="form-control" id="fechaNac" name="fechaNac"
                   value="<%= request.getParameter("fechaNac") != null ? request.getParameter("fechaNac") : "" %>">
        </div>
        <div class="col-md-6">
            <label for="direccion" class="form-label">Dirección</label>
             <input type="text" class="form-control" id="direccion" name="direccion"
                   value="<%= request.getParameter("direccion") != null ? request.getParameter("direccion") : "" %>">
        </div>
        <div class="col-md-6">
            <label for="provincia" class="form-label">Provincia</label>
             <select name="provincia" id="provincia" class="form-control">
        <option value="">Seleccione</option>
	        <%
	            ArrayList<Provincia> provincias = (ArrayList<Provincia>) request.getAttribute("provincias");
	            String provinciaSeleccionada = request.getParameter("provincia");
	
	            if (provincias != null) {
	                for (Provincia p : provincias) {
	                    boolean selected = (provinciaSeleccionada != null && provinciaSeleccionada.equals(String.valueOf(p.getId())));
	        %>
	            <option value="<%= p.getId() %>" <%= selected ? "selected" : "" %>><%= p.getProvincia() %></option>
	        <%
	                }
	            }
	        %>
    	</select>
    	 <!-- Botón intermedio para cargar localidades -->
	    <button type="submit" name="accion" value="cargarLocalidades" class="btn btn-outline-secondary btn-sm mt-2">
	        Cargar localidades
	    </button>
    
        </div>
        <div class="col-md-6">
              <label for="localidad" class="form-label">Localidad</label>
            <select name="localidad" id="localidad" class="form-control">
	        <%
	            ArrayList<Localidad> localidades = (ArrayList<Localidad>) request.getAttribute("localidades");
	            String localidadSeleccionada = request.getParameter("localidad");
	
	            if (localidades != null) {
	                for (Localidad l : localidades) {
	                    boolean selected = localidadSeleccionada != null && localidadSeleccionada.equals(String.valueOf(l.getId()));
	        %>
	            <option value="<%= l.getId() %>" <%= selected ? "selected" : "" %>><%= l.getLocalidad() %></option>
	        <%
	                }
	            } else {
	        %>
	            <option value="">-- Cargue una provincia --</option>
	        <%
	            }
	        %>
	    </select>
        </div>
        <div class="col-md-6">
            <label for="correo" class="form-label">Correo</label>
               <input type="email" class="form-control" id="correo" name="correo"
                   value="<%= request.getParameter("correo") != null ? request.getParameter("correo") : "" %>">
        </div>
        <div class="col-md-6">
            <label for="telefono" class="form-label">Teléfono</label>
           <input type="text" class="form-control" id="telefono" name="telefono"
                   value="<%= request.getParameter("telefono") != null ? request.getParameter("telefono") : "" %>">
        </div>
        <div class="col-12 text-center mt-4">
                  <button type="submit" name="accion" value="agregarCliente" class="btn btn-custom px-5 py-2">
            Agregar Cliente
        </button>
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
<%
    ArrayList<String> errores = (ArrayList<String>) request.getAttribute("errores");
    if (errores != null && !errores.isEmpty()) {
%>
    <script>
        window.onload = function() {
            let modal = new bootstrap.Modal(document.getElementById('modalErroresValidacion'));
            modal.show();
        };
    </script>
<%
    }
%>

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

<!-- Modal errores validación -->
<div class="modal fade" id="modalErroresValidacion" tabindex="-1" aria-labelledby="modalErroresValidacionLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header bg-danger text-white">
        <h5 class="modal-title" id="modalErroresValidacionLabel">Errores de Validación</h5>
      </div>
      <div class="modal-body">
        <% 
            //ArrayList<String> errores = (ArrayList<String>) request.getAttribute("errores");
            if (errores != null && !errores.isEmpty()) {
                for (String error : errores) {
        %>
                    <p><%= error %></p>
        <% 
                }
            } 
        %>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-warning" data-bs-dismiss="modal">Cerrar</button>
      </div>
    </div>
  </div>
 </div>
  

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
