package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidades.Localidad; 

public class LocalidadDao {
	
	public List<Localidad> obtenerLocalidadesPorProvincia(int id_provincia) {
	    List<Localidad> localidades = new ArrayList<>();
	    String sql = "SELECT id_localidad, nombre_localidad FROM localidad WHERE id_provincia = ?";
	    try (Connection cn = Conexion.getConexion();
	         PreparedStatement ps = cn.prepareStatement(sql)) {

	        ps.setInt(1, id_provincia);
	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                Localidad l = new Localidad();
	                l.setId(rs.getInt("id_localidad"));
	                l.setLocalidad(rs.getString("nombre_localidad"));
	                l.setId_pcia(id_provincia); // opcional
	                localidades.add(l);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return localidades;
	}
	public List<Localidad> obtenerTodasLasLocalidades() {
	    List<Localidad> localidades = new ArrayList<>();
	    String sql = "SELECT id_localidad, nombre_localidad, id_provincia FROM localidad";
	    try (Connection cn = Conexion.getConexion();
	         PreparedStatement ps = cn.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            Localidad l = new Localidad();
	            l.setId(rs.getInt("id_localidad"));
	            l.setLocalidad(rs.getString("nombre_localidad"));
	            l.setId_pcia(rs.getInt("id_provincia"));
	            localidades.add(l);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return localidades;
	}

}
