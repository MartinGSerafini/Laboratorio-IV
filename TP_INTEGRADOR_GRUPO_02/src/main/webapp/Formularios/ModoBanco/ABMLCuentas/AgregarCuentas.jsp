<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Agregar Cuenta</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../../z-CSS/ABMLCuentasCSS/AgregarCuentas.css">
   
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
                    <li><a class="dropdown-item" href="../ABMLClientes/AgregarClientes.jsp">CLIENTES</a></li>
                    <li><a class="dropdown-item" href="../AutorizacionPrestamos.jsp">PRÉSTAMOS</a></li>
                    <li><a class="dropdown-item" href="../Informes.jsp">INFORMES</a></li>
                    <li><a class="dropdown-item" href="../../Login/IngresoAdministrador.jsp">CERRAR SESIÓN</a></li>
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

<div class="container mt-5">
    <h2 class="text-center mb-4 text-danger">Agregar Cuentas</h2>
    <form class="row g-4">
        <div class="col-md-6">
            <label for="numero_Cuenta" class="form-label">Numero de Cuenta</label>
            <input type="number" class="form-control" id="dni" name="dni">
        </div>
        <div class="col-md-6">
            <label for="tipo_Cuenta" class="form-label">Tipo de Cuenta</label>
            <select class="form-select" id="tipo_Cuenta" name="tipo_Cuenta">
                <option value="">Seleccione</option>
                <option value="Masculino">Caja de ahorro</option>
                <option value="Femenino">Cuenta corriente</option>
            </select>
        </div>
        <div class="col-md-6">
            <label for="CBU" class="form-label">CBU</label>
            <input type="text" class="form-control" id="cuil" name="cuil">
        </div>
        <div class="col-md-6">
            <label for="saldo" class="form-label">Saldo de Cuenta</label>
            <input type="text" class="form-control" id="nombre" name="nombre">
        </div>
        <div class="col-12 text-center mt-4">
            <button type="submit" class="btn btn-custom px-5 py-2">Agregar Cuenta</button>
        </div>
    </form>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
