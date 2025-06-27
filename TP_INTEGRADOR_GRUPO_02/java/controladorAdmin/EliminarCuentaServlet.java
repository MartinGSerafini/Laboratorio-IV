package controladorAdmin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.NegocioCliente;

@WebServlet("/EliminarCuentaServlet")
public class EliminarCuentaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public EliminarCuentaServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idCuenta = request.getParameter("idCuenta");

        if (idCuenta != null && !idCuenta.trim().isEmpty()) {
            NegocioCliente negocio = new NegocioCliente();
            negocio.baja(idCuenta);
        }
        response.sendRedirect("ListadoClientesServlet");
		
	}

}
