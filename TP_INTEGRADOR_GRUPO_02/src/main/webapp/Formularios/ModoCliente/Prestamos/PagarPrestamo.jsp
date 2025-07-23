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

<div class="container mt-5">
    <h2 class="text-center text-danger">1. Seleccione un préstamo para pagar</h2>

    <table class="table table-bordered w-auto text-center mx-auto">
        <thead class="table-light">
            <tr>
                <th>Fecha Solicitud</th>
                <th>Importe Solicitado</th>
                <th>Importe Total</th>
                <th>Plazo de Pago</th>
                <th>Monto Cuota</th>
                <th>Cuenta Depósito</th>
                <th>Acción</th>
            </tr>
        </thead>
        <tbody>
        <%
            java.util.List<entidades.Prestamo> prestamos = (java.util.List<entidades.Prestamo>) request.getAttribute("prestamos");
            if (prestamos != null && !prestamos.isEmpty()) {
                for (entidades.Prestamo prestamo : prestamos) {
        %>
            <tr>
                <td><%= new java.text.SimpleDateFormat("dd/MM/yyyy").format(prestamo.getFechaSolicitudPres()) %></td>
                <td>$<%= prestamo.getImporteSolicitadoPres() %></td>
                <td>$<%= prestamo.getImporteTotalPres() %></td>
                <td><%= prestamo.getPlazoMesesPres() %></td>
                <td>$<%= prestamo.getMontoCuotaPres() %></td>
                <td><%= prestamo.getIdCuentaDepositoPres() %></td>
                <td>
                    <form method="get" action="${pageContext.request.contextPath}/PagarPrestamoServlet" style="margin:0;">
                        <input type="hidden" name="accion" value="verCuotas">
                        <input type="hidden" name="idPrestamo" value="<%= prestamo.getIdPrestamo() %>">
                        <button type="submit" class="btn btn-outline-danger btn-sm">Pagar</button>
                    </form>
                </td>
            </tr>
        <%
                }
            } else {
        %>
            <tr><td colspan="7">No hay préstamos para mostrar</td></tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>

<%
    java.util.List<entidades.Cuota> cuotas = (java.util.List<entidades.Cuota>) request.getAttribute("cuotas");
    Integer idPrestamoSeleccionado = (Integer) request.getAttribute("idPrestamoSeleccionado");
    java.math.BigDecimal totalAPagar = (java.math.BigDecimal) request.getAttribute("totalAPagar");
    String[] seleccionadas = (String[]) request.getAttribute("cuotasSeleccionadasMarcadas");

    if (cuotas != null && !cuotas.isEmpty() && idPrestamoSeleccionado != null) {
%>
<div class="container mt-5">
    <h3 class="text-danger text-center">2. Seleccione las cuotas que desea pagar</h3>

    <form method="get" action="${pageContext.request.contextPath}/PagarPrestamoServlet">
        <input type="hidden" name="accion" value="pagarCuotas">
        <input type="hidden" name="idPrestamo" value="<%= idPrestamoSeleccionado %>">

        <%
            if (totalAPagar != null) {
        %>
            <div class="text-center mb-3">
                <h4 class="text-success">Total a pagar: $<%= totalAPagar %></h4>
            </div>
        <%
            }
        %>

        <table class="table table-striped table-bordered text-center w-auto mx-auto">
            <thead class="table-light">
                <tr>
                    <th>N° Cuota</th>
                    <th>Importe</th>
                    <th>Vencimiento</th>
                    <th>Seleccionar</th>
                </tr>
            </thead>
            <tbody>
            <%
                for (entidades.Cuota cuota : cuotas) {
                    boolean marcada = false;
                    if (seleccionadas != null) {
                        for (String idSel : seleccionadas) {
                            if (Integer.parseInt(idSel) == cuota.getIdCuota()) {
                                marcada = true;
                                break;
                            }
                        }
                    }
            %>
                <tr>
                    <td><%= cuota.getNumeroCuota() %></td>
                    <td>$<%= cuota.getImporteCuota() %></td>
                    <td><%= new java.text.SimpleDateFormat("dd/MM/yyyy").format(cuota.getFechaVencCuota()) %></td>
                    <td>
                        <input type="checkbox" name="cuotasSeleccionadas" value="<%= cuota.getIdCuota() %>" <%= marcada ? "checked" : "" %>>
                    </td>
                </tr>
            <%
                }
            %>
            </tbody>
        </table>

        <div class="text-center mt-2">
            <button type="submit" name="accion" value="mostrarTotal" class="btn btn-outline-secondary">Mostrar Total</button>
        </div>

        <h3 class="text-danger text-center mt-5">3. Seleccione la cuenta para el pago</h3>

        <table class="table table-bordered w-auto text-center mx-auto">
            <thead class="table-light">
                <tr>
                    <th>Número</th>
                    <th>Tipo</th>
                    <th>Saldo</th>
                    <th>Seleccionar</th>
                </tr>
            </thead>
            <tbody>
            <%
                java.util.List<entidades.Cuenta> cuentas = (java.util.List<entidades.Cuenta>) request.getAttribute("cuentas");
                if (cuentas != null && !cuentas.isEmpty()) {
                    for (entidades.Cuenta cuenta : cuentas) {
            %>
                <tr>
                    <td><%= cuenta.getNumeroCuenta() %></td>
                    <td><%= cuenta.getTipoCuentaCuenta() %></td>
                    <td>$<%= cuenta.getSaldoCuenta() %></td>
                    <td>
                        <input type="radio" name="cuentaSeleccionada" value="<%= cuenta.getIdCuenta() %>" required>
                    </td>
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

        <div class="text-center mt-4 mb-5">
            <button type="submit" class="btn btn-danger btn-lg">Confirmar Pago de Cuotas</button>
        </div>
    </form>
</div>
<% } %>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
