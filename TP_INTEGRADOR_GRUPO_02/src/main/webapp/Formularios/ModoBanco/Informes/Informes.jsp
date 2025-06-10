<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Informes</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600;700&display=swap" rel="stylesheet">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

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
    <a href="#">Clientes</a>
    <a href="#">Cuentas</a>
    <a href="#">Prestamos</a>
    <a href="#">informes</a>
    <a href="#">Cerrar Sesion</a>
</div>
<div class="main-content">
  <h2>Informes</h2>
  <div class="button-grid">
    <button class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#modalInformeN1">Informe N1</button>
    <button class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#modalInformeN2">Informe N2</button>
  </div>
</div>

<!-- MODAL INFORME N1 -->
<div class="modal fade" id="modalInformeN1" tabindex="-1" aria-labelledby="modalInformeN1Label" aria-hidden="true">
  <div class="modal-dialog modal-lg modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header bg-danger text-white">
        <h5 class="modal-title" id="modalInformeN1Label">Informe N*1</h5>
        <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Cerrar"></button>
      </div>
      <div class="modal-body">
        <p>
          El porcentaje de X dentro de Y es %Z
        </p>
        <p>
          Y el porcentaje de A en B es %C.
        </p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
      </div>
    </div>
  </div>
</div>
<!-- MODAL INFORME N2 -->
<div class="modal fade" id="modalInformeN2" tabindex="-1" aria-labelledby="modalInformeN2Label" aria-hidden="true">
  <div class="modal-dialog modal-lg modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header bg-danger text-white">
        <h5 class="modal-title" id="modalInformeN2Label">Informe N*2</h5>
        <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Cerrar"></button>
      </div>
      <div class="modal-body">
        <p>
          El porcentaje de X dentro de Y es %Z
        </p>
        <p>
          Y el porcentaje de A en B es %C.
        </p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
      </div>
    </div>
  </div>
</div>
</body>
</html>