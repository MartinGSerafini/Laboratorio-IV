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
import entidades.Seguros;
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
		
		//-------------Agregar Seguro----------------
		/*// obtener datos del jsp
		String descripcion = request.getParameter("txtDescripcion"); 
		int idTipo = Integer.parseInt(request.getParameter("idTipo"));
		double costoContratacion = Double.parseDouble(request.getParameter("txtCostoContratacion")); 
		double costoAsegurado = Double.parseDouble(request.getParameter("txtCostoMaximo"));

        // asignar los valores en un obj seguros
        Seguros s = new Seguros();
        s.setDescripcion(descripcion);
        s.setCostoContratacion(costoContratacion);
        s.setCostoAsegurado(costoAsegurado);

        TipoSeguro tipo = new TipoSeguro();
        tipo.setIdTipo(idTipo);
        s.setTipoSeguro(tipo);

        SeguroDao dao = new SeguroDao();
        int filas = dao.agregarSeguro(s);

        if (filas > 0) {
            response.getWriter().println("Seguro agregado con éxto");
        } else {
            response.getWriter().println("Error al agregar seguro");
        }*/
		
		RequestDispatcher rd = request.getRequestDispatcher("/VISTAS/AgregarSeguros.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//-------------Agregar Seguro----------------
		// Recopilar datos del formulario
		String descripcion = request.getParameter("txtDescripcion");
		        
		// Validar y parsear idTipo para evitar NumberFormatException
		int idTipo = 0;
		String idTipoStr = request.getParameter("idTipo");
		if (idTipoStr != null && !idTipoStr.isEmpty()) {
		    	  
			try { idTipo = Integer.parseInt(idTipoStr); } 
		    catch (NumberFormatException e) { //maneja el error si idTipo es invalido
		        doGet(request, response);
		        return;
		    }
		} 
		else { // Maneja el error si idTipo es null
		    doGet(request, response);
		    return;
		}

		// Validar y parsear costos para evitar NumberFormatException
		double costoContratacion = 0.0;
		String costoContratacionStr = request.getParameter("txtCostoContratacion"); 
		if (costoContratacionStr != null && !costoContratacionStr.isEmpty()) {
			try { costoContratacion = Double.parseDouble(costoContratacionStr); } 
			catch (NumberFormatException e) {
				doGet(request, response);
				return;
		    }
		} 
		else {
			doGet(request, response);
		    return;
		}

		double costoAsegurado = 0.0;
		String costoAseguradoStr = request.getParameter("txtCostoMaximo");
		if (costoAseguradoStr != null && !costoAseguradoStr.isEmpty()) {
			try { costoAsegurado = Double.parseDouble(costoAseguradoStr); } 
			catch (NumberFormatException e) {
				doGet(request, response);
				return;
		    }
		} 
		else {
			doGet(request, response);
		    return;
		}

		// Asignar los valores a un objeto Seguros
		Seguros s = new Seguros();
		s.setDescripcion(descripcion);
		s.setCostoContratacion(costoContratacion);
		s.setCostoAsegurado(costoAsegurado);

		TipoSeguro tipo = new TipoSeguro();
		tipo.setIdTipo(idTipo);
		s.setTipoSeguro(tipo);

		SeguroDao dao = new SeguroDao();
		int filas = dao.agregarSeguro(s);

		        if (filas > 0) {
		            response.getWriter().println("Seguro agregado con éxto");
		        } else {
		            response.getWriter().println("Error al agregar seguro");
		        }
		   
		doGet(request, response); 
	}
}


