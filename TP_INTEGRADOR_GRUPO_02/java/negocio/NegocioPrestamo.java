package negocio;

import java.util.ArrayList;

import dao.daoPrestamo;
import entidades.EstadoPrestamo;
import entidades.Prestamo;

public class NegocioPrestamo {
	daoPrestamo daoPrestamo = new daoPrestamo();

	public ArrayList<Prestamo> listarPrestamos() {
		return daoPrestamo.obtenerTodos();
	}
	
	public int modificarEstadoPrestamo(String estado, String idPrestamo) {
		return daoPrestamo.modificarEstadoPrestamo(estado, idPrestamo);
	}
	
	public ArrayList<EstadoPrestamo> obtenerEstados(){
		return daoPrestamo.obtenerEstados();
	}
	
	public ArrayList<String> obtenerColumnas(){
		return daoPrestamo.obtenerColumnasPrestamo();
	}
	
	public ArrayList<Prestamo> filtrarPorEstado(String estado) {
		String sql = "SELECT * FROM prestamo where estado_pres="+estado;
		return daoPrestamo.filtrar(sql);
	}
	
	public ArrayList<Prestamo> filtrarBusqueda(String columna, String valor) {
		if(validarDatos(valor)) {
			String sql = "SELECT * FROM prestamo WHERE prestamo." + columna + " = '" + valor + "' AND estado_prestamo = 1";
		return daoPrestamo.filtrar(sql);
		}
		return null;
	}
	
	private boolean validarDatos(String valor) {
		if(valor.matches("\\d+")) {
			return true;
		}
		return false;
	}

}
