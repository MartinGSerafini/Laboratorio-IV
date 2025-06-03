package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import entidades.Seguros;

public class SeguroDao {

	public int obtenerUltimoID() {
		int id = 0;
		String sql = "SELECT MAX(idSeguro) AS maxId FROM seguros";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }
		
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
	
}
