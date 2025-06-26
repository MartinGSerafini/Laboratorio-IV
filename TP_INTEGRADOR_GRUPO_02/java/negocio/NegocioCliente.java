package negocio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;

import dao.Conexion;
import dao.daoCliente;
import entidades.Cliente;
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

	public boolean validarCliente(String usuario, String contrasena, int dni, String cuil,
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
}
