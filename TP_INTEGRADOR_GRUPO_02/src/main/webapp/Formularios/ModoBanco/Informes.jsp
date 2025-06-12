<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Informes</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600;700&display=swap" rel="stylesheet">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="../z-CSS/ABMLCuentasCSS/ListarCuentas.css">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
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
                    <li><a class="dropdown-item" href="ABMLClientes/AgregarClientes.jsp">CLIENTES</a></li>
				    <li><a class="dropdown-item" href="ABMLCuentas/AgregarCuentas.jsp">CUENTAS</a></li>
				    <li><a class="dropdown-item" href=AutorizacionPrestamos.jsp>PRESTAMOS</a></li>
				    <li><a class="dropdown-item" href="../Login/IngresoAdministrador.jsp">CERRAR SESIÓN</a></li>
                </ul>
            </div>
        </div>
        <span class="navbar-text text-white">
            <%= session.getAttribute("nombreUsuario") != null ? session.getAttribute("nombreUsuario") : "INVITADO" %>
        </span>
    </div>
</nav>

<div class="container mt-5">
    <h2 class="text-center mb-4 text-danger fw-bold display-6">Informes</h2>
    

    <!-- Botones de informes -->
    <div class="d-flex justify-content-center gap-3">
        <button class="btn btn-custom btn-informe" data-bs-toggle="modal" data-bs-target="#modalInformeN1">Informe N°1</button>
		<button class="btn btn-custom btn-informe" data-bs-toggle="modal" data-bs-target="#modalInformeN2">Informe N°2</button>
    </div>
</div>

<!-- MODAL INFORME N1 -->
<div class="modal fade" id="modalInformeN1" tabindex="-1" aria-labelledby="modalInformeN1Label" aria-hidden="true">
  <div class="modal-dialog modal-lg modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header bg-danger text-white">
        <h5 class="modal-title" id="modalInformeN1Label">Informe N°1</h5>
        <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Cerrar"></button>
      </div>
      <div class="modal-body">
        <p>El porcentaje de X dentro de Y es %Z.</p>
        <p>Y el porcentaje de A en B es %C.</p>
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
        <h5 class="modal-title" id="modalInformeN2Label">Informe N°2</h5>
        <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Cerrar"></button>
      </div>
      <div class="modal-body">
        <p>El porcentaje de X dentro de Y es %Z.</p>
        <p>Y el porcentaje de A en B es %C.</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
      </div>
    </div>
  </div>
</div>

</body>
</html>