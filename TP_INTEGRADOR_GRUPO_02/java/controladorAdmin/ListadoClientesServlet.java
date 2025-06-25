package controladorAdmin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Cliente;
import negocio.NegocioCliente;


@WebServlet("/ListadoClientesServlet")
public class ListadoClientesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public ListadoClientesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("btnbuscar") != null) {
			NegocioCliente negocio = new NegocioCliente();
			ArrayList<Cliente> lista = negocio.ObtenerListadoClientes();
			
			request.setAttribute("ListaCli", lista);
			
			RequestDispatcher rd = request.getRequestDispatcher("Formularios/ModoBanco/ABMLClientes/ListadoClientes.jsp");
            rd.forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
