<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Prestamos solicitados</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../../z-CSS/ABMLCuentasCSS/ListarCuentas.css">
</head>
<body>

<!-- NAVBAR -->
<nav class="navbar navbar-expand-lg navbar-custom px-4">
    <div class="container-fluid">
        <div class="d-flex align-items-center">
            <div class="dropdown me-3">
                <button class="btn dropdown-toggle" type="button" data-bs-toggle="dropdown">
                    ☰
                </button>
                <ul class="dropdown-menu">
                    <li><a class="dropdown-item" href="#">CLIENTES</a></li>
                    <li><a class="dropdown-item" href="#">CUENTAS</a></li>
                    <li><a class="dropdown-item" href="#">PRÉSTAMOS</a></li>
                    <li><a class="dropdown-item" href="#">INFORMES</a></li>
                    <li><a class="dropdown-item" href="#">CERRAR SESIÓN</a></li>
                </ul>
            </div>
            <a href="AgregarCuentas.jsp" class="btn btn-custom me-2">Agregar Cuenta</a>
            <a href="ListarCuentas.jsp" class="btn btn-custom">Listar Cuentas</a>
        </div>
        <span class="navbar-text text-white">
            <%= session.getAttribute("nombreUsuario") != null ? session.getAttribute("nombreUsuario") : "INVITADO" %>
        </span>
    </div>
</nav>

<!-- CONTENIDO -->
<div class="container mt-5">
    <h3 class="text-center mb-4 text-danger">Prestamos solicitados</h3>

    <!-- Busqueda y Filtros -->
    <form class="d-flex justify-content-center mb-4" method="get" action="ListarCuentas.jsp">
        <input type="text" name="busqueda" class="form-control w-25 me-2" placeholder="Buscar...">
        <select name="filtro" class="form-select w-25 me-2">
            <option selected>Seleccione un filtro</option>
        </select>
        <button type="submit" class="btn btn-custom">Buscar</button>
    </form>

    <!-- Tabla de Cuentas -->
    <div class="table-responsive d-flex justify-content-center">
        <table class="table table-striped table-bordered text-center w-auto">
            <thead class="table-dark">
                <tr>
                    <th>ID Cuenta</th><th>ID Cliente</th><th>Fecha</th><th>Importe a pagar</th><th>Prestamo solicitado</th>
                    <th>Plazo de pago mensual</th><th>Monto mensual</th><th>Cuotas</th><th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>0001</td><td>0001</td><td>11/01/1111</td><td>1.000.000</td><td>2.000.000</td>
                    <td>30 días</td><td>100.000</td><td>20 cuotas</td>
                    <td>
                        <button class="btn btn-warning btn-sm">Autorizar prestamo</button>
                        <button class="btn btn-danger btn-sm">Rechazar prestamo</button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>

    <!-- Paginación -->
    <nav class="d-flex justify-content-center mt-4">
        <ul class="pagination">
            <!-- Pagination dinámica futura -->
        </ul>
    </nav>
</div>

<!-- Bootstrap Bundle -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
