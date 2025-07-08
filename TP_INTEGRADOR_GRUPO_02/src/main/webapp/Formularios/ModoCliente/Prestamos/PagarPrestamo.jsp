<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Pagar Préstamo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../../z-CSS/ModoBancoCSS/PrestamosCSS/Prestamos.css">
    
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
    <a href="../InformacionPersonal.jsp">Informacion Personal</a>
    <a href="../Cuentas.jsp">Cuentas</a>
    <a href="../Transferencias.jsp">Transferir Dinero</a>
    <a href=NuevoPrestamo.jsp>Nuevo Prestamo</a>
    <a href="../../Login/IngresoCliente.jsp">Cerrar Sesion</a>
</div>


<div class="main-content">
    <h2>Seleccione el préstamo a pagar</h2>
    <div class="button-grid">
        <a href="#" class="btn">Préstamo 001 - $##.### restante</a>
        <a href="#" class="btn">Préstamo 002 - $##.### restante</a>
        <a href="#" class="btn">Préstamo 003 - $##.### restante</a>
        <a href="#" class="btn">Préstamo 004 - $##.### restante</a>
    </div>
</div>

<div class="container mt-5">
    <h3 class="text-center mb-4 text-danger">Cuotas a pagar</h3>
	<div class="table-responsive d-flex justify-content-center">
            <table class="table table-bordered w-auto text-center">
                <thead class="table-light">
                <tr>
                    <th>N° Cuota</th><th>Importe</th><th>Seleccionar</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>1</td><td>$#####</td>
                    <td><input type="radio" name="cuotaSeleccionada" value="1"></td>
                </tr>
                <tr>
                    <td>2</td><td>$#####</td>
                    <td><input type="radio" name="cuotaSeleccionada" value="2"></td>
                </tr>
                </tbody>
            </table>
        </div>
</div>

<div class="container mt-5">
    <h3 class="text-center mb-4 text-danger">Seleccione una cuenta</h3>
    <div class="table-responsive d-flex justify-content-center">
        <table class="table table-striped table-bordered text-center w-auto">
            <thead class="table-light">
                <tr>
                    <th>Nro Cuenta</th><th>Tipo</th><th>CBU</th><th>Saldo</th><th>Seleccionar</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>123321</td>
                    <td>Cuenta Corriente</td>
                    <td>75847261947</td>
                    <td>$15000</td>
                    <td><button class="btn btn-success btn-sm">Usar</button></td>
                </tr>
            </tbody>
        </table>
    </div>
</div>

<div class="main-content">
    <a href="#" class="btn">Confirmar Pago</a>
    <a href="#" class="btn">Cancelar</a>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
