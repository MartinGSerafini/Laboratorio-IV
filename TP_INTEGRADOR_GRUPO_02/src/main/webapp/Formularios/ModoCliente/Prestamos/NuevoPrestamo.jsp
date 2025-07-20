<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, entidades.Cuenta, java.text.NumberFormat, java.util.Locale" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8" />
    <title>Nuevo Préstamo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600&display=swap" rel="stylesheet" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Formularios/z-CSS/ModoClienteCSS/Prestamos/Prestamos.css" />
    <style>
        .btn-seleccionado {
            background-color: #dc3545 !important;
            color: white !important;
            border-color: #dc3545 !important;
        }
    </style>
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

<div class="main-content">
    <h2 class="text-center text-danger mb-4">Solicitar un préstamo</h2>
    <form id="formPrestamo" action="${pageContext.request.contextPath}/NuevoPrestamoServlet" method="post" class="d-flex flex-column align-items-center">
        <input type="hidden" name="accion" value="Aceptar" />
        <input type="hidden" name="cuentaSeleccionada" id="cuentaSeleccionada" />
        <input type="hidden" name="cuotasSeleccionadas" id="cuotasSeleccionadas" />
        <input type="hidden" name="montoCuotaSeleccionada" id="montoCuotaSeleccionada" />
        <input type="hidden" name="montoPrestamo" id="montoPrestamo" value="" />

        <div class="d-flex gap-3 mb-3">
            <div>
                <label for="monto" class="form-label">Monto:</label>
                <select class="form-select" name="monto" id="monto" required>
                    <option value="" disabled selected>Seleccioná un monto</option>
                    <option value="80000">$80.000</option>
                    <option value="90000">$90.000</option>
                    <option value="100000">$100.000</option>
                    <option value="120000">$120.000</option>
                </select>
            </div>

            <div>
                <label for="cuotas" class="form-label">Cuotas:</label>
                <select class="form-select" id="cuotas" required disabled>
                    <option value="" disabled selected>Seleccioná un plan</option>
                    <option value="4">4 cuotas (5%)</option>
                    <option value="6">6 cuotas (7%)</option>
                    <option value="8">8 cuotas (10%)</option>
                    <option value="12">12 cuotas (15%)</option>
                </select>
            </div>
        </div>
    </form>
</div>

<div class="container mt-5" id="cuentasSection">
    <div class="text-center mb-4 fw-semibold fs-5">
        <small id="valorCuotaTexto" class="d-block text-dark"></small>
    </div>

    <h3 class="text-center mb-4 text-danger">Seleccione la cuenta para recibir el préstamo</h3>
    <div class="table-responsive d-flex justify-content-center">
        <table class="table table-striped table-bordered text-center w-auto">
            <thead class="table-light">
                <tr>
                    <th>Nro Cuenta</th>
                    <th>Tipo</th>
                    <th>CBU</th>
                    <th>Saldo</th>
                    <th>Seleccionar</th>
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
                    <td><%= String.format("%.2f", c.getSaldoCuenta()) %></td>
                    <td>
                        <button type="button" class="btn btn-success btn-sm" onclick="seleccionarCuenta(this, '<%= c.getNumeroCuenta() %>')">Usar</button>
                    </td>
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

    <div class="d-flex justify-content-center mt-4">
        <button type="button" class="btn btn-primary" id="btnSeleccionar" disabled onclick="enviarFormulario()">Solicitar préstamo</button>
    </div>
</div>

<script>
document.addEventListener("DOMContentLoaded", function () {
    const montoSelect = document.getElementById("monto");
    const cuotasSelect = document.getElementById("cuotas");
    const btnSeleccionar = document.getElementById("btnSeleccionar");
    const valorCuotaTexto = document.getElementById("valorCuotaTexto");
    const cuentaSeleccionadaInput = document.getElementById("cuentaSeleccionada");

    let botonSeleccionado = null;

    function formatearNumero(valor) {
        return valor.toFixed(2);
    }

    function actualizarBoton() {
        btnSeleccionar.disabled = !(montoSelect.value && cuotasSelect.value && cuentaSeleccionadaInput.value);
    }

    montoSelect.addEventListener("change", function () {
        cuotasSelect.disabled = false;
        cuotasSelect.value = "";
        valorCuotaTexto.innerHTML = "";
        cuentaSeleccionadaInput.value = "";
        btnSeleccionar.disabled = true;

        if (botonSeleccionado) {
            botonSeleccionado.classList.remove("btn-seleccionado", "btn-danger");
            botonSeleccionado.classList.add("btn-success");
            botonSeleccionado = null;
        }
    });

    cuotasSelect.addEventListener("change", function () {
        const monto = parseFloat(montoSelect.value);
        const cuotas = parseInt(cuotasSelect.value);

        if (!isNaN(monto) && !isNaN(cuotas)) {
            let recargo = 0;
            switch (cuotas) {
                case 4: recargo = 0.05; break;
                case 6: recargo = 0.07; break;
                case 8: recargo = 0.10; break;
                case 12: recargo = 0.15; break;
            }

            const totalConRecargo = monto * (1 + recargo);
            const valorCuota = totalConRecargo / cuotas;

            valorCuotaTexto.innerHTML = "Total a devolver: " + formatearNumero(totalConRecargo) + ".<br>" +
            "Serán " + cuotas + " cuotas de " + formatearNumero(valorCuota) + " cada una.";


            document.getElementById("montoPrestamo").value = totalConRecargo.toFixed(2);
            document.getElementById("cuotasSeleccionadas").value = cuotas;
            document.getElementById("montoCuotaSeleccionada").value = valorCuota.toFixed(2);

            actualizarBoton();
        } else {
            valorCuotaTexto.textContent = "";
            btnSeleccionar.disabled = true;
        }
    });

    window.seleccionarCuenta = function(boton, numeroCuenta) {
        if (botonSeleccionado) {
            botonSeleccionado.classList.remove("btn-seleccionado", "btn-danger");
            botonSeleccionado.classList.add("btn-success");
        }

        boton.classList.remove("btn-success");
        boton.classList.add("btn-seleccionado");
        boton.classList.add("btn-danger");

        cuentaSeleccionadaInput.value = numeroCuenta;
        actualizarBoton();
    };

    window.enviarFormulario = function() {
        document.getElementById("formPrestamo").submit();
    };
});
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
