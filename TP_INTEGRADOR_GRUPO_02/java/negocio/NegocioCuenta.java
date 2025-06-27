package negocio;

import java.util.ArrayList;
import java.util.List;

import dao.daoCuenta;
import entidades.Cuenta;
import excepciones.OperacionNoEfectuadaExc;
import interfaces.servicioABML;

public class NegocioCuenta implements servicioABML<Cuenta>{

	daoCuenta daoCuenta = new daoCuenta();
	
	@Override
	public boolean alta(Cuenta cuenta) {
		try {
			boolean filas= daoCuenta.altaCuenta(cuenta);
			
			if(filas==false) {
				throw new OperacionNoEfectuadaExc("No se insertó ningúna cuenta.");
			}
			
			return true;
		}
		catch(OperacionNoEfectuadaExc e) {
			System.out.println("Error: "+e.getMessage());
			return false;
		}
	}

	@Override
	public boolean baja(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modificacion(String id, Cuenta entidad) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean lectura(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Cuenta> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Cuenta> ObtenerListadoCuentas(){
		ArrayList<Cuenta> lista = new ArrayList<Cuenta>();
		lista = daoCuenta.obtenerCuentas();
		return lista;
	}
	
	public ArrayList<Cuenta> obtenerCuentasPorFiltro(String columna, String valor){
		ArrayList<Cuenta> lista = new ArrayList<Cuenta>();
		lista = daoCuenta.obtenerCuentasPorFiltro(columna,valor);
		return lista;
	}
	
	public ArrayList<String> obtenerColumnasCuentas(){
		ArrayList<String> columnas = new ArrayList<String>();
		columnas = daoCuenta.obtenerColumnasCuentas();
		return columnas;
	}
	
	public boolean bajaCuenta(String id) {
	    return daoCuenta.bajaLogicaCuenta(id);
	}
	
}
