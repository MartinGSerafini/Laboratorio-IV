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
		
		RequestDispatcher rd = request.getRequestDispatcher("/VISTAS/AgregarSeguros.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String descripcion = "";
		int idTipo = 0;
		String idTipoStr;
		String costoContratacionStr;
		double costoContratacion = 0.0;
		double costoAsegurado = 0.0;
		String costoAseguradoStr;
		boolean todoValido = false;
		String btnAceptar1 = request.getParameter("btnAceptar1");
		
		//-------------Agregar Seguro----------------
	  
	  if(btnAceptar1 != null) {

		// Validación de descripción
		descripcion = request.getParameter("txtDescripcion");
		if (descripcion == null || descripcion.trim().isEmpty()) {
		    request.setAttribute("descrVacia", false);
		    todoValido = false;
		} else {
		    request.setAttribute("descrVacia", true);
		    todoValido = true;
		}

		// idTipo
		idTipoStr = request.getParameter("idTipo");
		if (idTipoStr != null && !idTipoStr.isEmpty()) {
		    try {
		        idTipo = Integer.parseInt(idTipoStr);
		        request.setAttribute("idTipoFalso", true);
		        todoValido = true;
		    } catch (NumberFormatException e) {
		        request.setAttribute("idTipoFalso", false);
		        todoValido = false;
		    }
		} else {
		    request.setAttribute("idTipoFalso", false);
		    todoValido = false;
		}

		// Costo de contratación
		costoContratacionStr = request.getParameter("txtCostoContratacion");
		if (costoContratacionStr != null && !costoContratacionStr.isEmpty()) {
		    try {
		        costoContratacion = Double.parseDouble(costoContratacionStr);
		        request.setAttribute("txtCostoContratacionFalso", true);
		        todoValido = true;
		    } catch (NumberFormatException e) {
		        request.setAttribute("txtCostoContratacionFalso", false);
		        todoValido = false;
		    }
		} else {
		    request.setAttribute("txtCostoContratacionFalso", false);
		    todoValido = false;
		}

		// Costo máximo
		costoAseguradoStr = request.getParameter("txtCostoMaximo");
		if (costoAseguradoStr != null && !costoAseguradoStr.isEmpty()) {
		    try {
		        costoAsegurado = Double.parseDouble(costoAseguradoStr);
		        request.setAttribute("txtCostoMaximoFalso", true);
		        todoValido = true;
		    } catch (NumberFormatException e) {
		        request.setAttribute("txtCostoMaximoFalso", false);
		        todoValido = false;
		    }
		} else {
		    request.setAttribute("txtCostoMaximoFalso", false);
		    todoValido = false;
		}
	  }
	  
	  if(todoValido) {

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
		        request.setAttribute("Agregado", true);
		    } else {
		        request.setAttribute("Agregado", false);
		    }

		    doGet(request, response);
	  }
	  else {
		  doGet(request, response);
		    return;

	  }
	}
}


