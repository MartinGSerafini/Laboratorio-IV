<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="entidades.Cuenta" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Nuevo Préstamo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Formularios/z-CSS/ModoClienteCSS/Prestamos/Prestamos.css">
</head>

<body>
<nav class="navbar navbar-expand-lg navbar-custom px-4">
    <div class="container-fluid">
        <div class="d-flex align-items-center">
            <div class="dropdown me-3">
                <button class="btn dropdown-toggle" type="button" data-bs-toggle="dropdown">☰</button>
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


<!-- prestamos disponibles -->
<div class="main-content">
    <h2>Solicitar un préstamo</h2>
    <form action="${pageContext.request.contextPath}/NuevoPrestamoServlet" method="get" class="d-flex flex-column align-items-center">
        <input type="hidden" name="accion" value="seleccionarPrestamo">

        <div class="d-flex gap-3 mb-3">
            <!-- Select de montos -->
            <div>
                <label for="monto" class="form-label">Monto:</label>
                <select class="form-select" name="monto" id="monto" required onchange="habilitarCuotas()">
                    <option value="" disabled selected>Seleccioná un monto</option>
                    <option value="80000">$80.000</option>
                    <option value="90000">$90.000</option>
                    <option value="100000">$100.000</option>
                    <option value="120000">$120.000</option>
                </select>
            </div>

            <!-- Select de cuotas -->
            <div>
                <label for="cuota" class="form-label">Cuotas:</label>
                <select class="form-select" name="cuota" id="cuota" required disabled onchange="mostrarValorCuota()">
                
                    <option value="" disabled selected>Seleccioná un plan</option>
                    <option value="4">4 cuotas</option>
                    <option value="6">6 cuotas</option>
                    <option value="8">8 cuotas</option>
                    <option value="12">12 cuotas</option>
                </select>
                <small id="valorCuotaTexto" class="text-muted mt-1 d-block"></small>
            </div>
        </div>

        <button type="submit" class="btn btn-primary">Solicitar préstamo</button>
    </form>
</div>


<div class="container mt-5" id="cuentasSection"
     style="<%= request.getAttribute("cuentas") == null ? "display: none;" : "" %>">


<!-- cuotas disponibles -->
<div class="main-content" id="cuotasSection" 
    <% if (request.getAttribute("cuotasDisponibles") == null) { %>
        style="display: none;"
    <% } %>
>
    <h2>Cuotas disponibles para el préstamo </h2>
    <div class="button-grid">
        <% 
            LinkedHashMap<Integer, Double> cuotas = (LinkedHashMap<Integer, Double>) request.getAttribute("cuotasDisponibles");
            if (cuotas != null) {
                NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("es", "AR"));
                for (Map.Entry<Integer, Double> entry : cuotas.entrySet()) {
                	 int numeroCuotas = entry.getKey();
                     double valorCuota = entry.getValue();
         %>
                     <a href="#" class="btn" onclick="mostrarCuentas()">Pagar en <%= numeroCuotas %> cuotas de <%= formatter.format(valorCuota) %></a>
         <%
                 }
             }
         %>
      </div>
</div>

<!-- Cuentas Disponibles -->
<div class="container mt-5" id="cuentasSection" style="display: none;">

    <h3 class="text-center mb-4 text-danger">Cuenta a seleccionar</h3>
    <div class="table-responsive d-flex justify-content-center">
        <table class="table table-striped table-bordered text-center w-auto">
            <thead class="table-light">
            <tr>
                <th>Nro Cuenta</th><th>Tipo</th>
                <th>CBU</th><th>Saldo</th><th></th>
            </tr>
            </thead>
            <tbody>
            <%
                List<Cuenta> listaCuentas = (List<Cuenta>) request.getAttribute("cuentas");
                if (listaCuentas != null && !listaCuentas.isEmpty()) {
                    for (Cuenta c : listaCuentas) {
            %>
                <tr>
                    <td><%= c.getNumeroCuenta() %></td>
                    <td><%= c.getTipoCuentaCuenta() %></td>
                    <td><%= c.getCbuCuenta() %></td>
                    <td>$<%= c.getSaldoCuenta() %></td>
                    <td><button class="btn btn-success btn-sm">Usar</button></td>
                </tr>
            <%
                    }
                } else {
            %>
                <tr>
                    <td colspan="5">No se encontraron cuentas para mostrar.</td>
                </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>
</div>

<!-- JavaScript -->
<script>
function habilitarCuotas() {
    const montoSelect = document.getElementById("monto");
    const cuotasSelect = document.getElementById("cuota");
    const valorCuotaTexto = document.getElementById("valorCuotaTexto");

    if (montoSelect.value) {
        cuotasSelect.disabled = false;
    } else {
        cuotasSelect.disabled = true;
        cuotasSelect.value = "";
        valorCuotaTexto.textContent = "";
    }
}

function mostrarValorCuota() {
    const montoSelect = document.getElementById("monto");
    const cuotasSelect = document.getElementById("cuota");
    const valorCuotaTexto = document.getElementById("valorCuotaTexto");

    const monto = parseFloat(montoSelect.value);
    const cuotas = parseInt(cuotasSelect.value);

    console.log("Monto:", monto, "Cuotas:", cuotas);

    if (!isNaN(monto) && !isNaN(cuotas) && cuotas > 0) {
        const valorCuota = monto / cuotas;
        console.log("Valor cuota calculado:", valorCuota);

        let valorCuotaFormateado = "";

        try {
            valorCuotaFormateado = valorCuota.toLocaleString("es-AR", {
                style: "currency",
                currency: "ARS",
                minimumFractionDigits: 2
            });
        } catch (e) {
            console.error("Error al formatear con toLocaleString:", e);
            valorCuotaFormateado = "$" + valorCuota.toFixed(2).replace(".", ",");
        }

        valorCuotaTexto.textContent = `Valor cuota fija mensual: ${valorCuotaFormateado}`;
        console.log("Texto actualizado a:", valorCuotaTexto.textContent);
    } else {
        valorCuotaTexto.textContent = "";
        console.log("No se pudo calcular la cuota.");
    }
}

</script>


<!--  
<div class="main-content">
        <a href="#" class="btn">Aceptar</a>
        <a href="#" class="btn">Cancelar</a>
</div>
-->

<div class="main-content">
	<form id="datosPrestamo" action="${pageContext.request.contextPath}/NuevoPrestamoServlet" method="post">
	    <input type="hidden" name="accion" value="Aceptar">
	    <input type="hidden" name="cuentaSeleccionada" id="cuentaSeleccionada">
	    <input type="hidden" name="montoPrestamo" value="${monto}">
	    <input type="hidden" name="cuotasSeleccionadas" id="cuotasSeleccionadas">
	    <input type="hidden" name="montoCuotaSeleccionada" id="montoCuotaSeleccionada">
	    
	    <button type="button" class="btn" onclick="abrirModalConfirmacion()" >Aceptar</button>
	    <a href="${pageContext.request.contextPath}/MenuClienteServlet" class="btn">Cancelar</a>
	</form>
</div>

<!-- Modal Confirmación -->
<div class="modal fade" id="modalConfirmacion" tabindex="-1" aria-labelledby="modalConfirmacionLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header bg-danger text-white">
        <h5 class="modal-title" id="modalErrorLabel">Confirmar Prestamo</h5>
      </div>
      <div class="modal-body">
			¿Desea confirmar la solicitud del préstmao?
      </div>
      <div class="modal-footer">
			<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
        	<button type="button" class="btn btn-danger" onclick="confirmarPrestamo()">Aceptar</button>
      </div>
    </div>
  </div>
</div>

<!-- Modal Éxito -->
<div class="modal fade" id="modalExito" tabindex="-1" aria-labelledby="modalExitoLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header bg-success text-white">
        <h5 class="modal-title" id="modalExitoLabel">Éxito</h5>
      </div>
      <div class="modal-body">
			Prestamo solicitado con éxito.
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-success" data-bs-dismiss="modal" onclick="document.getElementById('formReset').submit();">Aceptar</button>
      </div>
    </div>
  </div>
</div>

<!-- Script para manejar el modal de confirmacion -->
<script>
    function abrirModalConfirmacion() {
        document.getElementById("modalConfirmacion").style.display = "block";
    }

    function confrimarPrestamo() {
        document.getElementById("datosPrestamo").submit();
    }
</script>


<!-- Bootstrap Bundle -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
