package controladorCliente;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

import negocio.NegocioPrestamo;
import negocio.NegocioCliente;
import negocio.NegocioCuenta;
import negocio.NegocioCuota;
import entidades.Prestamo;
import entidades.Cuenta;

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
        
        // PAGAR PRESTAMO COMPLETO
        if(request.getParameter("prestamoId")!= null && request.getParameter("cuentaSeleccionada")!= null) {
        	int prestamoSeleccionado = Integer.parseInt(request.getParameter("prestamoId"));
        	int cuentaSeleccionada = Integer.parseInt(request.getParameter("cuentaSeleccionada"));
        	System.out.println("PRESTAMO SELECCIONADO: " + prestamoSeleccionado);
        	System.out.println("CUENTA SELECCIONADA: " + cuentaSeleccionada);
        	// FALTA VALIDAR QUE EL SALDO DE LA CUENTA SEA SUFICIENTE
        	if(negocioPrestamo.pagarPrestamoCompleto(prestamoSeleccionado) && negocioCuota.pagarPrestamoCompleto(prestamoSeleccionado)) {
        		// ARREGLAR ESTA PARTE
        		System.out.println("PRESTAMO PAGADO CON EXITO");
        	}
        	// FALTA DESCONTAR PLATA DE LA CUENTA
        }

        request.getRequestDispatcher("/Formularios/ModoCliente/Prestamos/PagarPrestamo.jsp").forward(request, response);
    }
}
