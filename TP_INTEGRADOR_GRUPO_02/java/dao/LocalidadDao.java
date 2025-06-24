package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidades.Localidad; 

public class LocalidadDao {
	
	//---lista las localidades segun el id de provincia----
	public List<Localidad> obtenerLocalidadesPorProvincia(int id_provincia) {
	ArrayList<Localidad> localidades = new ArrayList<>();
	String sql = "SELECT nombre_localidad FROM localidad WHERE id_provincia = "+ id_provincia;
	
	try (Connection cn = Conexion.getConexion();
			PreparedStatement ps = cn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery()){
		
		while(rs.next()) {
			Localidad l = new Localidad(); 
			l.setId_pcia(rs.getInt("id_provincia"));
			l.setLocalidad(rs.getString("nombre_localidad"));
			localidades.add(l); 
		}
	}
	catch(SQLException e) { e.printStackTrace(); }
	 
	return localidades;
 }
 
}
