<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Agregar Cliente</title>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Montserrat&display=swap');

        body {
            font-family: 'Montserrat', sans-serif;
            background: linear-gradient(135deg, #ffffff 0%, #ffe6eb 100%);
            margin: 0;
            padding: 0;
            color: black;
        }

        .navbar {
            background-color: #ec0000;
            padding: 10px 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            color: white;
        }

        .left-controls {
            display: flex;
            align-items: center;
            gap: 20px;
        }

        .menu-toggle {
            cursor: pointer;
            font-size: 24px;
            color: white;
        }

        .nav-button {
            padding: 8px 14px;
            background-color: white;
            color: #ec0000;
            font-weight: bold;
            border: none;
            border-radius: 5px;
            text-decoration: none;
            font-size: 14px;
        }

        .nav-button:hover {
            background-color: #ffe6eb;
        }

        .user-info {
            font-size: 14px;
        }

        .sidebar {
            position: absolute;
            top: 50px;
            left: 10px;
            background-color: #fff;
            border: 1px solid #ccc;
            padding: 10px;
            display: none;
            z-index: 1;
        }

        .sidebar a {
            display: block;
            padding: 8px;
            text-decoration: none;
            color: #ec0000;
            font-weight: bold;
        }

        .sidebar a:hover {
            background-color: #f2f2f2;
        }

        .main-content {
            padding: 40px 20px;
            max-width: 900px;
            margin: auto;
        }

        h2 {
            color: #ec0000;
            text-align: center;
            margin-bottom: 30px;
        }

        form {
            display: grid;
            grid-template-columns: 1fr 1fr;
            column-gap: 60px;  
   			row-gap: 20px;
        }

        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }

        input, select {
            width: 100%;
            padding: 10px;
            font-size: 14px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        .full-width {
            grid-column: span 2;
        }

        .submit-btn {
            background-color: #ec0000;
            color: white;
            font-weight: bold;
            padding: 12px 20px;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            margin-top: 20px;
        }

        .submit-btn:hover {
            background-color: #c70000;
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

<!-- Barra superior -->
<div class="navbar">
    <div class="left-controls">
        <div class="menu-toggle" onclick="toggleSidebar()">☰</div>
        <a href="AgregarCliente.jsp" class="nav-button">Agregar Cliente</a>
        <a href="ListarClientes.jsp" class="nav-button">Listar Clientes</a>
    </div>
    <div class="user-info">
        <%= session.getAttribute("nombreUsuario") != null ? session.getAttribute("nombreUsuario") : "INVITADO" %>
    </div>
</div>

<!-- Sidebar -->
<div id="sidebar" class="sidebar">
    <a href="#">Clientes</a>
    <a href="#">Cuentas</a>
    <a href="#">Prestamos</a>
    <a href="#">informes</a>
    <a href="#">Cerrar Sesion</a>
</div>

<!-- Contenido principal -->
<div class="main-content">
    <h2>Agregar Nuevo Cliente</h2>
    <form action="TuServletAgregarCliente" method="post">
        <div>
            <label>DNI</label>
            <input type="number" name="dniCliente" required>
        </div>
        <div>
            <label>CUIL</label>
            <input type="text" name="cuilCliente" required>
        </div>
        <div>
            <label>Nombre</label>
            <input type="text" name="nombreCliente" required>
        </div>
        <div>
            <label>Apellido</label>
            <input type="text" name="apellidoCliente" required>
        </div>
        <div>
            <label>Sexo</label>
            <select name="sexoCliente" required>
                <option value="">Seleccione</option>
                <option value="Masculino">Masculino</option>
                <option value="Femenino">Femenino</option>
                <option value="Otro">Otro</option>
            </select>
        </div>
        <div>
            <label>Nacionalidad</label>
            <input type="text" name="nacionalidadCliente" required>
        </div>
        <div>
            <label>Fecha de Nacimiento</label>
            <input type="date" name="fechaNacCliente" required>
        </div>
        <div>
            <label>Dirección</label>
            <input type="text" name="direccionCliente" required>
        </div>
        <div>
            <label>Localidad</label>
            <input type="text" name="localidadCliente" required>
        </div>
        <div>
            <label>Provincia</label>
            <input type="text" name="provinciaCliente" required>
        </div>
        <div>
            <label>Correo</label>
            <input type="email" name="correoCliente" required>
        </div>
        <div>
            <label>Teléfono</label>
            <input type="text" name="telefonoCliente" required>
        </div>
        <div>
            <label>Usuario</label>
            <input type="text" name="usuarioCliente" required>
        </div>
        <div>
            <label>Contraseña</label>
            <input type="password" name="contrasenaCliente" required>
        </div>
        <div class="full-width" style="text-align: center;">
            <button type="submit" class="submit-btn">Guardar Cliente</button>
        </div>
    </form>
</div>

</body>
</html>

