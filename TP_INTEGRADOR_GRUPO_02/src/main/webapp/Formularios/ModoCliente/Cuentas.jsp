<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cuentas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../z-CSS/ModoClienteCSS/Cuentas.css">

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
    <a href="Transferencias.jsp">Transferir Dinero</a>
    <a href=Prestamos/NuevoPrestamo.jsp>Nuevo Prestamo</a>
    <a href="Prestamos/PagarPrestamo.jsp">Pagar Prestamos</a>
    <a href="../Login/IngresoCliente.jsp">Cerrar Sesion</a>
</div>

<div class="container mt-5">
    <h2 class="text-center text-danger mb-4">Cuentas</h2>
    <form class="row g-4">
        <!-- Tabla de Cuentas -->
    <div class="table-responsive d-flex justify-content-center">
        <table class="table table-striped table-bordered text-center w-auto">
            <thead class="table-dark">
                <tr>
                    <th>Nro Cuenta</th><th>Tipo</th><th>CBU</th><th>Saldo</th><th>Fecha de Creacion</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>123321</td><td>Cuenta Corriente</td><td>75847261947</td><td>$15000</td><td>25/06/2024</td>
                    <td>
                        <button type="button" class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#modalHistorialCuenta">Ver Historial</button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
    </form>
</div>
<!-- MODAL HISTORIAL DE CUENTA -->
<div class="modal fade" id="modalHistorialCuenta" tabindex="-1" aria-labelledby="modalHistorialCuentaLabel" aria-hidden="true">
  <div class="modal-dialog modal-xl modal-dialog-centered modal-dialog-scrollable">
    <div class="modal-content">
      <div class="modal-body">
        <!-- Tabla de Clientes -->
        <div class="table-responsive">
          <table class="table table-striped table-bordered text-center" style="min-width: 1200px; width: 100%;">
            <thead class="table-dark">
              <tr>
                <th>ID</th><th>Fecha</th><th>Tipo de Movimiento</th><th>Cuenta Origen</th><th>Cuenta Destino</th><th>Importe</th>
       			<th>Detalle</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>0001</td><td>06/02/2023</td><td>Pago de Prestamo</td><td>39482758251</td><td>57282957162</td><td>$20500,60</td>
        		<td>Pago de Cuota 2</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
      </div>
    </div>
  </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
