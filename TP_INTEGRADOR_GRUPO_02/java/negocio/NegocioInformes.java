package negocio;

import java.util.ArrayList;
import java.util.Date;

import dao.DaoInformes;
import entidades.GeneroClientes;
import entidades.MovimientoReporte;
import entidades.ProvinciaCantidad;

public class NegocioInformes {
	
	DaoInformes daoInformes = new DaoInformes();
	
	public ArrayList<ProvinciaCantidad> top3provincias(){
		ArrayList<ProvinciaCantidad> lista = daoInformes.top3provincias();
		
		return lista;
	}
	
	public ArrayList<GeneroClientes> obtenerCantidadGenero(){
		ArrayList<GeneroClientes> lista = daoInformes.obtenerCantidadGenero();
		
		return lista;
	}
	
	public ArrayList<MovimientoReporte> obtenerReporteMovimientos(Date fechaInicio, Date fechaFin) {
        return daoInformes.obtenerMovimientosPorFecha(fechaInicio, fechaFin);
    }
	
}
 