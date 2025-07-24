package controladorCliente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import negocio.NegocioPrestamo;
import negocio.NegocioCliente;
import negocio.NegocioCuenta;
import negocio.NegocioCuota;
import entidades.Prestamo;
import entidades.Cuenta;
import entidades.Cuota;

@WebServlet("/PagarPrestamoServlet")
public class PagarPrestamoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private NegocioPrestamo negocioPrestamo = new NegocioPrestamo();
    private NegocioCuenta negocioCuenta = new NegocioCuenta();
    private NegocioCuota negocioCuota = new NegocioCuota();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String nombreUsuario = (String) session.getAttribute("ClienteLogueado");

        if (nombreUsuario == null) {
            System.out.println("No hay usuario logueado. Redirigiendo a Login.jsp");
            response.sendRedirect(request.getContextPath() + "/Login.jsp");
            return;
        }

        NegocioCliente negocioCliente = new NegocioCliente();
        int idCliente = negocioCliente.obtenerIdClientePorUsuario(nombreUsuario);

        System.out.println("Usuario logueado: " + nombreUsuario + ", idCliente: " + idCliente);

        String tipoPago = request.getParameter("tipoPago");
        String accion = request.getParameter("accion");

        if ("cuotas".equals(tipoPago)) {
            String[] cuotasSeleccionadas = request.getParameterValues("cuotasSeleccionadas");
            String cuentaSeleccionada = request.getParameter("cuentaSeleccionada");
            String idPrestamoStr = request.getParameter("idPrestamo");

            System.out.println("Proceso pago cuotas:");
            System.out.println("Cuotas seleccionadas: " + (cuotasSeleccionadas != null ? String.join(",", cuotasSeleccionadas) : "null"));
            System.out.println("Cuenta seleccionada: " + cuentaSeleccionada);
            System.out.println("idPrestamo: " + idPrestamoStr);

            if (cuotasSeleccionadas == null || cuotasSeleccionadas.length == 0) {
                request.setAttribute("error", "Debe seleccionar al menos una cuota para pagar.");
                System.out.println("Error: no se seleccionaron cuotas");
            } else if (cuentaSeleccionada == null || cuentaSeleccionada.isEmpty()) {
                request.setAttribute("error", "Debe seleccionar una cuenta para realizar el pago.");
                System.out.println("Error: no se seleccionó cuenta");
            } else if (idPrestamoStr == null || idPrestamoStr.isEmpty()) {
                request.setAttribute("error", "No se pudo identificar el préstamo.");
                System.out.println("Error: no se pudo obtener idPrestamo");
            } else {
                int idPrestamo = Integer.parseInt(idPrestamoStr);
                int idCuenta = Integer.parseInt(cuentaSeleccionada);
                BigDecimal total = BigDecimal.ZERO;

                for (String idCuotaStr : cuotasSeleccionadas) {
                    int idCuota = Integer.parseInt(idCuotaStr);
                    Cuota cuota = negocioCuota.obtenerCuotaPorId(idCuota);
                    if (cuota != null) {
                        System.out.println("Cuota id " + idCuota + " importe: " + cuota.getImporteCuota());
                        total = total.add(cuota.getImporteCuota());
                    } else {
                        System.err.println("No se encontró la cuota con id: " + idCuota);
                    }
                }

                System.out.println("Total a pagar: " + total.toPlainString());

                if (negocioCuenta.validarSaldoSuficiente(total.toPlainString(), idCuenta)) {
                    System.out.println("Saldo suficiente, procediendo a descontar y marcar cuotas...");
                    for (String idCuotaStr : cuotasSeleccionadas) {
                        int idCuota = Integer.parseInt(idCuotaStr);
                        boolean marcado = negocioCuota.marcarCuotaComoPagada(idCuota);
                        System.out.println("Marcar cuota " + idCuota + " como pagada: " + marcado);
                    }
                    boolean descontado = negocioCuenta.descontarPlata(total.toPlainString(), idCliente, idCuenta);
                    System.out.println("Resultado de descontar plata: " + descontado);

                    if (descontado) {
                       List<Cuota> cuotasPendientes = negocioCuota.obtenerCuotasPendientesPorPrestamo(idPrestamo);
                        if (cuotasPendientes == null || cuotasPendientes.isEmpty()) {
                            boolean actualizado = negocioPrestamo.actualizarEstadoPrestamo(idPrestamo, false);
                            System.out.println("Préstamo " + idPrestamo + " actualizado a pagado: " + actualizado);
                        }

                        request.setAttribute("mensaje", "Cuotas pagadas correctamente.");
                    } else {
                        request.setAttribute("error", "No se pudo descontar el monto de la cuenta.");
                    }
                } else {
                    request.setAttribute("error", "Saldo insuficiente en la cuenta seleccionada para realizar el pago.");
                    System.out.println("Error: saldo insuficiente en cuenta " + idCuenta);
                }

                request.setAttribute("totalAPagar", total);
                request.setAttribute("cuotasSeleccionadasMarcadas", cuotasSeleccionadas);
                request.setAttribute("cuentaSeleccionadaMarcada", cuentaSeleccionada);
                request.setAttribute("idPrestamoSeleccionado", idPrestamo);
            }
        } else if ("verCuotas".equals(accion)) {
            String idPrestamoStr = request.getParameter("idPrestamo");
            if (idPrestamoStr != null) {
                int idPrestamo = Integer.parseInt(idPrestamoStr);
                List<Cuota> cuotas = negocioCuota.obtenerCuotasPendientesPorPrestamo(idPrestamo);
                request.setAttribute("cuotas", cuotas);
                request.setAttribute("idPrestamoSeleccionado", idPrestamo);
                System.out.println("Mostrando cuotas pendientes para préstamo " + idPrestamo);
            }
        }

        List<Prestamo> prestamos = negocioPrestamo.obtenerPrestamosPendientesPorCliente(idCliente);
        List<Cuenta> cuentas = negocioCuenta.obtenerCuentasPorCliente(idCliente);
        request.setAttribute("prestamos", prestamos);
        request.setAttribute("cuentas", cuentas);

        if (request.getAttribute("cuotas") == null) {
            request.setAttribute("cuotas", null);
        }

        request.getRequestDispatcher("/Formularios/ModoCliente/Prestamos/PagarPrestamo.jsp").forward(request, response);
    }
}
