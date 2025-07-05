package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entidades.Prestamo;
import entidades.EstadoPrestamo;


public class daoPrestamo {
	
	public ArrayList<EstadoPrestamo>obtenerEstados(){
		String sql = "SELECT * from estadoprestamos";
		ArrayList<EstadoPrestamo> lista = new ArrayList<EstadoPrestamo>();
		try (Connection conn = Conexion.getConexion();
				Statement st = conn.createStatement();) {
			    ResultSet rs = st.executeQuery(sql);
			    while(rs.next()) {
			    	EstadoPrestamo estadoPrestamo = new EstadoPrestamo(rs.getInt("id_estadoPrestamos"),rs.getString("desc_estadoPrestamo"));
			    	lista.add(estadoPrestamo);
			    }
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {}
		return lista;
	}

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
	
	private void cargar(ArrayList<Prestamo> lista, ResultSet rs) throws SQLException {
		while(rs.next()) {
			int estado = rs.getInt("estado_prestamo");
			if(estado == 1) {
				
				Prestamo prestamo = new Prestamo();
				prestamo.setIdPrestamo(rs.getString("id_prestamo"));
				prestamo.setIdClientePres(rs.getInt("idCliente_pres"));
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
	}
	
	public ArrayList<Prestamo> obtenerTodos(){
		String sql = "SELECT *  from prestamo WHERE estado_prestamo = TRUE ORDER BY (estado_pres = 3) DESC, fechaSolicitud_pres DESC;";
		ArrayList<Prestamo> lista = new ArrayList<Prestamo>();
		try (Connection conn = Conexion.getConexion();
				Statement st = conn.createStatement();) {
			    ResultSet rs = st.executeQuery(sql);
			cargar(lista,rs);
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {}
		return lista;
	}
	
	public int modificarEstadoPrestamo(String estado, String id) {
		String sql = "UPDATE prestamo SET estado_pres =" + estado + " WHERE id_prestamo='"+id+"';";
		int filas = 0;
		try (Connection conn = Conexion.getConexion();
		         PreparedStatement ps = conn.prepareStatement(sql)) {
		        
		        filas = ps.executeUpdate();
		        return filas;

		    } catch (SQLException e) {
		        e.printStackTrace();
		        return filas;
		    }
	}
	
	public ArrayList<Prestamo> filtrar(String sql){
		ArrayList<Prestamo> lista = new ArrayList<Prestamo>();
		

   try (Connection conn = Conexion.getConexion();
        PreparedStatement ps = conn.prepareStatement(sql)) {
       try (ResultSet rs = ps.executeQuery()) {
           cargar(lista,rs);
       }

   } catch (Exception e) {
       e.printStackTrace();
   }
		return lista;
	}
	
	public ArrayList<String> obtenerColumnasPrestamo() {
        ArrayList<String> columnas = new ArrayList<>();
        String sql = "SHOW COLUMNS FROM prestamo";
        try (Connection conn = Conexion.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String nombreColumna = rs.getString("Field");
                if (!nombreColumna.equalsIgnoreCase("id_prestamo") && !nombreColumna.equalsIgnoreCase("estado_prestamo") &&
                	!nombreColumna.equalsIgnoreCase("fechaSolicitud_pres") &&
                !nombreColumna.equalsIgnoreCase("importeTotal_pres") && !nombreColumna.equalsIgnoreCase("montoCuota_pres")) {
                    columnas.add(nombreColumna);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return columnas;
    }

}
