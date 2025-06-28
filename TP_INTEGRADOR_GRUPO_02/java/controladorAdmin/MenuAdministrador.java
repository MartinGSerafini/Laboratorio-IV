package controladorAdmin;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/MenuAdministrador")
public class MenuAdministrador extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String contexto = request.getContextPath();
        String accion = request.getParameter("accion");
        String destino = request.getParameter("destino");

        if ("cerrar".equals(accion)) {
            request.getSession().invalidate();
            response.sendRedirect(contexto + "/Formularios/Login/InicioUsuario.jsp");
            return;
        }

        switch (destino) {
            case "clientes":
                response.sendRedirect(contexto + "/Formularios/ModoBanco/ABMLClientes/AgregarClientes.jsp");
                break;
            case "cuentas":
                response.sendRedirect(contexto + "/Formularios/ModoBanco/ABMLCuentas/AgregarCuentas.jsp");
                break;
            case "prestamos":
                response.sendRedirect(contexto + "/Formularios/ModoBanco/Prestamos/AutorizacionPrestamos.jsp");
                break;
            case "informes":
                response.sendRedirect(contexto + "/Formularios/ModoBanco/Informes/Informes.jsp");
                break;
            default:
                response.sendRedirect(contexto + "/Formularios/ModoBanco/Menu/MenuInicioBanco.jsp");
        }
    }
}
