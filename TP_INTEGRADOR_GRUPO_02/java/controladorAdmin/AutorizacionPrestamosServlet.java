package controladorAdmin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Prestamo;
import negocio.NegocioPrestamo;

/**
 * Servlet implementation class AutorizacionPrestamosServlet
 */
@WebServlet("/AutorizacionPrestamosServlet")
public class AutorizacionPrestamosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	NegocioPrestamo negocioPrestamo = new NegocioPrestamo();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AutorizacionPrestamosServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Prestamo> listado = negocioPrestamo.listarPrestamos();
		request.setAttribute("ListaPrestamos", listado);
		
		RequestDispatcher rd = request.getRequestDispatcher("/Formularios/ModoBanco/Prestamos/AutorizacionPrestamos.jsp");
		rd.forward(request, response); 
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
