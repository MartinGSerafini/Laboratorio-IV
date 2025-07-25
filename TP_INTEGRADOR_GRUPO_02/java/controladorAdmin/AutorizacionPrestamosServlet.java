package controladorAdmin;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.EstadoPrestamo;
import entidades.Prestamo;
import negocio.NegocioMovimientos;
import negocio.NegocioPrestamo;


@WebServlet("/AutorizacionPrestamosServlet")
public class AutorizacionPrestamosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	NegocioPrestamo negocioPrestamo = new NegocioPrestamo();
	NegocioMovimientos negocioMovimientos = new NegocioMovimientos();
    
    public AutorizacionPrestamosServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Prestamo> listado = new ArrayList<Prestamo>();
		
		String accion = request.getParameter("accion");
		
		if("autorizar".equals(accion)) {
			negocioPrestamo.modificarEstadoPrestamo("1", request.getParameter("idPrestamo"));
			String importe = request.getParameter("idImporteSolicitado");
			BigDecimal importeSolicitado = new BigDecimal(importe);
			int idCuenta = Integer.parseInt(request.getParameter("idCuentaDeposito"));
			negocioMovimientos.registrarMovimiento(idCuenta, importeSolicitado, 2, "Préstamo aprobado");
		}
		
		if("rechazar".equals(accion)) {
			negocioPrestamo.modificarEstadoPrestamo("2", request.getParameter("idPrestamo"));
		}
		
		ArrayList<String> filtros = negocioPrestamo.obtenerColumnas();
		request.setAttribute("ListaFiltros", filtros);
		
		ArrayList<EstadoPrestamo> estados = negocioPrestamo.obtenerEstados();
		request.setAttribute("ListaEstados", estados);
		
		listado = negocioPrestamo.listarPrestamos();
		request.setAttribute("ListaPrestamos", listado);
		
		//PAGINACION
		 int registrosPorPagina = 10;
	        int paginaActual = 1;
	        if (request.getParameter("pagina") != null) {
	            try {
	                paginaActual = Integer.parseInt(request.getParameter("pagina"));
	            } catch (NumberFormatException e) {
	                paginaActual = 1;
	            }
	        }
	        int desde = (paginaActual - 1) * registrosPorPagina;
	        int hasta = Math.min(desde + registrosPorPagina, listado.size());
	        ArrayList<Prestamo> pagina = new ArrayList<>(listado.subList(desde, hasta));

	        int totalPaginas = (int) Math.ceil((double) listado.size() / registrosPorPagina);
		
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
							request.setAttribute("errores", "Ingrese un dato numerico.");
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
