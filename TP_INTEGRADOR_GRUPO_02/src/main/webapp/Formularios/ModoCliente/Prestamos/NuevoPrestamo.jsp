<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <title>Nuevo prestamo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../../z-CSS/ModoClienteCSS/Prestamos.css">
        
    <script>
        function toggleSidebar() {
            const sidebar = document.getElementById("sidebar");
            sidebar.style.display = sidebar.style.display === "block" ? "none" : "block";
        }
    </script>
</head>
<!-- NAVBAR -->
<div class="navbar">
    <div class="menu-toggle" onclick="toggleSidebar()">☰</div>
    <div class="user-info">
        <%= session.getAttribute("nombreUsuario") != null ? session.getAttribute("nombreUsuario") : "INVITADO" %>
    </div>
</div>

<div id="sidebar" class="sidebar">
    <a href="../InformacionPersonal.jsp">Informacion Personal</a>
    <a href="../Cuentas.jsp">Cuentas</a>
    <a href="../Transferencias.jsp">Transferir Dinero</a>
    <a href="PagarPrestamo.jsp">Pagar Prestamos</a>
    <a href="../../Login/IngresoCliente.jsp">Cerrar Sesion</a>
</div>

<div class="main-content">
    <h2>Prestamos disponibles</h2>
    <div class="button-grid">
        <a href="#" class="btn">Prestamo 1 por $xxx.xx</a>
        <a href="#" class="btn">Prestamo 2 por $xxx.xx</a>
        <a href="#" class="btn">Prestamo 3 por $xxx.xx</a>
        <a href="#" class="btn">Prestamo 4 por $xxx.xx</a>
    </div>
</div>

<div class="main-content">
    <h2>Cuotas disponibles</h2>
    <div class="button-grid">
        <a href="#" class="btn">x cuotas de $xxx.xx</a>
        <a href="#" class="btn">x cuotas de $xxx.xx</a>
        <a href="#" class="btn">x cuotas de $xxx.xx</a>
        <a href="#" class="btn">x cuotas de $xxx.xx</a>
    </div>
</div>

<div class="container mt-5">
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
                <tr>
                    <td>123321</td><td>Cuenta Corriente</td><td>75847261947</td>
                    <td>$15000</td>
                    <td>
                        <button class="btn btn-warning btn-sm">Editar</button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>

    <!-- Paginación -->
    <nav class="d-flex justify-content-center mt-4">
        <ul class="pagination">
        </ul>
    </nav>
</div>

<div class="main-content">
        <a href="#" class="btn">Aceptar</a>
        <a href="#" class="btn">Cancelar</a>
</div>


<!-- Bootstrap Bundle -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<body>
</html>