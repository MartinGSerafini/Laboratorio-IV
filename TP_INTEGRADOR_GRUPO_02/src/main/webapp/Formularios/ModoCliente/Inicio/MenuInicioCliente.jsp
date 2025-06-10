<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Menú Inicio</title>

    <!-- Fuente moderna de Google Fonts -->
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
    <a href="#">Transferir Dinero</a>
    <a href="#">Nuevo Prestamo</a>
    <a href="#">Pagar Prestamos</a>
    <a href="#">Cerrar Sesion</a>
</div>

<div class="main-content">
    <h2>Menú Inicio</h2>
    <div class="button-grid">
        <a href="#" class="btn">INFORMACION PERSONAL</a>
        <a href="#" class="btn">TRASFERIR DINERO</a>
        <a href="#" class="btn">NUEVO PRESTAMO</a>
        <a href="#" class="btn">PAGAR PRESTAMOS</a>
    </div>
</div>

</body>
</html>
