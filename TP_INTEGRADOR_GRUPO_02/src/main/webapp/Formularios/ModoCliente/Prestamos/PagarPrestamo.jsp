<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Pagar Préstamo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Formularios/z-CSS/ModoClienteCSS/Prestamos/Prestamos.css">

    <script>
        function toggleSidebar() {
            const sidebar = document.getElementById("sidebar");
            sidebar.style.display = sidebar.style.display === "block" ? "none" : "block";
        }
    </script>
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
                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/InfoPersonalClienteServlet">Información Personal</a></li>
                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/CuentasClienteServlet">Cuentas</a></li>
                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/TransferenciasClienteServlet">Transferir Dinero</a></li>
                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/NuevoPrestamoServlet">Nuevo Préstamo</a></li>
                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/PagarPrestamoServlet">Pagar Préstamo</a></li>
                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/Formularios/Login/InicioUsuario.jsp">Cerrar Sesión</a></li>
                </ul>
            </div>
        </div>
        <span class="navbar-text text-white">
            <%= session.getAttribute("ClienteLogueado") != null ? session.getAttribute("ClienteLogueado") : "INVITADO" %>
        </span>
    </div>
</nav>

<!-- Paso 1: Seleccionar préstamo -->
<div class="container mt-5">
    <h2 class="text-center text-danger">1. Seleccione un préstamo</h2>
    <div class="table-responsive d-flex justify-content-center">
        <table class="table table-bordered w-auto text-center">
            <thead class="table-light">
                <tr>
                    <th>Fecha Solicitud</th>
                    <th>Importe Solicitado</th>
                    <th>Importe Total</th>
                    <th>Plazo de Pago</th>
                    <th>Monto Cuota</th>
                    <th>Cuenta Depósito</th>
                    <th>Seleccionar</th>
                </tr>
            </thead>
            <tbody>
            <%
                java.util.List<entidades.Prestamo> prestamos = (java.util.List<entidades.Prestamo>) request.getAttribute("prestamos");
                if (prestamos != null) {
                    for (entidades.Prestamo prestamo : prestamos) {
            %>
                <tr>
                    <td><%= new java.text.SimpleDateFormat("dd/MM/yyyy").format(prestamo.getFechaSolicitudPres()) %></td>
                    <td>$<%= prestamo.getImporteSolicitadoPres() %></td>
                    <td>$<%= prestamo.getImporteTotalPres() %></td>
                    <td><%= prestamo.getPlazoMesesPres() %></td>
                    <td>$<%= prestamo.getMontoCuotaPres() %></td>
                    <td><%= prestamo.getIdCuentaDepositoPres() %></td>
                    <td><input type="radio" name="prestamoSeleccionado" value="<%= prestamo.getIdPrestamo() %>" onchange="activarOpciones(<%= prestamo.getIdPrestamo() %>)"/></td>
                </tr>
            <%
                    }
                } else {
            %>
                <tr><td colspan="8">No hay préstamos para mostrar</td></tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>
</div>

<div class="container mt-4 text-center" id="accionesPago" style="display: none;">
    <h4>2. ¿Qué desea hacer?</h4>
    <button class="btn me-3" onclick="abrirModalCuentas('total')">Pagar Todo</button>
    <button class="btn" onclick="cargarCuotas()">Pagar Cuotas</button>
</div>

<div class="container mt-5" id="cuotasContainer" style="display: none;">
    <h4 class="text-center text-danger">3. Seleccione cuotas</h4>
    <div class="table-responsive d-flex justify-content-center">
        <table class="table table-striped table-bordered text-center w-auto">
            <thead class="table-light">
                <tr><th>N° Cuota</th><th>Importe</th><th>Vencimiento</th><th>Seleccionar</th></tr>
            </thead>
            <tbody id="tablaCuotas">
                <!-- A completar dinámicamente -->
            </tbody>
        </table>
    </div>
    <div class="text-center">
        <button class="btn mt-3" onclick="abrirModalCuentas('cuotas')">Pagar Cuotas Seleccionadas</button>
    </div>
</div>

<div class="modal fade" id="modalCuentas" tabindex="-1">
  <div class="modal-dialog modal-lg modal-dialog-centered">
    <div class="modal-content">
      <form method="post" action="ConfirmarPagoCuotaServlet">
        <div class="modal-header">
          <h5 class="modal-title">Seleccione la cuenta para pagar</h5>
        </div>
        <div class="modal-body">
          <div class="table-responsive">
            <table class="table table-bordered text-center">
              <thead>
                <tr><th>Número</th><th>Tipo</th><th>Saldo</th><th>Seleccionar</th></tr>
              </thead>
              <tbody>
              <%
                java.util.List<entidades.Cuenta> cuentas = (java.util.List<entidades.Cuenta>) request.getAttribute("cuentas");
                if (cuentas != null) {
                    for (entidades.Cuenta cuenta : cuentas) {
              %>
                <tr>
                    <td><%= cuenta.getNumeroCuenta() %></td>
					<td><%= cuenta.getTipoCuentaCuenta() %></td>
					<td>$<%= cuenta.getSaldoCuenta() %></td>
				<td><input type="radio" name="cuentaSeleccionada" value="<%= cuenta.getIdCuenta() %>" required/></td>
                </tr>
              <%
                    }
                } else {
              %>
                <tr><td colspan="4">No hay cuentas para mostrar</td></tr>
              <%
                }
              %>
              </tbody>
            </table>
          </div>
        </div>
        <div class="modal-footer">
          <input type="hidden" name="prestamoId" id="inputPrestamoId">
          <input type="hidden" name="tipoPago" id="inputTipoPago">
          <input type="hidden" name="cuotasSeleccionadas" id="inputCuotasSeleccionadas">
          <button type="submit" class="btn btn-primary">Confirmar Pago</button>
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
        </div>
      </form>
    </div>
  </div>
</div>

<script>
let prestamoSeleccionado = null;

function activarOpciones(idPrestamo) {
    prestamoSeleccionado = idPrestamo;
    document.getElementById("accionesPago").style.display = "block";
    document.getElementById("cuotasContainer").style.display = "none";
}

function cargarCuotas() {
    if (!prestamoSeleccionado) return;
    // Aquí deberías cargar las cuotas reales desde el backend o un servlet
    const cuotas = [
        { numero: 1, importe: 5000, vencimiento: "2025-08-01" },
        { numero: 2, importe: 5000, vencimiento: "2025-09-01" }
    ];

    const tbody = document.getElementById("tablaCuotas");
    tbody.innerHTML = "";
    cuotas.forEach(c => {
        tbody.innerHTML += `
            <tr>
                <td>${c.numero}</td>
                <td>$${c.importe}</td>
                <td>${c.vencimiento}</td>
                <td><input type="checkbox" name="cuotas" value="${c.numero}"></td>
            </tr>
        `;
    });

    document.getElementById("cuotasContainer").style.display = "block";
}

function abrirModalCuentas(tipo) {
    if (!prestamoSeleccionado) return;

    document.getElementById("inputPrestamoId").value = prestamoSeleccionado;
    document.getElementById("inputTipoPago").value = tipo;

    if (tipo === "cuotas") {
        const seleccionadas = Array.from(document.querySelectorAll("input[name='cuotas']:checked"))
            .map(cb => cb.value)
            .join(",");
        document.getElementById("inputCuotasSeleccionadas").value = seleccionadas;
    } else {
        document.getElementById("inputCuotasSeleccionadas").value = "";
    }

    const modal = new bootstrap.Modal(document.getElementById("modalCuentas"));
    modal.show();
}
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
