package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import entidades.GeneroClientes;
import entidades.MovimientoReporte;
import entidades.ProvinciaCantidad;

public class DaoInformes {
	
	public ArrayList<ProvinciaCantidad> top3provincias(){
		
		ArrayList<ProvinciaCantidad> lista = new ArrayList<>();

	    String sql = "SELECT p.nombre_provincia, COUNT(*) AS cantidad " +
	                 "FROM Cliente c " +
	                 "JOIN Provincia p ON c.provincia_cliente = p.id_provincia " +
	                 "GROUP BY p.nombre_provincia " +
	                 "ORDER BY cantidad DESC " +
	                 "LIMIT 3";

	    try (Connection conn = Conexion.getConexion();
	         PreparedStatement ps = conn.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            String nombre = rs.getString("nombre_provincia");
	            int cantidad = rs.getInt("cantidad");
	            lista.add(new ProvinciaCantidad(nombre, cantidad));
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return lista;
	}
		
	
	
		public ArrayList<GeneroClientes> obtenerCantidadGenero() {
	    ArrayList<GeneroClientes> lista = new ArrayList<>();

	    String sql = "SELECT sexo_cliente, COUNT(*) AS cantidad " +
	                 "FROM Cliente " +
	                 "GROUP BY sexo_cliente";

	    try (Connection conn = Conexion.getConexion();
	         PreparedStatement ps = conn.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            String sexo = rs.getString("sexo_cliente");
	            int cantidad = rs.getInt("cantidad");
	            lista.add(new GeneroClientes(sexo, cantidad));
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return lista;
	}
	
		
		 public ArrayList<MovimientoReporte> obtenerMovimientosPorFecha(Date fechaInicio, Date fechaFin) {
		        ArrayList<MovimientoReporte> lista = new ArrayList<>();
		        String sql = "SELECT id_mov, idCuenta_mov, fecha_mov, detalle_mov, importe_mov, idTipoMov_mov, idCuentaDestino_mov " +
		                     "FROM Movimiento " +
		                     "WHERE fecha_mov BETWEEN ? AND ? " +
		                     "ORDER BY fecha_mov";

		        try (Connection conn = Conexion.getConexion();
		             PreparedStatement ps = conn.prepareStatement(sql)) {

		            // Pasamos fechas como java.sql.Date
		            ps.setDate(1, new java.sql.Date(fechaInicio.getTime()));
		            ps.setDate(2, new java.sql.Date(fechaFin.getTime()));

		            try (ResultSet rs = ps.executeQuery()) {
		                while (rs.next()) {
		                    MovimientoReporte movimiento = new MovimientoReporte();
		                    movimiento.setIdMov(rs.getInt("id_mov"));
		                    movimiento.setIdCuenta(rs.getInt("idCuenta_mov"));
		                    movimiento.setFechaMov(rs.getDate("fecha_mov"));
		                    movimiento.setDetalleMov(rs.getString("detalle_mov"));
		                    movimiento.setImporteMov(rs.getBigDecimal("importe_mov"));
		                    movimiento.setIdTipoMov(rs.getInt("idTipoMov_mov"));
		                    
		                    // idCuentaDestino_mov puede ser null, chequeamos antes
		                    int idCuentaDestino = rs.getInt("idCuentaDestino_mov");
		                    if (rs.wasNull()) {
		                        movimiento.setIdCuentaDestino(null);
		                    } else {
		                        movimiento.setIdCuentaDestino(idCuentaDestino);
		                    }

		                    lista.add(movimiento);
		                }
		            }
		        } catch (Exception e) {
		            e.printStackTrace();
		            // Manejo de excepciones según convenga
		        }
		        return lista;
		    }
		
	
		

		
		
		
	
	
			
}
