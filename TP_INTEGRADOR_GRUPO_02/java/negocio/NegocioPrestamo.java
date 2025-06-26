package negocio;

import java.util.ArrayList;

import dao.daoPrestamo;
import entidades.Prestamo;

public class NegocioPrestamo {
	daoPrestamo daoPrestamo = new daoPrestamo();

	public ArrayList<Prestamo> listarPrestamos() {
		return daoPrestamo.obtenerTodos();
	}

}
