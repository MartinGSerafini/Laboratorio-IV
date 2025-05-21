package DaoImpl;

import java.sql.Connection;
import Dao.PersonaDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import entidad.Persona;
import Dao.Conexion;

public class PersonaDaoImpl implements PersonaDao {

    public List<Persona> obtenerTodas() {
        List<Persona> lista = new ArrayList<>();

        String sql = "SELECT Dni, Nombre, Apellido FROM Personas";

        try (Connection cn = Conexion.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Persona p = new Persona();
                p.setDni(rs.getString("Dni"));
                p.setNombre(rs.getString("Nombre"));
                p.setApellido(rs.getString("Apellido"));
                lista.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
    
    @Override
	public int agregarPersona(String query) {
		int filas = 0;
		try (Connection cn = Conexion.getConexion()) {
			Statement st = (Statement) cn.createStatement();
			filas = st.executeUpdate(query);
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }
		return filas;
	}
}