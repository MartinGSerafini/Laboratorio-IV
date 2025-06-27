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
	
	public ArrayList<Prestamo> filtrarPorEstado(String estado) {
		String sql = "SELECT * FROM prestamo where estado_pres="+estado;
		return daoPrestamo.filtrar(sql);
	}

}
