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
            response.sendRedirect(request.getContextPath() + "/Login.jsp");
            return;
        }

        NegocioCliente negocioCliente = new NegocioCliente();
        int idCliente = negocioCliente.obtenerIdClientePorUsuario(nombreUsuario);

        List<Prestamo> prestamos = negocioPrestamo.obtenerPrestamosPendientesPorCliente(idCliente);
        List<Cuenta> cuentas = negocioCuenta.obtenerCuentasPorCliente(idCliente);
        request.setAttribute("prestamos", prestamos);
        request.setAttribute("cuentas", cuentas);

        String accion = request.getParameter("accion");
        String idPrestamoStr = request.getParameter("idPrestamo");

        if (request.getParameter("pagarCompleto") != null) {
            int prestamoId = Integer.parseInt(request.getParameter("prestamoId"));
            int cuentaId = Integer.parseInt(request.getParameter("cuentaSeleccionada"));
            BigDecimal importeTotal = negocioPrestamo.obtenerImporteTotalPrestamo(prestamoId);

            if (negocioCuenta.validarSaldoSuficiente(importeTotal.toPlainString(), cuentaId)) {
                negocioPrestamo.pagarPrestamoCompleto(prestamoId);
                negocioCuota.pagarPrestamoCompleto(prestamoId);
                negocioCuenta.descontarPlata(importeTotal.toPlainString(), idCliente, cuentaId);
                request.setAttribute("mensaje", "Préstamo pagado completamente.");
            } else {
                request.setAttribute("error", "Saldo insuficiente para pago completo.");
            }
        }

        if ("cuotas".equals(request.getParameter("tipoPago"))) {
            String[] cuotasSeleccionadas = request.getParameterValues("cuotasSeleccionadas");
            String idCuentaStr = request.getParameter("cuentaSeleccionada");

            if (cuotasSeleccionadas != null && idPrestamoStr != null && idCuentaStr != null) {
                int idPrestamo = Integer.parseInt(idPrestamoStr);
                int cuentaId = Integer.parseInt(idCuentaStr);

                BigDecimal total = BigDecimal.ZERO;
                for (String idCuotaStr : cuotasSeleccionadas) {
                    int idCuota = Integer.parseInt(idCuotaStr);
                    Cuota cuota = negocioCuota.obtenerCuotaPorId(idCuota);
                    total = total.add(cuota.getImporteCuota());
                }

                if (negocioCuenta.validarSaldoSuficiente(total.toPlainString(), cuentaId)) {
                    for (String idCuotaStr : cuotasSeleccionadas) {
                        int idCuota = Integer.parseInt(idCuotaStr);
                        negocioCuota.marcarCuotaComoPagada(idCuota);
                    }
                    negocioCuenta.descontarPlata(total.toPlainString(), idCliente, cuentaId);
                    request.setAttribute("mensaje", "Cuotas pagadas correctamente.");
                } else {
                    request.setAttribute("error", "Saldo insuficiente para pago de cuotas.");
                }
            }
        }

        if ("mostrarTotal".equals(accion)) {
            String[] cuotasSeleccionadas = request.getParameterValues("cuotasSeleccionadas");
            String cuentaSeleccionada = request.getParameter("cuentaSeleccionada");

            if (cuotasSeleccionadas != null && idPrestamoStr != null) {
                BigDecimal total = BigDecimal.ZERO;
                for (String idCuotaStr : cuotasSeleccionadas) {
                    int idCuota = Integer.parseInt(idCuotaStr);
                    Cuota cuota = negocioCuota.obtenerCuotaPorId(idCuota);
                    total = total.add(cuota.getImporteCuota());
                }

                request.setAttribute("totalAPagar", total);
                request.setAttribute("cuotasSeleccionadasMarcadas", cuotasSeleccionadas);
                request.setAttribute("cuentaSeleccionadaMarcada", cuentaSeleccionada);

                int idPrestamo = Integer.parseInt(idPrestamoStr);
                List<Cuota> cuotas = negocioCuota.obtenerCuotasPendientesPorPrestamo(idPrestamo);
                request.setAttribute("cuotas", cuotas);
                request.setAttribute("idPrestamoSeleccionado", idPrestamo);
            }
        }


        if ("verCuotas".equals(accion) || "pagarCuotas".equals(accion) || "cuotas".equals(request.getParameter("tipoPago"))) {
            if (idPrestamoStr != null) {
                int idPrestamo = Integer.parseInt(idPrestamoStr);
                List<Cuota> cuotas = negocioCuota.obtenerCuotasPendientesPorPrestamo(idPrestamo);
                request.setAttribute("cuotas", cuotas);
                request.setAttribute("idPrestamoSeleccionado", idPrestamo);
            }
        }

        request.getRequestDispatcher("/Formularios/ModoCliente/Prestamos/PagarPrestamo.jsp").forward(request, response);
    }
}

