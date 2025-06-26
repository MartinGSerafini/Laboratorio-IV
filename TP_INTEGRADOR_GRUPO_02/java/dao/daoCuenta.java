package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import entidades.Cuenta;

public class daoCuenta {
	
	public boolean altaCuenta(Cuenta cuenta) {
		
		String sql = "INSERT INTO Cuenta(idCliente_cuenta, idTipoCuenta_cuenta, fechaCreacion_cuenta, numero_cuenta, cbu_cuenta, saldo_cuenta, estado_cuentas)"+
		"VALUES(?, ?, ?, ?, ?, 0, 1)";
		
		 try (Connection conn = Conexion.getConexion();
		        
				PreparedStatement ps = conn.prepareStatement(sql)) {

				 ps.setInt(1, cuenta.getIdClienteCuenta());
				 ps.setInt(2, cuenta.getIdTipoCuentaCuenta());
				 ps.setDate(3, new java.sql.Date(cuenta.getFechaCreacionCuenta().getTime()));
				 ps.setString(4, cuenta.getNumeroCuenta());
				 ps.setString(5, cuenta.getCbuCuenta());
		        
		        int filasInsertadas = ps.executeUpdate();
		        return filasInsertadas > 0;

		    } catch (SQLException e) {
		        e.printStackTrace();
		        return false;
		    }
		
		
		
	}
	
	
	
}
