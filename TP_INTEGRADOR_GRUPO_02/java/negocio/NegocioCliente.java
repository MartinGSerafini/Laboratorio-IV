package negocio;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dao.daoCliente;
import entidades.Cliente;
import excepciones.ClienteNoEncontradoExc;
import excepciones.OperacionNoEfectuadaExc;
import interfaces.servicioABML;

public class NegocioCliente implements servicioABML<Cliente>{

	daoCliente daoCliente = new daoCliente();
 
	public boolean alta(Cliente cliente){
		try {
			boolean filas= daoCliente.altaCliente(cliente);
			
			if(filas==false) {
				throw new OperacionNoEfectuadaExc("No se insertó ningún cliente.");
			}
			
			return true;
		}
		catch(OperacionNoEfectuadaExc e) {
			System.out.println("Error: "+e.getMessage());
			return false;
		}
	}
	
	public boolean baja(String id) {
	    return daoCliente.bajaLogicaCliente(id);
	}

	public boolean modificacion(String id, Cliente cliente) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean lectura(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Cliente> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean verificarCliente(String usuario, String contrasena) {
		
		if (usuario == null || usuario.trim().isEmpty()) return false;
		if (contrasena == null || contrasena.trim().isEmpty()) return false; 
		
		boolean bool = daoCliente.verificarCliente(usuario, contrasena);
		return bool;
	}

    public String validarCliente(String usuario, String contrasena, int dni, String cuil,
            String nombre, String apellido, String sexo, int nacionalidad,
            String fechaNacStr, String direccion, int localidad, int provincia,
            String correo, String telefono) {

    	StringBuilder errores = new StringBuilder(); //Para concatenar errores

		String dniStr = String.valueOf(dni);
		String nacionalidadStr = String.valueOf(nacionalidad);
		String localidadStr = String.valueOf(localidad);
		String provinciaStr = String.valueOf(provincia);
		
		/// --- Validacion de campos vacíos ---
		//Si hay algun campo vacio, mostramos el mensaje y salimos del método
		if (usuario == null || usuario.trim().isEmpty() ||
			contrasena == null || contrasena.trim().isEmpty() ||
			dniStr.trim().isEmpty() || 
			cuil == null || cuil.trim().isEmpty() ||
			nombre == null || nombre.trim().isEmpty() ||
			apellido == null || apellido.trim().isEmpty() ||
			sexo == null || sexo.trim().isEmpty() ||
			nacionalidadStr.trim().isEmpty() || 
			fechaNacStr == null || fechaNacStr.trim().isEmpty() ||
			direccion == null || direccion.trim().isEmpty() ||
			localidadStr.trim().isEmpty() || 
			provinciaStr.trim().isEmpty() || 
			correo == null || correo.trim().isEmpty() ||
			telefono == null || telefono.trim().isEmpty()) {
			
				//valida que las listas desplegables no estén en 0
				if (nacionalidad <= 0 || localidad <= 0 || provincia <= 0) {
					errores.append("Debe seleccionar una opción válida para Nacionalidad, Provincia y Localidad. ");
				}
				if (errores.length() == 0) { // Si no hay error de IDs, ponemos el genérico de vacío
					errores.append("Todos los campos son obligatorios. ");
				}
				return errores.toString(); 
		}
		
		/// --- Validaciones de formato ---
		// si los campos no están vacíos
		
		try { java.sql.Date.valueOf(fechaNacStr); } 
		catch (IllegalArgumentException e) { errores.append("Formato de fecha de nacimiento inválido. "); }
		
		// Validación de longitud de DNI (8 dígitos)
		if (dniStr.length() != 8) { errores.append("El DNI debe tener 8 dígitos. "); }
		
		// Validación de formato de CUIL (XX-XXXXXXXX-X)
		Pattern cuilPattern = Pattern.compile("^\\d{2}-\\d{8}-\\d$");	//uso una expresión regular
		Matcher cuilMatcher = cuilPattern.matcher(cuil);
		if (!cuilMatcher.matches()) { errores.append("Formato de CUIL incorrecto (ej: 20-12345678-9). "); }
		
		//si hay errores devuelve los mensajes de error 
		if (errores.length() > 0) { return errores.toString(); } 
		else { return null; } // Todo válido 
	}
	
	/*public boolean validarCliente(String usuario, String contrasena, int dni, String cuil,
            String nombre, String apellido, String sexo, String nacionalidad,
            String fechaNacStr, String direccion, String localidad, String provincia,
            String correo, String telefono) {
		
		
		String dniStr = String.valueOf(dni); 
		
		if (usuario == null || usuario.trim().isEmpty()) return false; 
		if (contrasena == null || contrasena.trim().isEmpty()) return false; 
		if (dniStr == null || dniStr.trim().isEmpty())return false;
		if (cuil == null || cuil.trim().isEmpty()) return false; 
		if (nombre == null || nombre.trim().isEmpty()) return false; 
		if (apellido == null || apellido.trim().isEmpty()) return false; 
		if (sexo == null || sexo.trim().isEmpty()) return false; 
		if (nacionalidad == null || nacionalidad.trim().isEmpty()) return false; 
		if (fechaNacStr == null || fechaNacStr.trim().isEmpty()) return false; 
		if (direccion == null || direccion.trim().isEmpty()) return false; 
		if (localidad == null || localidad.trim().isEmpty()) return false; 
		if (provincia == null || provincia.trim().isEmpty()) return false; 
		if (correo == null || correo.trim().isEmpty()) return false; 
		if (telefono == null || telefono.trim().isEmpty()) return false;
		
		try {
			Integer.parseInt(dniStr);
			java.sql.Date.valueOf(fechaNacStr);
		}
		catch (Exception e) { return false; }
		
		return true;
	}*/
	
	public ArrayList<Cliente> ObtenerListadoClientes(){
		ArrayList<Cliente> lista = new ArrayList<Cliente>();
		lista = daoCliente.obtenerClientes();
		return lista; 
	}

	public ArrayList<String> obtenerColumnasClientes() {
	    return daoCliente.obtenerColumnasClientes();
	}

	public ArrayList<Cliente> obtenerClientesPorFiltro(String columna, String valor) {
	    return daoCliente.obtenerClientesPorFiltro(columna, valor);
	}
	
	public int obtenerIdCliente(String dni) {
		
		int idCliente = daoCliente.obtenerIdCliente(dni);
		if(idCliente != 0) {
			return idCliente;
		}
		else {
			throw new ClienteNoEncontradoExc("No se encontró un cliente con el DNI: "+dni);
		}
	}
	
	
	
}
