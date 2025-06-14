package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.NegocioSeguro;
import negocio.NegocioTipoSeguro;
import entidades.Seguros;
import entidades.TipoSeguro;

@WebServlet("/ListarSegurosServlet")
public class servletListarSeguros extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public servletListarSeguros() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    NegocioTipoSeguro tipoNegocio = new NegocioTipoSeguro();
	    List<TipoSeguro> listaTipo = tipoNegocio.obtenerTodos();
	    request.setAttribute("listaTipo", listaTipo);

	    String idTipoStr = request.getParameter("idTipo");
	    NegocioSeguro seguroNegocio = new NegocioSeguro();
	    List<Seguros> listaSeguros;

	    if (idTipoStr != null && !idTipoStr.isEmpty()) {
	        try {
	            int idTipo = Integer.parseInt(idTipoStr);
	            listaSeguros = seguroNegocio.obtenerPorTipo(idTipo);
	        } catch (NumberFormatException e) {
	            listaSeguros = seguroNegocio.obtenerTodos();
	        }
	    } else {
	        listaSeguros = seguroNegocio.obtenerTodos(); 
	    }

	    request.setAttribute("listaSeguros", listaSeguros);

	    RequestDispatcher dispatcher = request.getRequestDispatcher("/VISTAS/ListarSeguros.jsp");
	    dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
}