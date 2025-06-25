<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Menú Inicio</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600;700&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            font-family: 'Poppins', sans-serif;
            background: linear-gradient(135deg, #ffffff 0%, #ffe6eb 100%);
            color: black;
            margin: 0;
            padding: 0;
        }

        .navbar-custom {
            background-color: #ec0000;
        }

        .navbar-custom .nav-link,
        .navbar-custom .navbar-brand,
        .navbar-custom .dropdown-toggle {
            color: white;
            font-weight: bold;
        }

        .navbar-custom .nav-link:hover,
        .navbar-custom .dropdown-menu a:hover {
            color: #ec0000;
            background-color: white;
        }

        .dropdown-toggle::after {
            display: none;
        }

        .dropdown-toggle {
            font-size: 1.2rem;
            padding: 0.375rem 0.75rem;
            color: #ec0000 !important;
            background-color: white !important;
            border: 2px solid #ec0000;
            font-weight: bold;
        }

        .user-info {
            font-size: 18px;
            font-weight: 600;
            color: white;
        }

        .main-content {
            text-align: center;
            padding: 60px 20px;
        }

        .main-content h2 {
            font-size: 36px;
            color: #ec0000;
            margin-bottom: 30px;
        }

        .button-grid {
            display: grid;
            grid-template-columns: repeat(2, 200px);
            gap: 30px;
            justify-content: center;
            margin-top: 30px;
        }

        .btn {
            padding: 25px 30px;
            border: 2px solid #ec0000;
            background-color: white;
            text-decoration: none;
            color: #ec0000;
            font-weight: 600;
            font-size: 20px;
            border-radius: 8px;
            transition: 0.3s;
        }

        .btn:hover {
            background-color: #ec0000;
            color: white;
        }
    </style>
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
        <a href="${pageContext.request.contextPath}/MenuAdministrador?destino=clientes" class="btn">CLIENTES</a>
		<a href="${pageContext.request.contextPath}/MenuAdministrador?destino=cuentas" class="btn">CUENTAS</a>
		<a href="${pageContext.request.contextPath}/MenuAdministrador?destino=prestamos" class="btn">PRÉSTAMOS</a>
		<a href="${pageContext.request.contextPath}/MenuAdministrador?destino=informes" class="btn">INFORMES</a>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
