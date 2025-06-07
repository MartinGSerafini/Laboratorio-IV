package negocio;

import java.util.List;

import dao.TiposeguroDao;
import entidades.TipoSeguro;

public class NegocioTipoSeguro {
	private TiposeguroDao tipoSeguroDao = new TiposeguroDao();
	
	public List<TipoSeguro> obtenerTodos(){
		return tipoSeguroDao.obtenerTodos();
	}
}
