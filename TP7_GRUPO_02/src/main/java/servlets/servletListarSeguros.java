package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TiposeguroDao;
import entidades.TipoSeguro;

@WebServlet("/ListarSegurosServlet")
public class servletListarSeguros extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public servletListarSeguros() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		TiposeguroDao tipoDao = new TiposeguroDao();
	    List<TipoSeguro> listaTipo = tipoDao.obtenerTodos();
	    request.setAttribute("listaTipo", listaTipo);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/VISTAS/ListarSeguros.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
}