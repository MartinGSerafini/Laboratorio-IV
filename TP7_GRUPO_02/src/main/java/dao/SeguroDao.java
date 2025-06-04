package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import entidades.Seguros;

public class SeguroDao {
	
	public void conectar() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	}

	public int obtenerUltimoID() {
		int id = 0;
		String sql = "SELECT MAX(idSeguro) AS maxId FROM seguros";
		
		conectar();
		
		Connection cn = null;
		
		try (Connection con = Conexion.getConexion();
	             PreparedStatement ps = con.prepareStatement(sql);
	             ResultSet rs = ps.executeQuery()) {
			rs.next();
			id=rs.getInt("maxId");
			
		} catch (Exception e){
			e.printStackTrace();
		}
		
		return id;
	}
	
	public ArrayList<Seguros> obtenerTodos(){
		ArrayList<Seguros> lista = new ArrayList<Seguros>();
		TiposeguroDao ts = new TiposeguroDao();
		
		String sql = "Select * from seguros";
		conectar();
		Connection cn = null;
		try (Connection con = Conexion.getConexion();
	             PreparedStatement ps = con.prepareStatement(sql);
	             ResultSet rs = ps.executeQuery()) {
			while(rs.next()) {
				Seguros s = new Seguros();
				s.setIdSeguro(rs.getInt("idSeguro"));
				s.setDescripcion(rs.getString("descripcion"));
				s.setTipoSeguro(ts.obtenerTipoSeguro(rs.getInt("idTipo")));
				s.setCostoContratacion(rs.getDouble("costoContratacion"));
				s.setCostoAsegurado(rs.getDouble("costoAsegurado"));
				lista.add(s);
			}
			
		} catch (Exception e){
			e.printStackTrace();
		}
		return lista;
	}
	
	public int agregarSeguro(Seguros seguro) {
	    String sql = "INSERT INTO seguros (descripcion, idTipo, costoContratacion, costoAsegurado) "
	            + "VALUES (?, ?, ?, ?)";
	    conectar(); 
	    int filas = 0;
	    try (Connection con = Conexion.getConexion(); 
	         PreparedStatement ps = con.prepareStatement(sql)) { 
	            
	            ps.setString(1, seguro.getDescripcion());
	            ps.setInt(2, seguro.getTipoSeguro().getIdTipo());
	            ps.setDouble(3, seguro.getCostoContratacion());
	            ps.setDouble(4, seguro.getCostoAsegurado());

	            filas = ps.executeUpdate(); 
	        
	    }catch (Exception e){
	        e.printStackTrace();
	    }
	    return filas;	
	}
	
	
	
	
}
