package controladorAdmin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dao.LocalidadDao;
import dao.NacionalidadDao;
import dao.ProvinciaDao;
import dao.daoCliente;
import entidades.Cliente;
import entidades.Localidad;
import entidades.Nacionalidad;
import entidades.Provincia;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import negocio.NegocioCliente;; 


@WebServlet("/AgregarClienteServlet")
public class AgregarClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AgregarClienteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		NacionalidadDao nacionalidadDao = new NacionalidadDao();
	    ArrayList<Nacionalidad> listaNacionalidades = nacionalidadDao.obtenerNacionalidades();
	    request.setAttribute("nacionalidades", listaNacionalidades);

	    ProvinciaDao provinciaDao = new ProvinciaDao();
	    List<Provincia> listaProvincias = provinciaDao.obtenerProvincias();
	    request.setAttribute("provincias", listaProvincias);
	
		RequestDispatcher rd = request.getRequestDispatcher("/Formularios/ModoBanco/ABMLClientes/AgregarClientes.jsp");
		rd.forward(request, response); 
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		
		String accion = request.getParameter("accion");
		
		daoCliente daoCliente = new daoCliente();
		int idCliente = daoCliente.obtenerUltimoId() + 1;
		String idClienteStr = String.valueOf(idCliente);
			
	    NacionalidadDao nacionalidadDao = new NacionalidadDao();
	    ProvinciaDao provinciaDao = new ProvinciaDao();

	    List<Nacionalidad> nacionalidades = nacionalidadDao.obtenerNacionalidades();
	    List<Provincia> provincias = provinciaDao.obtenerProvincias();

	    request.setAttribute("nacionalidades", nacionalidades);
	    request.setAttribute("provincias", provincias);

	    // Acción: cargar localidades
	    if ("cargarLocalidades".equals(accion)) {
	        String idProvStr = request.getParameter("provincia");

	        if (idProvStr != null && !idProvStr.isEmpty()) {
	            try {
	                int idProv = Integer.parseInt(idProvStr);
	                LocalidadDao localidadDao = new LocalidadDao();
	                List<Localidad> localidades = localidadDao.obtenerLocalidadesPorProvincia(idProv);
	                request.setAttribute("localidades", localidades);
	            } catch (NumberFormatException e) {
	                e.printStackTrace(); 
	            }
	        }

	        request.getRequestDispatcher("/Formularios/ModoBanco/ABMLClientes/AgregarClientes.jsp").forward(request, response);
	        return;
	    }

	    // Acción: agregar cliente
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

	            NegocioCliente servicio = new NegocioCliente();
	            String ErroresValidacion = servicio.validarYVerificarCliente(idClienteStr, usuario, contrasena, dni, cuil,
	                    nombre, apellido, sexo, nacionalidad, fechaNacStr, direccion,
	                    localidad, provincia, correo, telefono);
	            
	            ArrayList<String> errores = new ArrayList<>();
	            errores.add(ErroresValidacion);
	            
	            if(ErroresValidacion != null && !ErroresValidacion.isEmpty()) {
	            	
	            	request.setAttribute("errores", errores);
	            	
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

	                if (provincia > 0) {
	                    LocalidadDao localidadDao = new LocalidadDao();
	                    List<Localidad> localidadesCargadas = localidadDao.obtenerLocalidadesPorProvincia(provincia);
	                    request.setAttribute("localidades", localidadesCargadas);
	                }

	                request.getRequestDispatcher("/Formularios/ModoBanco/ABMLClientes/AgregarClientes.jsp").forward(request, response);
	                return; 
	            }
	            
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
	    
	    // Acción: Limpiar form
	    if ("resetFormulario".equals(accion)) {
	        NacionalidadDao nacionalidadDao2 = new NacionalidadDao();
	        ProvinciaDao provinciaDao2 = new ProvinciaDao();

	        List<Nacionalidad> nacionalidades2 = nacionalidadDao.obtenerNacionalidades();
	        List<Provincia> provincias2 = provinciaDao.obtenerProvincias();

	        request.setAttribute("nacionalidades", nacionalidades);
	        request.setAttribute("provincias", provincias);

	        request.getRequestDispatcher("/Formularios/ModoBanco/ABMLClientes/AgregarClientes.jsp").forward(request, response);
	        return;
	    }
	    
	    
	}
}
