package controladorCliente;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Cliente;
import negocio.NegocioCliente;

@WebServlet("/InfoPersonalClienteServlet")
public class InfoPersonalClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public InfoPersonalClienteServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String usuario = (String) session.getAttribute("ClienteLogueado");
		
		Cliente cliente = new Cliente();
		NegocioCliente negocio = new NegocioCliente();
		
		cliente = negocio.obtenerCliente(usuario);
		if (cliente == null) {
		    System.out.println("No se encontró cliente para usuario: " + usuario);
		} else {
		    System.out.println("Cliente encontrado: " + cliente.getNombreCliente());
		}
		request.setAttribute("cliente", cliente);
		RequestDispatcher rd = request.getRequestDispatcher("Formularios/ModoCliente/InformacionPersonal/InformacionPersonal.jsp");
        rd.forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
