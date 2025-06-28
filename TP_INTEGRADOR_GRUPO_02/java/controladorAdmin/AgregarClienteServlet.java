package controladorAdmin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LocalidadDao;
import dao.NacionalidadDao;
import dao.ProvinciaDao;
import dao.daoCliente;
import entidades.Cliente;
import entidades.Localidad;
import entidades.Nacionalidad;
import entidades.Provincia;
import negocio.NegocioCliente;; 


@WebServlet("/AgregarClienteServlet")
public class AgregarClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AgregarClienteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//-------Usuario------------
		daoCliente daoCliente = new daoCliente();
		int idCuenta = daoCliente.obtenerUltimoId() + 1; 
		request.setAttribute("idUsuario", idCuenta);
		
		//-------Nacionalidad------------
		NacionalidadDao nacionalidadDao = new NacionalidadDao();
	    ArrayList<Nacionalidad> listaNacionalidades = nacionalidadDao.obtenerNacionalidades();
	    request.setAttribute("nacionalidades", listaNacionalidades);

	   
	   //-------Provincia------------
	    ProvinciaDao provinciaDao = new ProvinciaDao();
	    List<Provincia> listaProvincias = provinciaDao.obtenerProvincias();
	    request.setAttribute("provincias", listaProvincias);
	
	    //-------Localidad------------
		RequestDispatcher rd = request.getRequestDispatcher("/Formularios/ModoBanco/ABMLClientes/AgregarClientes.jsp");
		rd.forward(request, response); 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		
		String accion = request.getParameter("accion");

	    // Cargar nacionalidades y provincias siempre que vuelvas al JSP
	    NacionalidadDao nacionalidadDao = new NacionalidadDao();
	    ProvinciaDao provinciaDao = new ProvinciaDao();

	    List<Nacionalidad> nacionalidades = nacionalidadDao.obtenerNacionalidades();
	    List<Provincia> provincias = provinciaDao.obtenerProvincias();

	    request.setAttribute("nacionalidades", nacionalidades);
	    request.setAttribute("provincias", provincias);

	    // Acci�n: cargar localidades
	    if ("cargarLocalidades".equals(accion)) {
	        String idProvStr = request.getParameter("provincia");

	        if (idProvStr != null && !idProvStr.isEmpty()) {
	            try {
	                int idProv = Integer.parseInt(idProvStr);
	                LocalidadDao localidadDao = new LocalidadDao();
	                List<Localidad> localidades = localidadDao.obtenerLocalidadesPorProvincia(idProv);
	                request.setAttribute("localidades", localidades);
	            } catch (NumberFormatException e) {
	                e.printStackTrace(); // pod�s mejorar con logs o un mensaje de error al usuario
	            }
	        }

	        // Volver al formulario con provincias, nacionalidades y localidades cargadas
	        request.getRequestDispatcher("/Formularios/ModoBanco/ABMLClientes/AgregarClientes.jsp").forward(request, response);
	        return;
	    }

	    // Acci�n: agregar cliente
	    if ("agregarCliente".equals(accion)) {
	        try {
	            String usuario = request.getParameter("usuario");
	            String contrasena = request.getParameter("contrasena");
	            int dni = Integer.parseInt(request.getParameter("dni"));
	            String cuil = request.getParameter("cuil");
	            String nombre = request.getParameter("nombre");
	            String apellido = request.getParameter("apellido");
	            String sexo = request.getParameter("sexo");
	            int nacionalidad = Integer.parseInt(request.getParameter("nacionalidad"));
	            String fechaNacStr = request.getParameter("fechaNac");
	            String direccion = request.getParameter("direccion");
	            int localidad = Integer.parseInt(request.getParameter("localidad"));
	            int provincia = Integer.parseInt(request.getParameter("provincia"));
	            String correo = request.getParameter("correo");
	            String telefono = request.getParameter("telefono");

	            java.sql.Date fechaNac = java.sql.Date.valueOf(fechaNacStr);

	            //instanciar NegocioCliente
	            NegocioCliente servicio = new NegocioCliente();
	           
	            //validar cliente y obtener errores
	            String ErroresValidacion = servicio.validarCliente(usuario, contrasena, dni, cuil,
	                    nombre, apellido, sexo, nacionalidad, fechaNacStr, direccion,
	                    localidad, provincia, correo, telefono);
	            
	            if(ErroresValidacion != null && !ErroresValidacion.isEmpty()) {
	            	ArrayList<String> errores = new ArrayList<>();
	            	errores.add(ErroresValidacion);
	            	request.setAttribute("errores", errores);
	            	
	            	// Mantener los valores ingresados en el formulario después del error
	                request.setAttribute("usuario", usuario);
	                request.setAttribute("contrasena", contrasena);
	                request.setAttribute("dni", dni); 
	                request.setAttribute("cuil", cuil);
	                request.setAttribute("nombre", nombre);
	                request.setAttribute("apellido", apellido);
	                request.setAttribute("sexo", sexo);
	                request.setAttribute("nacionalidad", String.valueOf(nacionalidad)); 
	                request.setAttribute("fechaNac", fechaNacStr);
	                request.setAttribute("direccion", direccion);
	                request.setAttribute("provincia", String.valueOf(provincia)); 
	                request.setAttribute("localidad", String.valueOf(localidad)); 
	                request.setAttribute("correo", correo);
	                request.setAttribute("telefono", telefono);

	                // Si hay una provincia seleccionada, recargar las localidades
	                if (provincia > 0) {
	                    LocalidadDao localidadDao = new LocalidadDao();
	                    List<Localidad> localidadesCargadas = localidadDao.obtenerLocalidadesPorProvincia(provincia);
	                    request.setAttribute("localidades", localidadesCargadas);
	                }

	                request.getRequestDispatcher("/Formularios/ModoBanco/ABMLClientes/AgregarClientes.jsp").forward(request, response);
	                return; 
	            }
	            
	          //si no hay errores, procede con el alta
	            Cliente cliente = new Cliente(dni, cuil, nombre, apellido, sexo,
                        nacionalidad, fechaNac, direccion, localidad,
                        provincia, correo, telefono, usuario, contrasena);
	            
	            boolean exito = servicio.alta(cliente);

	            if (exito) {
	                request.setAttribute("mensaje", "ok");
	            } else {
	                request.setAttribute("mensaje", "error");
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	            request.setAttribute("mensaje", "error");
	        }

	        request.getRequestDispatcher("/Formularios/ModoBanco/ABMLClientes/AgregarClientes.jsp").forward(request, response);
	    }
	}
}
