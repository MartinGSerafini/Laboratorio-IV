package negocio;

import java.math.BigDecimal;

import dao.DaoMovimientos;

public class NegocioMovimientos {
	
	DaoMovimientos daoMovimientos = new DaoMovimientos();
	
	public boolean registrarMovimientoTransferencia(int idCuentaOrigen, int idCuentaDestino, BigDecimal importe, String detalle) { 
		
		return daoMovimientos.registrarMovimientoTransferencia(idCuentaOrigen, idCuentaDestino, importe, detalle);
		
	}
	
}
