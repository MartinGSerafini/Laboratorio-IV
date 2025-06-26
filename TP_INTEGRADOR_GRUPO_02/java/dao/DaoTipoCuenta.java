package dao;

import java.sql.Connection;
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
	
	
}
