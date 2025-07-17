package controladorCliente;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import negocio.NegocioCliente;

@WebServlet("/IngresoClienteServlet")
public class IngresoClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public IngresoClienteServlet() {
        super();
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String usuario = request.getParameter("usuario");
	    String contrasena = request.getParameter("contrasena");

	    if (usuario != null && contrasena != null) {
	        NegocioCliente negocio = new NegocioCliente();
	        boolean valido = negocio.verificarCliente(usuario, contrasena);

	        if (valido) {
	        	HttpSession session = request.getSession();
	            session.setAttribute("ClienteLogueado", usuario);
	            response.sendRedirect("Formularios/ModoCliente/Menu/MenuInicioCliente.jsp");
	        } else {
	            request.setAttribute("LoginError", true);
	            RequestDispatcher rd = request.getRequestDispatcher("Formularios/Login/IngresoCliente.jsp");
	            rd.forward(request, response);
	        }
	    } else {
	        response.sendRedirect("Formularios/Login/IngresoCliente.jsp");
	    }
	}


}
