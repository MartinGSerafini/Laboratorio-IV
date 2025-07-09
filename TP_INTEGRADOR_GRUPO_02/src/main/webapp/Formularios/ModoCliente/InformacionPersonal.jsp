<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="entidades.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Infomación Personal</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/Formularios/z-CSS/ModoClienteCSS/InformacionPersonal.css">

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
        <%= session.getAttribute("nombreUsuario") != null ? session.getAttribute("nombreUsuario") : "INVITADO"%>
    </div>
</div>

<div id="sidebar" class="sidebar">
    <a href="${pageContext.request.contextPath}/CuentasClienteServlet">Cuentas</a>
    <a href="${pageContext.request.contextPath}/TransferenciasClienteServlet">Transferir Dinero</a>
    <a href=Prestamos/NuevoPrestamo.jsp>Nuevo Prestamo</a>
    <a href="Prestamos/PagarPrestamo.jsp">Pagar Prestamos</a>
    <a href="${pageContext.request.contextPath}/Formularios/Login/IngresoCliente.jsp">Cerrar Sesion</a>
</div>

<div class="container mt-5">
    <h2 class="text-center text-danger mb-4">Información Personal</h2>
    <form class="row g-4 mb-4" method="get" action="/InfoPersonalClienteServlet">
    <% Cliente cli = new Cliente(); 
       cli = (Cliente) request.getAttribute("cliente"); 
       if(cli != null){%>
        <div class="col-md-6">
            <label class="form-label">Usuario</label>
            <input type="text" class="form-control" value="<%= cli.getUsuarioCliente() %>" 
           readonly>
        </div>
        <div class="col-md-6">
            <label class="form-label">Contraseña</label>
            <input type="text" class="form-control" value=" <%= cli.getContrasenaCliente() %>" 
           readonly>
        </div>
        <div class="col-md-6">
            <label class="form-label">DNI</label>
            <input type="text" class="form-control" value="<%= cli.getDniCliente() %>" 
           readonly>
        </div>
        <div class="col-md-6">
            <label class="form-label">CUIL</label>
            <input type="text" class="form-control" value="<%= cli.getCuilCliente() %>" 
           readonly>
        </div>
        <div class="col-md-6">
            <label class="form-label">Nombre</label>
            <input type="text" class="form-control" value="<%= cli.getNombreCliente() %>" 
           readonly>
        </div>
        <div class="col-md-6">
            <label class="form-label">Apellido</label>
            <input type="text" class="form-control" value="<%= cli.getApellidoCliente() %>" 
           readonly>
        </div>
        <div class="col-md-6">
            <label class="form-label">Sexo</label>
            <input type="text" class="form-control" value="<%= cli.getSexoCliente() %>" 
           readonly>
        </div>
        <div class="col-md-6">
            <label class="form-label">Nacionalidad</label>
            <input type="text" class="form-control" value="<%= cli.getNombreNacionalidad() %>" 
           readonly>
        </div>
        <div class="col-md-6">
            <label class="form-label">Fecha de Nacimiento</label>
            <input type="text" class="form-control" value="<%= cli.getFechaNacCliente() %>" 
           readonly>
        </div>
        <div class="col-md-6">
            <label class="form-label">Dirección</label>
            <input type="text" class="form-control" value="<%= cli.getDireccionCliente() %>" 
           readonly>
        </div>
        <div class="col-md-6">
            <label class="form-label">Localidad</label>
            <input type="text" class="form-control" value="<%= cli.getNombreLocalidad() %>" 
           readonly>
        </div>
        <div class="col-md-6">
            <label class="form-label">Provincia</label>
            <input type="text" class="form-control" value="<%= cli.getNombreProvincia() %>" 
           readonly>
        </div>
        <div class="col-md-6">
            <label class="form-label">Correo</label>
            <input type="email" class="form-control" value="<%= cli.getCorreoCliente() %>" 
           readonly>
        </div>
        <div class="col-md-6">
            <label class="form-label">Teléfono</label>
            <input type="text" class="form-control" value="<%= cli.getTelefonoCliente() %>" 
           readonly>
        </div>
        <%}%>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
