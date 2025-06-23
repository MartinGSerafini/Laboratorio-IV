package controlador;

import javax.servlet.*;
import javax.servlet.http.*;

import dao.AdministradorDao;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/loginAdmin")
public class LoginAdministradorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String usuario = request.getParameter("usuario");
        String contrasena = request.getParameter("contrasena");

        AdministradorDao dao = new AdministradorDao();
        boolean loginCorrecto = dao.validarLogin(usuario, contrasena);

        if (loginCorrecto) {
            HttpSession session = request.getSession();
            session.setAttribute("adminLogueado", usuario);
            response.sendRedirect("ModoBanco/Menu/MenuInicioBanco.jsp");
        } else {
            request.setAttribute("errorLogin", true);
            RequestDispatcher rd = request.getRequestDispatcher("Formularios/Login/IngresoAdministrador.jsp");
            rd.forward(request, response);
        }
    }
}