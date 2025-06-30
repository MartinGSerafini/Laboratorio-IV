<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Menú Inicio</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600;700&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Formularios/z-CSS/ModoBancoCSS/MenuBanco.css">

</head>
<body>

<nav class="navbar navbar-expand-lg navbar-custom px-4">
    <div class="container-fluid">
        <div class="d-flex align-items-center">
            <div class="dropdown me-3">
                <button class="btn dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                    ☰
                </button>
                <ul class="dropdown-menu">
                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/MenuAdministrador?accion=cerrar">CERRAR SESIÓN</a></li>
                </ul>
            </div>
        </div>
        <span class="navbar-text text-white ms-auto">
            <%= session.getAttribute("adminLogueado") != null ? session.getAttribute("adminLogueado") : "INVITADO" %>
        </span>
    </div>
</nav>

<div class="main-content">
    <h2>Menú Inicio</h2>
    <div class="button-grid">
        <a href="${pageContext.request.contextPath}/ListadoClientesServlet" class="btn">CLIENTES</a>
		<a href="${pageContext.request.contextPath}/ListadoCuentasServlet" class="btn">CUENTAS</a>
		<a href="${pageContext.request.contextPath}/AutorizacionPrestamosServlet" class="btn">PRÉSTAMOS</a>
		<a href="${pageContext.request.contextPath}/InformesServlet" class="btn">INFORMES</a>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
