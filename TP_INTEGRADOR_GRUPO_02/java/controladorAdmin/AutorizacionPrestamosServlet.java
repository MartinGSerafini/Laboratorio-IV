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
		ArrayList<Prestamo> listado = new ArrayList<Prestamo>();
		if(request.getParameter("autorizar") != null) {
			negocioPrestamo.modificarEstadoPrestamo("1", request.getParameter("idPrestamo"));
		}
		
		if(request.getParameter("rechazar") != null) {
			negocioPrestamo.modificarEstadoPrestamo("2", request.getParameter("idPrestamo"));
		}
		
		ArrayList<String> filtros = negocioPrestamo.obtenerColumnas();
		request.setAttribute("ListaFiltros", filtros);
		
		ArrayList<EstadoPrestamo> estados = negocioPrestamo.obtenerEstados();
		request.setAttribute("ListaEstados", estados);
		
		listado = negocioPrestamo.listarPrestamos();
		request.setAttribute("ListaPrestamos", listado);
		
		
		
		if(request.getParameter("btnBuscar") != null) {
			if (request.getParameter("estadoSeleccionado") != null && !request.getParameter("estadoSeleccionado").equals("0")) {
				listado = negocioPrestamo.filtrarPorEstado(request.getParameter("estadoSeleccionado"));
				request.setAttribute("ListaPrestamos", listado);
			}
			request.setAttribute("ListaEstados", estados);
			
			if(request.getParameter("filtro")!= null) {
				if(request.getParameter("busqueda") != null && !request.getParameter("busqueda").trim().isEmpty()) {
						listado = negocioPrestamo.filtrarBusqueda(request.getParameter("filtro"),request.getParameter("busqueda").trim());
						if(listado != null) {
							request.setAttribute("ListaPrestamos", listado);
						}
						else {
							request.setAttribute("errores", "INGRESE DATO VALIDO");
						}
				}
			}
		}
		
		if(request.getParameter("btnquitarFiltro")!= null) {
			request.setAttribute("filtroSeleccionado", "0");
			request.setAttribute("busqueda", "");
			listado = negocioPrestamo.listarPrestamos();
			request.setAttribute("ListaPrestamos", listado);
		}
		
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/Formularios/ModoBanco/Prestamos/AutorizacionPrestamos.jsp");
		rd.forward(request, response); 
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
}
