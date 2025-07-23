package controladorCliente;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.RoundingMode;

import entidades.Cuenta;
import negocio.NegocioCliente;
import negocio.NegocioCuenta;
import negocio.NegocioPrestamo;

@WebServlet("/NuevoPrestamoServlet")
public class NuevoPrestamoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String nombreUsuario = (String) session.getAttribute("ClienteLogueado");

        if (nombreUsuario == null) {
            response.sendRedirect(request.getContextPath() + "/Login.jsp");
            return;
        }

        NegocioCliente negocioCliente = new NegocioCliente();
        int idCliente = negocioCliente.obtenerIdClientePorUsuario(nombreUsuario);

        NegocioCuenta negocioCuenta = new NegocioCuenta();
        List<Cuenta> cuentas = negocioCuenta.obtenerCuentasPorCliente(idCliente);

        request.setAttribute("cuentas", cuentas);
        request.getRequestDispatcher("/Formularios/ModoCliente/Prestamos/NuevoPrestamo.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if ("Aceptar".equalsIgnoreCase(accion)) {
            try {
                int idCuenta = Integer.parseInt(request.getParameter("cuentaSeleccionada"));
                BigDecimal importeTotal = new BigDecimal(request.getParameter("montoPrestamo"));
                int cuotas = Integer.parseInt(request.getParameter("cuotasSeleccionadas"));
                BigDecimal montoCuota = new BigDecimal(request.getParameter("montoCuotaSeleccionada"));
                Date fechaActual = new Date(System.currentTimeMillis());

                HttpSession session = request.getSession();
                String nombreUsuario = (String) session.getAttribute("ClienteLogueado");

                NegocioCliente negocioCliente = new NegocioCliente();
                int idCliente = negocioCliente.obtenerIdClientePorUsuario(nombreUsuario);

                BigDecimal recargo = getRecargoPorCuotas(cuotas);
                BigDecimal divisor = BigDecimal.ONE.add(recargo);
                BigDecimal importeSolicitado = importeTotal.divide(divisor, 2, RoundingMode.HALF_UP);

                NegocioPrestamo negocioPrestamo = new NegocioPrestamo();
                int idPrestamoGenerado = negocioPrestamo.insertarPrestamo(
                    idCliente,
                    idCuenta,
                    importeSolicitado,
                    importeTotal,
                    cuotas,
                    montoCuota,
                    fechaActual
                );

                if (idPrestamoGenerado > 0) {
                    negocioPrestamo.insertarCuotas(idPrestamoGenerado, cuotas, montoCuota, fechaActual);
                    request.setAttribute("mensaje", "exito");
                } else {
                    request.setAttribute("mensaje", "error");
                }
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("mensajeError", "Ocurri� un error inesperado: " + e.getMessage());
            }
            doGet(request, response);
        }
    }

    private BigDecimal getRecargoPorCuotas(int cuotas) {
        switch (cuotas) {
            case 4: return BigDecimal.valueOf(0.05);
            case 6: return BigDecimal.valueOf(0.07);
            case 8: return BigDecimal.valueOf(0.10);
            case 12: return BigDecimal.valueOf(0.15);
            default: return BigDecimal.ZERO;
        }
    }
}
