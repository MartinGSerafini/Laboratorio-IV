package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
	
	public ArrayList<Cuenta> obtenerCuentas(){
		String sql = "SELECT c.*, tc.descripcion_tipocuenta FROM Cuenta c INNER JOIN tipocuenta tc ON c.idTipoCuenta_cuenta = tc.idTipoCuenta WHERE c.estado_cuentas = 1";
		ArrayList<Cuenta> lista = new ArrayList<Cuenta>();
		try (Connection conn = Conexion.getConexion();
				Statement st = conn.createStatement();) {
			    ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()) {	
				
				Cuenta cuenta = new Cuenta();
				cuenta.setIdCuenta(rs.getString("id_cuenta"));
				cuenta.setIdClienteCuenta(rs.getInt("idCliente_cuenta"));
				cuenta.setIdTipoCuentaCuenta(rs.getInt("idTipoCuenta_cuenta"));
				cuenta.setFechaCreacionCuenta(rs.getDate("fechaCreacion_cuenta"));
				cuenta.setNumeroCuenta(rs.getString("numero_cuenta"));
				cuenta.setCbuCuenta(rs.getString("cbu_cuenta"));
				cuenta.setSaldoCuenta(rs.getBigDecimal("saldo_cuenta"));
				cuenta.setTipoCuentaCuenta(rs.getString("descripcion_tipocuenta"));
				
				lista.add(cuenta);
			}
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {}
		return lista;
	}
	
	 public ArrayList<Cuenta> obtenerCuentasPorFiltro(String columna, String valor) {
	        ArrayList<Cuenta> listaFiltrada = new ArrayList<Cuenta>();
	        String sql = "SELECT c.*, tc.descripcion_tipocuenta" + 
	        " FROM Cuenta c " + 
	        "INNER JOIN tipocuenta tc ON c.idTipoCuenta_cuenta = tc.idTipoCuenta" + 
	        " WHERE c." + columna + " LIKE ? AND c.estado_cuentas = 1";

	        try (Connection conn = Conexion.getConexion();
	             PreparedStatement ps = conn.prepareStatement(sql)) {

	            ps.setString(1, "%" + valor + "%");
	            try (ResultSet rs = ps.executeQuery()) {
	                while (rs.next()) {
	                	Cuenta cuenta = new Cuenta();
	    				cuenta.setIdCuenta(rs.getString("id_cuenta"));
	    				cuenta.setIdClienteCuenta(rs.getInt("idCliente_cuenta"));
	    				cuenta.setFechaCreacionCuenta(rs.getDate("fechaCreacion_cuenta"));
	    				cuenta.setNumeroCuenta(rs.getString("numero_cuenta"));
	    				cuenta.setCbuCuenta(rs.getString("cbu_cuenta"));
	    				cuenta.setSaldoCuenta(rs.getBigDecimal("saldo_cuenta"));
	    				cuenta.setTipoCuentaCuenta(rs.getString("descripcion_tipocuenta"));
	    				
	    				listaFiltrada.add(cuenta);
	                }
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return listaFiltrada;
	    }
	 
	 public ArrayList<String> obtenerColumnasCuentas() {
	        ArrayList<String> columnas = new ArrayList<>();
	        String sql = "SHOW COLUMNS FROM cuenta";
	        try (Connection conn = Conexion.getConexion();
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery(sql)) {
	            while (rs.next()) {
	                String nombreColumna = rs.getString("Field");
	                if (!nombreColumna.equalsIgnoreCase("estado_cuentas")) {
	                    columnas.add(nombreColumna);
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return columnas;
	    }

	 public boolean bajaLogicaCuenta(String idCuenta) {
	        String sql = "UPDATE cuenta SET estado_cuentas = 0 WHERE id_cuenta = ?";
	        try (Connection conn = Conexion.getConexion();
	             PreparedStatement ps = conn.prepareStatement(sql)) {
	            
	            ps.setString(1, idCuenta);
	            int filasActualizadas = ps.executeUpdate();
	            return filasActualizadas > 0;
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	    } 
	 
	 public boolean modificarCuenta(Cuenta cuenta) {
	        String sql = "UPDATE cuenta SET " +
	            "numero_cuenta = ?, " +
	            "cbu_cuenta = ?, " +
	            "saldo_cuenta = ?, " +
	            "idTipoCuenta_cuenta = ?, "+
	            "WHERE id_cuenta = ? AND estado_cuentas = 1";

	        try (Connection conn = Conexion.getConexion();
	             PreparedStatement ps = conn.prepareStatement(sql)) {

	            ps.setString(4, cuenta.getNumeroCuenta());
	            ps.setString(6, cuenta.getCbuCuenta());
	            ps.setBigDecimal(7, cuenta.getSaldoCuenta());
	            ps.setInt(8, cuenta.getIdTipoCuentaCuenta());

	            int filasActualizadas = ps.executeUpdate();
	            return filasActualizadas > 0;

	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	 
	 public boolean existeCBU(String cbu) {
	        String sql = "SELECT COUNT(*) FROM cuenta WHERE cbu_cuenta = ?";
	        try (Connection conn = Conexion.getConexion();
	             PreparedStatement ps = conn.prepareStatement(sql)) {
	            ps.setString(1, cbu);
	            try (ResultSet rs = ps.executeQuery()) {
	                if (rs.next()) {
	                    return rs.getInt(1) > 0;
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }
	
}
