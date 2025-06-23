package controlador;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Cliente;
import negocio.NegocioCliente;

@WebServlet("/AgregarClienteServlet")
public class AgregarClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AgregarClienteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String usuario = request.getParameter("usuario");
	    String contrasena = request.getParameter("contrasena");
	    int dni = Integer.parseInt(request.getParameter("dni"));
	    String cuil = request.getParameter("cuil");
	    String nombre = request.getParameter("nombre");
	    String apellido = request.getParameter("apellido");
	    String sexo = request.getParameter("sexo");
	    String nacionalidad = request.getParameter("nacionalidad");
	    String fechaNacStr = request.getParameter("fechaNac");
	    String direccion = request.getParameter("direccion");
	    String localidad = request.getParameter("localidad");
	    String provincia = request.getParameter("provincia");
	    String correo = request.getParameter("correo");
	    String telefono = request.getParameter("telefono");

	    java.sql.Date fechaNac = java.sql.Date.valueOf(fechaNacStr);

	    Cliente cliente = new Cliente("001", dni, cuil, nombre, apellido, sexo,
	                                   nacionalidad, fechaNac, direccion, localidad,
	                                   provincia, correo, telefono, usuario, contrasena);

	    NegocioCliente servicio = new NegocioCliente();
	    boolean exito = servicio.alta(cliente);

	    if (exito) {
	        request.setAttribute("mensaje", "ok");
	    } else {
	        request.setAttribute("mensaje", "error");
	    }
	    request.getRequestDispatcher("/Formularios/ModoBanco/ABMLClientes/AgregarClientes.jsp").forward(request, response);
	}

}
