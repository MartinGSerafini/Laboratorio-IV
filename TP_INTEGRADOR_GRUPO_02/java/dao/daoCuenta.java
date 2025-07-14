package dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
	        String sql = "SELECT c.*, tc.descripcion_tipocuenta FROM Cuenta c INNER JOIN tipocuenta tc ON c.idTipoCuenta_cuenta = tc.idTipoCuenta WHERE c." + columna + " LIKE ? AND c.estado_cuentas = 1";

	        try (Connection conn = Conexion.getConexion();
	             PreparedStatement ps = conn.prepareStatement(sql)) {

	        	ps.setString(1,"%" + valor + "%");
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
	        String sql = "UPDATE cuenta SET numero_cuenta = ?, idCliente_cuenta = ?, cbu_cuenta = ?, saldo_cuenta = ?, idTipoCuenta_cuenta = ? WHERE id_cuenta = ? AND estado_cuentas = 1";

	        try (Connection conn = Conexion.getConexion();
	             PreparedStatement ps = conn.prepareStatement(sql)) {

	            ps.setString(1, cuenta.getNumeroCuenta());
	            ps.setInt(2, cuenta.getIdClienteCuenta());
	            ps.setString(3, cuenta.getCbuCuenta());
	            ps.setBigDecimal(4, cuenta.getSaldoCuenta());
	            ps.setInt(5, cuenta.getIdTipoCuentaCuenta());
	            ps.setString(6, cuenta.getIdCuenta());

	            int filasActualizadas = ps.executeUpdate();
	            return filasActualizadas > 0;

	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	 
	 public boolean existeCBU(String cbu, String id) {
	        String sql = "SELECT COUNT(*) FROM cuenta WHERE cbu_cuenta = ? AND id_cuenta = ?";
	        try (Connection conn = Conexion.getConexion();
	             PreparedStatement ps = conn.prepareStatement(sql)) {
	            ps.setString(1, cbu);
	            ps.setString(2, id);
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
	 
	 public int contarCuentasXClientes(int cliente) {
		 String sql = "SELECT COUNT(*) FROM cuenta WHERE idCliente_cuenta=?";
		 int cantCuentas = 0;
	        try (Connection conn = Conexion.getConexion();
	             PreparedStatement ps = conn.prepareStatement(sql)) {
	            ps.setInt(1, cliente);
	            try (ResultSet rs = ps.executeQuery()) {
	                if (rs.next()) {
	                    cantCuentas = rs.getInt(1);
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return cantCuentas;
	 }
	
	 public int buscarCuentaXIdCliente(String id) {
		 String sql = "SELECT idCliente_cuenta FROM cuenta WHERE id_cuenta=?";
		 int idCliente = 0;
	    	
	    	try (Connection conn = Conexion.getConexion();
		             PreparedStatement ps = conn.prepareStatement(sql)) {
		            
		            ps.setString(1, id);
		            try (ResultSet rs = ps.executeQuery()) {
		            	
		            	if (rs.next()) {
		                    idCliente = rs.getInt("idCliente_cuenta");
		                }
		            	
		            }
		            
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
	    	return idCliente;
	 }
	 
	 public List<Cuenta> obtenerCuentasPorCliente(int idCliente) {
		    List<Cuenta> cuentas = new ArrayList<>();

		    String query = "SELECT c.Numero_Cuenta, c.CBU_Cuenta, c.Saldo_Cuenta, t.Descripcion_TipoCuenta " +
		                   "FROM Cuenta c " +
		                   "INNER JOIN TipoCuenta t ON c.IdTipoCuenta_Cuenta = t.IdTipoCuenta " +
		                   "WHERE c.IdCliente_Cuenta = ?";

		    try (Connection conn = Conexion.getConexion();
		         PreparedStatement ps = conn.prepareStatement(query)) {

		        ps.setInt(1, idCliente);

		        ResultSet rs = ps.executeQuery();
		        while (rs.next()) {
		            Cuenta c = new Cuenta();
		            c.setNumeroCuenta(rs.getString("Numero_Cuenta"));
		            c.setCbuCuenta(rs.getString("CBU_Cuenta"));
		            c.setSaldoCuenta(rs.getBigDecimal("Saldo_Cuenta"));
		            c.setTipoCuentaCuenta(rs.getString("Descripcion_TipoCuenta")); 

		            cuentas.add(c);
		        }

		    } catch (SQLException e) {
		        e.printStackTrace(); 
		    }

		    return cuentas;
		}

	 
	 
	 public ArrayList<Cuenta> cuentasXCliente(int idCliente){
		 String sql = "SELECT c.*, tc.descripcion_tipocuenta FROM Cuenta c INNER JOIN tipocuenta tc ON c.idTipoCuenta_cuenta = tc.idTipoCuenta WHERE idCliente_cuenta = ? AND c.estado_cuentas = 1";
			ArrayList<Cuenta> lista = new ArrayList<Cuenta>();
			
			try (Connection conn = Conexion.getConexion();
					
		            PreparedStatement ps = conn.prepareStatement(sql)) {

		        	ps.setInt(1,idCliente);
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
		    				
		    				lista.add(cuenta);
		                }
		            }

		        } catch (Exception e) {
		            e.printStackTrace();
		        }
			return lista;
	 }

	 
	 public Cuenta obtenerCuentaCBU(String cbu){
			String sql = "SELECT c.*, tc.descripcion_tipocuenta FROM Cuenta c INNER JOIN tipocuenta tc ON c.idTipoCuenta_cuenta = tc.idTipoCuenta WHERE c.estado_cuentas = 1 AND c.cbu_cuenta = ?";
			Cuenta cuenta = new Cuenta();
			
			try (Connection conn = Conexion.getConexion();
					
					PreparedStatement ps = conn.prepareStatement(sql)) {
	        		ps.setString(1,cbu);
	        		
	        		try(ResultSet rs = ps.executeQuery()){
	        			
	        			if (rs.next()) {
	    					
	        				cuenta.setIdCuenta(rs.getString("id_cuenta"));
	    					cuenta.setIdClienteCuenta(rs.getInt("idCliente_cuenta"));
	    					cuenta.setIdTipoCuentaCuenta(rs.getInt("idTipoCuenta_cuenta"));
	    					cuenta.setFechaCreacionCuenta(rs.getDate("fechaCreacion_cuenta"));
	    					cuenta.setNumeroCuenta(rs.getString("numero_cuenta"));
	    					cuenta.setCbuCuenta(rs.getString("cbu_cuenta"));
	    					cuenta.setSaldoCuenta(rs.getBigDecimal("saldo_cuenta"));
	    					cuenta.setTipoCuentaCuenta(rs.getString("descripcion_tipocuenta"));
	        		   }
	        		}
	        		conn.close();
	        		
	        	 }catch(Exception e) {
					e.printStackTrace();
				}finally {}
				return cuenta;
			}
	 
	 
	 public boolean realizarTransferencia(String cbuOrigen, String cbuDestino, BigDecimal monto) {
		 
		 
		 String sql1 = "UPDATE Cuenta SET saldo_cuenta = saldo_cuenta - ? WHERE cbu_cuenta = ?";
		 String sql2 = "UPDATE Cuenta SET saldo_cuenta = saldo_cuenta + ? WHERE cbu_cuenta = ?";
		 
		 
		 try (Connection conn = Conexion.getConexion()) {
		        conn.setAutoCommit(false);

		        try (
		            PreparedStatement ps1 = conn.prepareStatement(sql1);
		            PreparedStatement ps2 = conn.prepareStatement(sql2)
		        ) {
		            ps1.setBigDecimal(1, monto);
		            ps1.setString(2, cbuOrigen);

		            ps2.setBigDecimal(1, monto);
		            ps2.setString(2, cbuDestino);

		            int filas1 = ps1.executeUpdate();
		            int filas2 = ps2.executeUpdate();

		            if (filas1 == 1 && filas2 == 1) {
		                conn.commit();
		                return true;
		            } else {
		                conn.rollback();
		                return false;
		            }

		        } catch (Exception e) {
		            conn.rollback();
		            e.printStackTrace();
		            return false;
		        }

		    } catch (Exception e) {
		        e.printStackTrace();
		        return false;
		    }
		 
		 
	 }

}
