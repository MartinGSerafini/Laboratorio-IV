package controladorAdmin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.NegocioCuenta;

@WebServlet("/EliminarCuentaServlet")
public class EliminarCuentaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idCuenta = request.getParameter("idCuenta");

        if (idCuenta != null && !idCuenta.trim().isEmpty()) {
            NegocioCuenta negocio = new NegocioCuenta();
            negocio.bajaCuenta(idCuenta);
        }
        response.sendRedirect("ListadoCuentasServlet");
		
	}

}
