package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entidades.Provincia;

public class ProvinciaDao {
 
	//----obtener todas las provincias de la bd---
	public List<Provincia> obtenerProvincias() {
     List<Provincia> provincias = new ArrayList<>();
     String sql = "SELECT nombre_provincia FROM provincia"; 
     
     try (Connection cn = Conexion.getConexion();
    		 PreparedStatement ps = cn.prepareStatement(sql);
    		 ResultSet rs = ps.executeQuery()){
    	 
    	 while(rs.next()) {
    		 Provincia p = new Provincia(); 
    		 p.setProvicia(rs.getString("nombre_provincia"));
    		 provincias.add(p); 
    	 }
     } 
     catch (SQLException e) { e.printStackTrace(); }
     
     return provincias;
 }
}

