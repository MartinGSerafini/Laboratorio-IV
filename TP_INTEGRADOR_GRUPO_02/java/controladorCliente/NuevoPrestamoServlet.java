package controladorCliente;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Cliente;
import entidades.Cuenta;
import entidades.Prestamo;
import negocio.NegocioCliente;
import negocio.NegocioCuenta;
import negocio.NegocioMovimientos;


@WebServlet("/NuevoPrestamoServlet")
public class NuevoPrestamoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String accion = request.getParameter("accion");
				
			HttpSession session = request.getSession();
			String nombreUsuario = (String) session.getAttribute("ClienteLogueado");
			
			NegocioCliente negocioCliente = new NegocioCliente();
			int idCliente = negocioCliente.obtenerIdClientePorUsuario(nombreUsuario);
					
			NegocioCuenta negocioCuenta = new NegocioCuenta();
			List<Cuenta> cuentas = negocioCuenta.obtenerCuentasPorCliente(idCliente);
			
			request.setAttribute("cuentas", cuentas);
			
			
		if ("seleccionarPrestamo".equals(accion)) {

			
            double montoPrestamo = Double.parseDouble(request.getParameter("monto")); 
            LinkedHashMap<Integer, Double> cuotasDisponibles = new LinkedHashMap<>();

            cuotasDisponibles.put(4, montoPrestamo / 4);  
            cuotasDisponibles.put(6, montoPrestamo / 6);  
            cuotasDisponibles.put(8, montoPrestamo / 8);  
            cuotasDisponibles.put(12, montoPrestamo / 12);

            request.setAttribute("cuotasDisponibles", cuotasDisponibles);
			
			request.getRequestDispatcher("/Formularios/ModoCliente/Prestamos/NuevoPrestamo.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String accion = request.getParameter("accion");
		if ("aceptar".equals(accion)){
			
			//datos traidos del fromulario
			String numeroCuenta = request.getParameter("cuentaSeleccionada");
			BigDecimal montoPrestamo = new BigDecimal(request.getParameter("montoPrestamo"));
			int cuotas = Integer.parseInt(request.getParameter("cuotasSeleccionadas"));
			BigDecimal montoPorCuota = new BigDecimal(request.getParameter("montoCuotaSeleccionada"));
			Date fechaActual = new Date(System.currentTimeMillis());
			
			//obtener id del cliente logueado
			HttpSession session = request.getSession();
			String nombreUsuario = (String) session.getAttribute("ClienteLogueado");
			
			NegocioCliente negocioCliente = new NegocioCliente();
			int idCliente = negocioCliente.obtenerIdClientePorUsuario(nombreUsuario);
			
			//obtener cuenta meidante id
			//armar la entidad prestamo
			//registrar prestamo
			//registrar movimiento
			 
			//mostrar mensaje de éxito
			
		}
		doGet(request, response);
	}

}
