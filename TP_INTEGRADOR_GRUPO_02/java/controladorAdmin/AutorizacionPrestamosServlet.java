package controladorAdmin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.EstadoPrestamo;
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
		if(request.getParameter("autorizar") != null) {
			negocioPrestamo.modificarEstadoPrestamo("1", request.getParameter("idPrestamo"));
		}
		
		if(request.getParameter("rechazar") != null) {
			negocioPrestamo.modificarEstadoPrestamo("2", request.getParameter("idPrestamo"));
		}
		
		ArrayList<Prestamo> listado = negocioPrestamo.listarPrestamos();
		request.setAttribute("ListaPrestamos", listado);
		
		RequestDispatcher rd = request.getRequestDispatcher("/Formularios/ModoBanco/Prestamos/AutorizacionPrestamos.jsp");
		rd.forward(request, response); 
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		if (request.getParameter("filtro") != null && !request.getParameter("filtro").equals("0")) {
		    int filtroSeleccionado = Integer.parseInt(request.getParameter("filtro"));
		    switch(filtroSeleccionado) {
		    case 1:
                ArrayList<EstadoPrestamo> estados = negocioPrestamo.obtenerEstados();
                request.setAttribute("ListaEstados", estados);
                break;
		    case 2:
		    	break;
		    }
		}
		if(request.getParameter("btnBuscar") != null) {
			if (request.getParameter("estadoSeleccionado") != null && !request.getParameter("estadoSeleccionado").equals("0")) {
			    ArrayList<Prestamo> listado = negocioPrestamo.filtrarPorEstado(request.getParameter("estadoSeleccionado"));
			    request.setAttribute("ListaPrestamos", listado);
			}
			ArrayList<EstadoPrestamo> estados = negocioPrestamo.obtenerEstados();
		    request.setAttribute("ListaEstados", estados);
		}
		RequestDispatcher rd = request.getRequestDispatcher("/Formularios/ModoBanco/Prestamos/AutorizacionPrestamos.jsp");
		rd.forward(request, response); 
	}
	
}
