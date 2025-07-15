package controladorCliente;

import java.io.IOException;
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
				
			// --- Lógica para obtener el cliente y sus cuentas ---
			HttpSession session = request.getSession();
			String nombreUsuario = (String) session.getAttribute("nombreUsuario");
			
			NegocioCliente negocioCliente = new NegocioCliente();
			int idCliente = negocioCliente.obtenerIdClientePorUsuario(nombreUsuario);
					
			NegocioCuenta negocioCuenta = new NegocioCuenta();
			List<Cuenta> cuentas = negocioCuenta.obtenerCuentasPorCliente(idCliente);
			
			request.setAttribute("cuentas", cuentas);
			
			
		if ("seleccionarPrestamo".equals(accion)) {

			
			// --- Lógica para calcular y pasar las cuotas al JSP ---
            double montoPrestamo = Double.parseDouble(request.getParameter("monto")); // Obtener el monto del préstamo seleccionado 
            // Mapa para almacenar las cuotas y sus valores
            LinkedHashMap<Integer, Double> cuotasDisponibles = new LinkedHashMap<>(); //clave: nro de cuotas, valor: monto de cada cuota

            cuotasDisponibles.put(4, montoPrestamo / 4);  
            cuotasDisponibles.put(6, montoPrestamo / 6);  
            cuotasDisponibles.put(8, montoPrestamo / 8);  
            cuotasDisponibles.put(12, montoPrestamo / 12);

            request.setAttribute("cuotasDisponibles", cuotasDisponibles);
			
			request.getRequestDispatcher("/Formularios/ModoCliente/Prestamos/NuevoPrestamo.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		NegocioMovimientos negocioMovimientos = new NegocioMovimientos();
		
		
		
		doGet(request, response);
	}

}
