package negocio;

import java.util.ArrayList;

import dao.SeguroDao;
import dao.TiposeguroDao;
import entidades.Seguros;

public class NegocioSeguro {
	private SeguroDao seguroDao = new SeguroDao();
	private TiposeguroDao tipoSeguroDao = new TiposeguroDao();
	
	
	public int obtenerUltimoID() {
		return seguroDao.obtenerUltimoID();
	}
	
	public ArrayList<Seguros> obtenerTodos(){
		return seguroDao.obtenerTodos();
	}
	
	public ArrayList<Seguros> obtenerPorTipo(int idTipo){
		return seguroDao.obtenerPorTipo(idTipo);
	}
	
	public void agregarSeguro(String descripcion, String idTipo, String costoContratacion, String costoAsegurado) {
		
	}
	
}
