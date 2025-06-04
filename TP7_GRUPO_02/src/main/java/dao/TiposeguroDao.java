package dao;

import entidades.TipoSeguro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

public class TiposeguroDao {

    public List<TipoSeguro> obtenerTodos() {
    	try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }
    	
        List<TipoSeguro> lista = new ArrayList<TipoSeguro>();

        String sql = "SELECT idTipo, descripcion FROM tipoSeguros";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                TipoSeguro tipo = new TipoSeguro(rs.getInt("idTipo"), rs.getString("descripcion"));
                lista.add(tipo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
    
    public TipoSeguro obtenerTipoSeguro(int id) {
    	TipoSeguro tipo = new TipoSeguro();
    	String sql = "SELECT idTipo, descripcion FROM tipoSeguros where idTipo="+id;
    	
    	try (Connection con = Conexion.getConexion();
                Statement st = (Statement) con.createStatement();
                ResultSet rs = st.executeQuery(sql)) {
    		
    			rs.next();
                tipo.setIdTipo(rs.getInt("idTipo"));
                tipo.setDescripcion(rs.getString("descripcion"));
           } catch (Exception e) {
               e.printStackTrace();
           }
    	return tipo;
    }
}