<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Pagar Préstamo</title>
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
