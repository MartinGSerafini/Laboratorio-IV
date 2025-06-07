package negocio;

import java.util.ArrayList;

import dao.SeguroDao;
import dao.TiposeguroDao;
import entidades.Seguros;
import entidades.TipoSeguro;

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
	
	public boolean validarSeguro(String descripcion, String idTipoStr, String costoContratacionStr, String costoAseguradoStr) {
        boolean todoValido = true;

        if (descripcion == null || descripcion.trim().isEmpty()) { todoValido = false; }
        try {Integer.parseInt(idTipoStr);} 
        catch (NumberFormatException e) { todoValido = false; }

        try { Double.parseDouble(costoContratacionStr); } 
        catch (NumberFormatException e) { todoValido = false; }

        try {Double.parseDouble(costoAseguradoStr); } 
        catch (NumberFormatException e) { todoValido = false; }
        
        return todoValido;
    }
	
	public boolean agregarSeguro(String descripcion, int idTipo, double costoContratacion, double costoAsegurado) { 
		Seguros s = new Seguros();
	    s.setDescripcion(descripcion);
	    s.setCostoContratacion(costoContratacion);
	    s.setCostoAsegurado(costoAsegurado);

	    TipoSeguro tipo = new TipoSeguro();
	    tipo.setIdTipo(idTipo);
	    s.setTipoSeguro(tipo);

	    SeguroDao dao = new SeguroDao();
	    int filas = dao.agregarSeguro(s);
        return filas > 0;
	}
}
