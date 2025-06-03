package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SeguroDao;
import dao.TiposeguroDao;
import entidades.TipoSeguro;

@WebServlet("/servletAgregarSeguros")
public class servletAgregarSeguros extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public servletAgregarSeguros() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//------------ID seguro---------------
		SeguroDao segu = new SeguroDao();
		int id = segu.obtenerUltimoID() + 1;
		request.setAttribute("IDsiguiente", id);
		
		//-------------Tipo Seguro-----------------
		TiposeguroDao tipoDao = new TiposeguroDao();
		 List<TipoSeguro> listaTipo = tipoDao.obtenerTodos();
		request.setAttribute("listaTipo", listaTipo);
				
		
		RequestDispatcher rd = request.getRequestDispatcher("/VISTAS/AgregarSeguros.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
