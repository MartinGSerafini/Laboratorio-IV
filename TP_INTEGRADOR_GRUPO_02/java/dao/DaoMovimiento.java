package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entidades.Movimiento;

public class DaoMovimiento {
	
	public ArrayList<Movimiento> obtenerMovimientosPorCuenta(int idCuenta){
		String sql = "SELECT m.id_mov, m.idCuenta_mov, m.fecha_mov, m.detalle_mov, m.importe_mov,"
				+ "m.idTipoMov_mov, tm.descripcion_tipoMov, m.idCuentaDestino_mov, c.cbu_cuenta "
				+ "FROM movimiento m "
				+ "INNER JOIN tipomovimiento tm ON m.idTipoMov_mov = tm.idTipoMovimiento "
				+ "LEFT JOIN cuenta c ON m.idCuentaDestino_mov = c.id_cuenta "
				+ "WHERE m.idCuenta_mov = ? OR m.idCuentaDestino_mov = ?";
		ArrayList<Movimiento> lista = new ArrayList<Movimiento>();
		
		try (Connection conn = Conexion.getConexion();
	             PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, idCuenta);
			ps.setInt(2, idCuenta);
			
		    try (ResultSet rs = ps.executeQuery()) {
		    	while(rs.next()) {
		    		Movimiento m = new Movimiento();
		    		m.setIdMov(rs.getString("id_mov"));
		    		m.setIdCuentaMov(rs.getInt("idCuenta_mov"));
		    		m.setFechaMov(rs.getDate("fecha_mov"));
		    		m.setDetalleMov(rs.getString("detalle_mov"));
		    		m.setImporteMov(rs.getBigDecimal("importe_mov"));
		    		m.setIdTipoMovMov(rs.getInt("idTipoMov_mov"));
		    		m.setDescTipoMov(rs.getString("descripcion_tipoMov"));
		    		m.setIdCuentaDestinoMov(rs.getString("idCuentaDestino_mov"));
		    		m.setCbuCuentaDestino(rs.getString("cbu_cuenta"));
		    		lista.add(m);
		    	}
		  }
		}catch(Exception e) {
			e.printStackTrace();
		}finally {}
		return lista;
	}

}
