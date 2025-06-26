package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import entidades.Prestamo;
import entidades.EstadoPrestamo;


public class daoPrestamo {
	
	public EstadoPrestamo obtenerEstadoPrestamo(int id) {
		EstadoPrestamo estadoPrestamo = new EstadoPrestamo();
		String sql = "SELECT * FROM estadoprestamos WHERE id_estadoPrestamos="+id;
		try (Connection con = Conexion.getConexion();
                Statement st = (Statement) con.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
    		
    			rs.next();
    			estadoPrestamo.setId(rs.getInt("id_estadoPrestamos"));
                estadoPrestamo.setDescripcion(rs.getString("desc_estadoPrestamo"));
           } catch (Exception e) {
               e.printStackTrace();
           }
		return estadoPrestamo;
	}
	
	public ArrayList<Prestamo> obtenerTodos(){
		String sql = "SELECT *  from prestamo WHERE estado_prestamo = TRUE ORDER BY (estado_pres = 3) DESC, fechaSolicitud_pres DESC;";
		ArrayList<Prestamo> lista = new ArrayList<Prestamo>();
		try (Connection conn = Conexion.getConexion();
				Statement st = conn.createStatement();) {
			    ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()) {
				int estado = rs.getInt("estado_prestamo");
				if(estado == 1) {
				
				Prestamo prestamo = new Prestamo();
				prestamo.setIdPrestamo(rs.getString("id_prestamo"));
				prestamo.setIdCuentaDepositoPres(rs.getString("idCliente_pres"));
				prestamo.setFechaSolicitudPres(rs.getDate("fechaSolicitud_pres"));
				prestamo.setImporteSolicitadoPres(rs.getBigDecimal("importeSolicitado_pres"));
				prestamo.setImporteTotalPres(rs.getBigDecimal("importeTotal_pres"));
				prestamo.setPlazoMesesPres(rs.getInt("plazoMeses_pres"));
				prestamo.setMontoCuotaPres(rs.getBigDecimal("montoCuota_pres"));
				prestamo.setEstadoPres(obtenerEstadoPrestamo(rs.getInt("estado_pres")));
				prestamo.setIdCuentaDepositoPres(rs.getString("idCuentaDeposito_pres"));
				lista.add(prestamo);
				}
			}
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {}
		return lista;
	}

}
