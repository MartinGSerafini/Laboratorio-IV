package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DaoMovimientos {
	
	public boolean registrarMovimientoTransferencia(int idCuentaOrigen, int idCuentaDestino, BigDecimal importe, String detalle) {
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    try {
	        conn = Conexion.getConexion();
	        String sql = "INSERT INTO movimiento (idCuenta_mov, fecha_mov, detalle_mov, importe_mov, idTipoMov_mov, idCuentaDestino_mov) " +
	                     "VALUES (?, CURRENT_DATE(), ?, ?, 4, ?)";

	        stmt = conn.prepareStatement(sql);
	        stmt.setInt(1, idCuentaOrigen);
	        stmt.setString(2, detalle); 
	        stmt.setBigDecimal(3, importe);
	        stmt.setInt(4, idCuentaDestino);

	        int filasInsertadas = stmt.executeUpdate();
	        return filasInsertadas > 0;

	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    } finally {
	        try {
	            if (stmt != null) stmt.close();
	            if (conn != null) conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	public boolean registrarMovimiento(int idCuenta, BigDecimal importe, int idTipoMovimiento, String detalle) {
	    String sql = "INSERT INTO MOVIMIENTO (idCuenta_mov, fecha_mov, detalle_mov, importe_mov, idTipoMov_mov) " +
	                 "VALUES (?, CURRENT_DATE, ?, ?, ?)";

	    try (Connection conn = Conexion.getConexion();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setInt(1, idCuenta);
	        ps.setString(2, detalle); 
	        ps.setBigDecimal(3, importe);
	        ps.setInt(4, idTipoMovimiento); 

	        int filasAfectadas = ps.executeUpdate();
	        
	        return filasAfectadas > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

}
