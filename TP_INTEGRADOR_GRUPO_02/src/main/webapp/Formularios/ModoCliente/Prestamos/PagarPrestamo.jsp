<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Pagar Préstamo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Poppins', sans-serif;
            background: linear-gradient(135deg, #ffffff 0%, #ffe6eb 100%);
            color: black;
            margin: 0;
            padding: 0;
        }
        .navbar {
            background-color: #ec0000;
            padding: 15px 25px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            color: white;
            font-size: 18px;
        }
        .menu-toggle { cursor: pointer; font-size: 28px; color: white; }
        .user-info { font-size: 18px; font-weight: 600; color: white; }
        .sidebar {
            position: absolute;
            top: 60px;
            left: 10px;
            background-color: #fff;
            border: 1px solid #ccc;
            padding: 15px;
            display: none;
            z-index: 1;
        }
        .sidebar a {
            display: block;
            padding: 10px;
            text-decoration: none;
            color: #ec0000;
            font-weight: 600;
            font-size: 16px;
        }
        .sidebar a:hover { background-color: #f2f2f2; }
        .main-content {
            text-align: center;
            padding: 60px 20px;
        }
        .main-content h2 {
            font-size: 36px;
            color: #ec0000;
            margin-bottom: 30px;
        }
        .btn {
            padding: 10px 20px;
            border: 2px solid #ec0000;
            background-color: white;
            text-decoration: none;
            color: #ec0000;
            font-weight: 500;
            font-size: 16px;
            border-radius: 8px;
            transition: 0.3s;
        }
        .button-grid {
            display: grid;
            grid-template-columns: repeat(4, 200px);
            gap: 30px;
            justify-content: center;
            margin-top: 30px;
        }
        .btn:hover {
            background-color: #ec0000;
            color: white;
        }
        .btn:focus, .btn:active {
            background-color: #004d40;
            color: white;
            outline: none;
        }
    </style>
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
    <div id="sidebar" class="sidebar">
    <a href="../InformacionPersonal.jsp">Informacion Personal</a>
    <a href="../Cuentas.jsp">Cuentas</a>
    <a href="../Transferencias.jsp">Transferir Dinero</a>
    <a href=NuevoPrestamo.jsp>Nuevo Prestamo</a>
    <a href="../../Login/IngresoCliente.jsp">Cerrar Sesion</a>
</div>
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
