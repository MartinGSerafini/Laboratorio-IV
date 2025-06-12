<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Transferencias</title>
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
            padding: 5px 15px;
            border: 2px solid #ec0000;
            background-color: white;
            text-decoration: none;
            color: #ec0000;
            font-weight: 500;
            font-size: 15px;
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
    <a href="InformacionPersonal.jsp">Informacion Personal</a>
    <a href="Cuentas.Jsp">Cuentas</a>
    <a href=Prestamos/NuevoPrestamo.jsp>Nuevo Prestamo</a>
    <a href="Prestamos/PagarPrestamo.jsp">Pagar Prestamos</a>
    <a href="../Login/IngresoCliente.jsp">Cerrar Sesion</a>
</div>

<!-- CONTENIDO -->
<div class="container mt-5">
    <h3 class="text-center mb-4 text-danger">Transferencias</h3>

    <!-- Busqueda y Filtros -->
    <form class="d-flex justify-content-center mb-4" method="get" action="ListarCuentas.jsp">
        <input type="text" name="busqueda" class="form-control w-25 me-2" placeholder="Buscar...">

        <button type="submit" class="btn btn-custom">Buscar Cuenta</button>
    </form>

    <!-- Tabla de Cuentas -->
    <div class="table-responsive d-flex justify-content-center">
        <table class="table table-striped table-bordered text-center w-auto">
            <thead class="table-dark">
                <tr>
                	<th>Nombre</th><th>Apellido</th><th>Nro Cuenta</th><th>Tipo</th><th>CBU</th><th>Acciones</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>Martin</td><td>Serafini</td><td>123321</td><td>Cuenta Corriente</td><td>75847261947</td>
                <td>
                	<button class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#modalTransferir">Transferir</button>
                </td>
              </tr>
            </tbody>
        </table>
    </div>

    <!-- Paginación -->
    <nav class="d-flex justify-content-center mt-4">
        <ul class="pagination">
        </ul>
    </nav>
</div>

<!-- MODAL TRANSFERIR -->
<div class="modal fade" id="modalTransferir" tabindex="-1" aria-labelledby="modalTransferirLabel" aria-hidden="true">
  <div class="modal-dialog modal-xl modal-dialog-centered modal-dialog-scrollable">
    <div class="modal-content">
      <div class="modal-header bg-danger text-white">

        <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Cerrar"></button>
      </div>
      <div class="modal-body">
        
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
      </div>
    </div>
  </div>
</div>

<!-- Bootstrap Bundle -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
