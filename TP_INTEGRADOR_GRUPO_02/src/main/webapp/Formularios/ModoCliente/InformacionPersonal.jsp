<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Infomacion Personal</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="../z-CSS/ModoClienteCSS/InformacionPersonal.css">

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
    <a href="Cuentas.Jsp">Cuentas</a>
    <a href="Transferencias.jsp">Transferir Dinero</a>
    <a href=Prestamos/NuevoPrestamo.jsp>Nuevo Prestamo</a>
    <a href="Prestamos/PagarPrestamo.jsp">Pagar Prestamos</a>
    <a href="../Login/IngresoCliente.jsp">Cerrar Sesion</a>
</div>

<div class="container mt-5">
    <h2 class="text-center text-danger mb-4">Información Personal</h2>
    <form class="row g-4">
        <div class="col-md-6">
            <label class="form-label">Usuario</label>
            <input type="text" class="form-control" value="<%= request.getAttribute("usuario") != null ? request.getAttribute("nombre") : "Martin2025" %>" 
           readonly>
        </div>
        <div class="col-md-6">
            <label class="form-label">Contraseña</label>
            <input type="text" class="form-control" value="<%= request.getAttribute("contrasena") != null ? request.getAttribute("nombre") : "Contraseña123" %>" 
           readonly>
        </div>
        <div class="col-md-6">
            <label class="form-label">DNI</label>
            <input type="text" class="form-control" value="<%= request.getAttribute("dni") != null ? request.getAttribute("nombre") : "42419413" %>" 
           readonly>
        </div>
        <div class="col-md-6">
            <label class="form-label">CUIL</label>
            <input type="text" class="form-control" value="<%= request.getAttribute("cuil") != null ? request.getAttribute("nombre") : "27424194137" %>" 
           readonly>
        </div>
        <div class="col-md-6">
            <label class="form-label">Nombre</label>
            <input type="text" class="form-control" value="<%= request.getAttribute("nombre") != null ? request.getAttribute("nombre") : "Martin" %>" 
           readonly>
        </div>
        <div class="col-md-6">
            <label class="form-label">Apellido</label>
            <input type="text" class="form-control" value="<%= request.getAttribute("apellido") != null ? request.getAttribute("nombre") : "Serafini" %>" 
           readonly>
        </div>
        <div class="col-md-6">
            <label class="form-label">Sexo</label>
            <input type="text" class="form-control" value="<%= request.getAttribute("sexo") != null ? request.getAttribute("nombre") : "Masculino" %>" 
           readonly>
        </div>
        <div class="col-md-6">
            <label class="form-label">Nacionalidad</label>
            <input type="text" class="form-control" value="<%= request.getAttribute("nacionalidad") != null ? request.getAttribute("nombre") : "Argentino" %>" 
           readonly>
        </div>
        <div class="col-md-6">
            <label class="form-label">Fecha de Nacimiento</label>
            <input type="text" class="form-control" value="<%= request.getAttribute("fechaNac") != null ? request.getAttribute("nombre") : "06/01/2000" %>" 
           readonly>
        </div>
        <div class="col-md-6">
            <label class="form-label">Dirección</label>
            <input type="text" class="form-control" value="<%= request.getAttribute("direccion") != null ? request.getAttribute("nombre") : "Matheu 1532" %>" 
           readonly>
        </div>
        <div class="col-md-6">
            <label class="form-label">Localidad</label>
            <input type="text" class="form-control" value="<%= request.getAttribute("localidad") != null ? request.getAttribute("nombre") : "Tigre" %>" 
           readonly>
        </div>
        <div class="col-md-6">
            <label class="form-label">Provincia</label>
            <input type="text" class="form-control" value="<%= request.getAttribute("provincia") != null ? request.getAttribute("nombre") : "Buenos Aires" %>" 
           readonly>
        </div>
        <div class="col-md-6">
            <label class="form-label">Correo</label>
            <input type="email" class="form-control" value="<%= request.getAttribute("correo") != null ? request.getAttribute("nombre") : "Guillermo@gmail.com" %>" 
           readonly>
        </div>
        <div class="col-md-6">
            <label class="form-label">Teléfono</label>
            <input type="text" class="form-control" value="<%= request.getAttribute("telefono") != null ? request.getAttribute("1123889911") : "Juan" %>" 
           readonly>
        </div>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
