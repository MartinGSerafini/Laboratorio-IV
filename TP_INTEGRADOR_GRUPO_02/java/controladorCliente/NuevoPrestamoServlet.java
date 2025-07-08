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
			//obtener id del cliente desde la sesión
			HttpSession sesion = request.getSession();
			int idCliente = (int) sesion.getAttribute("idCliente");
			
			//obtener cuentas del cliente
			NegocioCuenta negocio = new NegocioCuenta();
			List<Cuenta> cuentas = negocio.obtenerCuentasPorCliente(idCliente);

			request.setAttribute("cuentas", cuentas);

			request.getRequestDispatcher("/Formularios/ModoBanco/Prestamos/NuevoPrestamo.jsp").forward(request, response);
		}
		
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
