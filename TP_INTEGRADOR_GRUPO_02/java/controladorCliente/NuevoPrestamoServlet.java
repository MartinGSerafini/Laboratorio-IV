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

import entidades.Cuenta;

import negocio.NegocioCliente;
import negocio.NegocioCuenta;

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
            String numeroCuenta = request.getParameter("cuentaSeleccionada");
            BigDecimal montoPrestamo = new BigDecimal(request.getParameter("montoPrestamo"));
            int cuotas = Integer.parseInt(request.getParameter("cuotasSeleccionadas"));
            BigDecimal montoPorCuota = new BigDecimal(request.getParameter("montoCuotaSeleccionada"));
            Date fechaActual = new Date(System.currentTimeMillis());

            HttpSession session = request.getSession();
            String nombreUsuario = (String) session.getAttribute("ClienteLogueado");

            NegocioCliente negocioCliente = new NegocioCliente();
            int idCliente = negocioCliente.obtenerIdClientePorUsuario(nombreUsuario);

            //  Falta crear y guardar el préstamo en base de datos

            request.setAttribute("mensajeExito", true);

        doGet(request, response);
        }
    }
}
