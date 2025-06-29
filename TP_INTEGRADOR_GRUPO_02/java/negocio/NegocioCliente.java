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
				throw new OperacionNoEfectuadaExc("No se inserto ningun cliente.");
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
	    return daoCliente.modificarCliente(cliente);
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
		if (
			/*usuario == null || usuario.trim().isEmpty() ||*/ // es autoincremental en la bd
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
		
		if (dniStr.length() != 8) { errores.append("El DNI debe tener 8 dígitos. "); }
				Pattern cuilPattern = Pattern.compile("^\\d{2}-\\d{8}-\\d$");	//uso una expresión regular
		Matcher cuilMatcher = cuilPattern.matcher(cuil);
		if (!cuilMatcher.matches()) { errores.append("Formato de CUIL incorrecto (ej: 20-12345678-9). "); }
		
		if (errores.length() > 0) { return errores.toString(); } 
		else { return null; }
	}
	
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
			throw new ClienteNoEncontradoExc("No se encontro un cliente con el DNI: "+dni);
		}
	}
	public String validarYVerificarCliente(String idCliente, String usuario, String contrasena, int dni, String cuil,
            String nombre, String apellido, String sexo, int nacionalidad,
            String fechaNacStr, String direccion, int localidad, int provincia,
            String correo, String telefono) {

System.out.println("ValidarCliente - Par�metros recibidos: " +
"idCliente=" + idCliente +
", usuario=" + usuario +
", contrasena=" + contrasena +
", dni=" + dni +
", cuil=" + cuil +
", nombre=" + nombre +
", apellido=" + apellido +
", sexo=" + sexo +
", nacionalidad=" + nacionalidad +
", fechaNacStr=" + fechaNacStr +
", direccion=" + direccion +
", localidad=" + localidad +
", provincia=" + provincia +
", correo=" + correo +
", telefono=" + telefono);

StringBuilder errores = new StringBuilder();

// Validar campos String
if (usuario == null || usuario.trim().isEmpty()) {
errores.append("El usuario es obligatorio.\n");
}
if (contrasena == null || contrasena.trim().isEmpty()) {
errores.append("La contrase�a es obligatoria.\n");
}
if (cuil == null || cuil.trim().isEmpty()) {
errores.append("El CUIL es obligatorio.\n");
}
if (nombre == null || nombre.trim().isEmpty()) {
errores.append("El nombre es obligatorio.\n");
}
if (apellido == null || apellido.trim().isEmpty()) {
errores.append("El apellido es obligatorio.\n");
}
if (sexo == null || sexo.trim().isEmpty()) {
errores.append("El sexo es obligatorio.\n");
}
if (fechaNacStr == null || fechaNacStr.trim().isEmpty()) {
errores.append("La fecha de nacimiento es obligatoria.\n");
}
if (direccion == null || direccion.trim().isEmpty()) {
errores.append("La direcci�n es obligatoria.\n");
}
if (correo == null || correo.trim().isEmpty()) {
errores.append("El correo es obligatorio.\n");
}
if (telefono == null || telefono.trim().isEmpty()) {
errores.append("El tel�fono es obligatorio.\n");
}

// Validar campos num�ricos
if (dni <= 0) {
errores.append("El DNI es obligatorio y debe ser un n�mero v�lido.\n");
}
if (nacionalidad <= 0) {
errores.append("Debe seleccionar una nacionalidad v�lida.\n");
}
if (localidad <= 0) {
errores.append("Debe seleccionar una localidad v�lida.\n");
}
if (provincia <= 0) {
errores.append("Debe seleccionar una provincia v�lida.\n");
}

// Validar formatos espec�ficos

// Validar fecha
if (fechaNacStr != null && !fechaNacStr.trim().isEmpty()) {
try {
java.sql.Date.valueOf(fechaNacStr);
} catch (IllegalArgumentException e) {
errores.append("Formato de fecha de nacimiento inv�lido.\n");
}
}

// Validar longitud DNI y CUIL solo si no hay errores anteriores en esos campos
if (dni > 0 && !String.valueOf(dni).matches("\\d{8}")) {
errores.append("El DNI debe tener exactamente 8 d�gitos num�ricos.\n");
}
if (cuil != null && !cuil.trim().isEmpty() && !cuil.matches("\\d{11}")) {
errores.append("El CUIL debe tener exactamente 11 d�gitos num�ricos.\n");
}

// Validar unicidad solo si no hay errores con esos campos
if (errores.length() == 0) {
if (daoCliente.existeDni(dni, idCliente)) {
errores.append("El DNI ya est� registrado para otro cliente.\n");
}
if (daoCliente.existeCuil(cuil, idCliente)) {
errores.append("El CUIL ya est� registrado para otro cliente.\n");
}
if (daoCliente.existeUsuario(usuario, idCliente)) {
errores.append("El usuario ya est� registrado para otro cliente.\n");
}
}

return errores.length() > 0 ? errores.toString() : null;
}


}
