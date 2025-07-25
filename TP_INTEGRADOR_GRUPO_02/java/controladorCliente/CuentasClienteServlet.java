package controladorCliente;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Cliente;
import entidades.Cuenta;
import entidades.Movimiento;
import negocio.NegocioCuenta;
import negocio.NegocioMovimiento;
import negocio.NegocioCliente;


@WebServlet("/CuentasClienteServlet")
public class CuentasClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NegocioCuenta negocioCuenta = new NegocioCuenta();
       
    public CuentasClienteServlet() {
        super();
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		Cliente cliente = new Cliente();
		if (session != null) {
		    String usuarioCliente = (String) session.getAttribute("ClienteLogueado");
		    NegocioCliente negocioCliente = new NegocioCliente();
		    cliente = negocioCliente.obtenerCliente(usuarioCliente);
		}
		ArrayList<Cuenta> lista = negocioCuenta.cuentasXCliente(cliente.getIdCliente());
		request.setAttribute("ListaCuentas", lista);
		
		ArrayList<Movimiento> listaMovimientos = new ArrayList<Movimiento>();
		if(request.getParameter("btnVerHistorial") != null) {
			String id = request.getParameter("idCuenta");
			String cbu = request.getParameter("cbuCuenta");
			request.setAttribute("CbuCuenta", cbu);
			NegocioMovimiento negocioMovimiento = new NegocioMovimiento();
			listaMovimientos = negocioMovimiento.obtenerMovimientosPorCuenta(id);
			request.setAttribute("ListaMovimientos", listaMovimientos);
			request.setAttribute("AbrirModal", true);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/Formularios/ModoCliente/Cuentas/Cuentas.jsp");
		rd.forward(request, response); 
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
