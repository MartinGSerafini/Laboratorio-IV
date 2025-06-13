<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Menú Inicio</title>

    <!-- Fuente moderna de Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600;700&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="../z-CSS/ModoClienteCSS/MenuInicioCliente.css">

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
    <a href="#">Informacion Personal</a>
    <a href="#">Cerrar Sesion</a>
</div>

<div class="main-content">
    <h2>Menú Inicio</h2>
    <div class="button-grid">
        <a href="Cuentas.jsp" class="btn">CUENTAS</a>
        <a href="Transferencias.jsp" class="btn">TRASFERIR DINERO</a>
        <a href="Prestamos/NuevoPrestamo.jsp" class="btn">NUEVO PRESTAMO</a>
        <a href="Prestamos/PagarPrestamo.jsp" class="btn">PAGAR PRESTAMOS</a>
    </div>
</div>

</body>
</html>
