package controladorCliente;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import negocio.NegocioCliente;

@WebServlet("/IngresoClienteServlet")
public class IngresoClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public IngresoClienteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String usuario = request.getParameter("usuario");
	    String contraseña = request.getParameter("contraseña");

	    if (usuario != null && contraseña != null) {
	        NegocioCliente negocio = new NegocioCliente();
	        boolean valido = negocio.verificarCliente(usuario, contraseña);

	        if (valido) {
	            RequestDispatcher rd = request.getRequestDispatcher("/MenuInicioCliente.jsp");
	            rd.forward(request, response);
	        } else {
	            request.setAttribute("LoginError", true);
	            RequestDispatcher rd = request.getRequestDispatcher("/IngresoCliente.jsp");
	            rd.forward(request, response);
	        }
	    } else {
	        response.sendRedirect("IngresoCliente.jsp");
	    }
	}


}
