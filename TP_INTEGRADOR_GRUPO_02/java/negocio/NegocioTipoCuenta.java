package negocio;

import java.util.ArrayList;

import dao.DaoTipoCuenta;
import entidades.TipoCuenta;

public class NegocioTipoCuenta {

	public ArrayList<TipoCuenta>obtenerListaTipos(){
		DaoTipoCuenta dao = new DaoTipoCuenta();
		return dao.obtenerListaTipos();
	}
	
}
