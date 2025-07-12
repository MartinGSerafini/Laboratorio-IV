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


@WebServlet("/NuevoPrestamoServlet")
public class NuevoPrestamoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String accion = request.getParameter("accion");

		if ("seleccionarPrestamo".equals(accion)) {
			/*//obtener id del cliente desde la sesión
			HttpSession session = request.getSession();
            Integer idClienteObj = (Integer) session.getAttribute("idCliente"); 
            int idCliente = idClienteObj.intValue(); 
			
			//obtener cuentas del cliente
			NegocioCuenta negocio = new NegocioCuenta();
			List<Cuenta> cuentas = negocio.obtenerCuentasPorCliente(idCliente);
			request.setAttribute("cuentas", cuentas);*/
			
			// --- Lógica para calcular y pasar las cuotas al JSP ---
            // Obtener el monto del préstamo seleccionado
            double montoPrestamo = Double.parseDouble(request.getParameter("monto"));

            // Creamos un mapa para almacenar las cuotas y sus valores..
            LinkedHashMap<Integer, Double> cuotasDisponibles = new LinkedHashMap<>(); //clave: nro de cuotas, valor: monto de cada cuota

            cuotasDisponibles.put(4, montoPrestamo / 4);  
            cuotasDisponibles.put(6, montoPrestamo / 6);  
            cuotasDisponibles.put(8, montoPrestamo / 8);  
            cuotasDisponibles.put(12, montoPrestamo / 12);

            // Pasamos el mapa de cuotas al JSP para que pueda mostrarlas.
            request.setAttribute("cuotasDisponibles", cuotasDisponibles);
			

			request.getRequestDispatcher("/Formularios/ModoCliente/Prestamos/NuevoPrestamo.jsp").forward(request, response);
		}
		
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
