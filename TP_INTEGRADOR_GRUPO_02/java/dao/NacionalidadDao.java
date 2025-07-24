package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidades.Nacionalidad;

public class NacionalidadDao {

    public ArrayList<Nacionalidad> obtenerNacionalidades() {
        ArrayList<Nacionalidad> nacionalidades = new ArrayList<>();
        String sql = "SELECT id_nacionalidad, nombre_nacionalidad FROM nacionalidad";
        try (Connection cn = Conexion.getConexion(); 
             PreparedStatement ps = cn.prepareStatement(sql); 
             ResultSet rs = ps.executeQuery()) { 

            while (rs.next()) { 
                Nacionalidad n = new Nacionalidad();
                n.setId(rs.getInt("id_nacionalidad"));
                n.setNacionalidad(rs.getString("nombre_nacionalidad"));
                nacionalidades.add(n); 
            }
        } 
        catch (SQLException e) { e.printStackTrace(); }
        
        return nacionalidades; 
    }
}