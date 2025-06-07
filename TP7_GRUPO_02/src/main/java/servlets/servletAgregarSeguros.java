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
import negocio.NegocioSeguro;
import negocio.NegocioTipoSeguro;

@WebServlet("/servletAgregarSeguros")
public class servletAgregarSeguros extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public servletAgregarSeguros() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//------------ID seguro---------------
		NegocioSeguro segu = new NegocioSeguro();
		int id = segu.obtenerUltimoID() + 1;
		request.setAttribute("IDsiguiente", id);
		
		//-------------Tipo Seguro-----------------
		NegocioTipoSeguro tipoNegocio = new NegocioTipoSeguro();
		 List<TipoSeguro> listaTipo = tipoNegocio.obtenerTodos();
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
		String btnAceptar1 = request.getParameter("btnAceptar1");
		
		//-------------Agregar Seguro----------------
	  
	  if(btnAceptar1 != null) {
		  
		  descripcion = request.getParameter("txtDescripcion");
		  idTipoStr = request.getParameter("idTipo");
		  costoContratacionStr = request.getParameter("txtCostoContratacion");
		  costoAseguradoStr = request.getParameter("txtCostoMaximo");
		  
		  NegocioSeguro ns = new NegocioSeguro();
		  
		  /// Validaciones en NegocioSeguro
		  boolean valido = ns.validarSeguro(descripcion, idTipoStr, costoContratacionStr, costoAseguradoStr);
		  
		  if(valido) {
			  idTipo = Integer.parseInt(idTipoStr);
			  costoContratacion = Double.parseDouble(costoAseguradoStr);
			  costoAsegurado = Double.parseDouble(costoAseguradoStr);
			  
			  /// Intentar agregar el seguro
			  boolean agregado = ns.agregarSeguro(descripcion, idTipo, costoContratacion, costoAsegurado);
			  
			  request.setAttribute("Agregado", agregado);
			  
		  } else {		///configuracion de los atributos para mostrar en el jsp
				// Validación de descripción
				if (descripcion == null || descripcion.trim().isEmpty()) { request.setAttribute("descrVacia", false); } 
				else { request.setAttribute("descrVacia", true); }
				
				// idTipo
				if (idTipoStr != null && !idTipoStr.isEmpty()) {
				    try { request.setAttribute("idTipoFalso", true); } 
				    catch (NumberFormatException e) { request.setAttribute("idTipoFalso", false); }
				} else { request.setAttribute("idTipoFalso", false); }
				
				// Costo de contratación
				if (costoContratacionStr != null && !costoContratacionStr.isEmpty()) {
				    try { request.setAttribute("txtCostoContratacionFalso", true); } 
				    catch (NumberFormatException e) { request.setAttribute("txtCostoContratacionFalso", false); }
				} else { request.setAttribute("txtCostoContratacionFalso", false); }
		
				// Costo máximo
				if (costoAseguradoStr != null && !costoAseguradoStr.isEmpty()) {
				    try { request.setAttribute("txtCostoMaximoFalso", true); } 
				    catch (NumberFormatException e) { request.setAttribute("txtCostoMaximoFalso", false); }
				} else { request.setAttribute("txtCostoMaximoFalso", false); }
				
			    request.setAttribute("Agregado",  false);
		  	}
	  	}
	  	doGet(request, response); 
	}
}


