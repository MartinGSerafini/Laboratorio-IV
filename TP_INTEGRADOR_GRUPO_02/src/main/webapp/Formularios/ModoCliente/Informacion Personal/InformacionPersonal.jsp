<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Infomacion Personal</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600&display=swap" rel="stylesheet">

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

        .menu-toggle {
            cursor: pointer;
            font-size: 28px;
            color: white;
        }

        .user-info {
            font-size: 18px;
            font-weight: 600;
            color: white;
        }

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

        .sidebar a:hover {
            background-color: #f2f2f2;
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
    <a href="#">Cuentas</a>
    <a href="#">Transferir Dinero</a>
    <a href="#">Nuevo Prestamo</a>
    <a href="#">Pagar Prestamos</a>
    <a href="#">Cerrar Sesion</a>
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
