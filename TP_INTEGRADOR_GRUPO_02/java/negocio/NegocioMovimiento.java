package negocio;

import java.util.ArrayList;

import dao.DaoMovimiento;
import entidades.Movimiento;

public class NegocioMovimiento {
	DaoMovimiento daoMovimiento = new DaoMovimiento();
	
	public ArrayList<Movimiento> obtenerMovimientosPorCuenta(String id){
		int idCuenta = Integer.parseInt(id);
		return daoMovimiento.obtenerMovimientosPorCuenta(idCuenta);
	}

}
