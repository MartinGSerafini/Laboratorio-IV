package controladorAdmin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import negocio.NegocioCliente;

@WebServlet("/EliminarClienteServlet")
public class EliminarClienteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idCliente = request.getParameter("idCliente");

        if (idCliente != null && !idCliente.trim().isEmpty()) {
            NegocioCliente negocio = new NegocioCliente();
            negocio.baja(idCliente);
        }
        response.sendRedirect("ListadoClientesServlet");
    }
}
