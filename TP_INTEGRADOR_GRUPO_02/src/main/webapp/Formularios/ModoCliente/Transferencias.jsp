<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Transferencias</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="../z-CSS/ModoClienteCSS/Transferencias.css">

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
  <div class="modal-dialog modal-md modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header encabezado-modal">
        <h5 class="modal-title" id="modalTransferirLabel">Transferencia</h5>
        <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Cerrar"></button>
      </div>
      <div class="modal-body">

        <!-- Dinero Disponible -->
        <div class="mb-3">
          <label class="form-label fw-bold">Dinero Disponible:</label>
          <p class="dinero-disponible">$ <span id="dineroDisponible">50,000</span></p>
        </div>

        <!-- Ingreso de Monto -->
        <div class="mb-3">
          <label for="montoTransferencia" class="form-label">Ingrese el monto a transferir:</label>
          <input type="number" class="form-control" id="montoTransferencia" placeholder="Ej: 10000">
        </div>

        <!-- Botón Transferir -->
        <div class="text-center">
          <button type="button" class="btn-transferencia">Realizar Transferencia</button>
        </div>

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
