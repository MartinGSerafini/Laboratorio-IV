package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entidades.TipoCuenta;

public class DaoTipoCuenta {
	
	public ArrayList<TipoCuenta>obtenerListaTipos(){
		
		String sql = "SELECT * FROM tipocuenta";
		ArrayList<TipoCuenta>lista = new ArrayList<TipoCuenta>(); 
		
		try (Connection conn = Conexion.getConexion();
				
				Statement st = conn.createStatement();){
			  	ResultSet rs = st.executeQuery(sql);

			  	while(rs.next()) {
			  		
			  		TipoCuenta tipoCuenta = new TipoCuenta();
			  		tipoCuenta.setIdtipoCuenta(rs.getInt("idTipoCuenta"));
			  		tipoCuenta.setDescripcionTipoCuenta(rs.getString("descripcion_tipoCuenta"));
			  		
			  		lista.add(tipoCuenta);
			  	}
			  	conn.close();
			  	
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		
		return lista;
	}
	
	public String obtenerTipoXid(int id) {
		
		String sql = "SELECT descripcion_tipoCuenta FROM tipocuenta WHERE idtipoCuenta = ?";
		String desc = null;
		
		try (Connection conn = Conexion.getConexion();
				
				PreparedStatement ps = conn.prepareStatement(sql)) {
        		ps.setInt(1,id);
        		
        		try(ResultSet rs = ps.executeQuery()){
        			
        			if (rs.next()) {
    					
        				desc = rs.getString("descripcion_tipoCuenta");
        		   }
        		}
        		conn.close();
        		
        	 }catch(Exception e) {
				e.printStackTrace();
			}finally {}
			return desc;
	}
	
}
