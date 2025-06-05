package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SeguroDao;

@WebServlet("/servletAgregarSeguros")
public class servletAgregarSeguros extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public servletAgregarSeguros() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		SeguroDao segu = new SeguroDao();
		int id = segu.obtenerUltimoID();
		
		request.setAttribute("IDsiguiente", id);
		RequestDispatcher rd = request.getRequestDispatcher("AgregarSeguros.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
