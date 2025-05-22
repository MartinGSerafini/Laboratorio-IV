package daoImpl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dao.Conexion;
import dao.PersonaDao;
import entidad.Persona;

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

	@Override
	public boolean actualizarPersona(Persona persona) {
		String sql = "UPDATE Personas SET Nombre = ?, Apellido = ? WHERE Dni = ?";

	    try (Connection cn = Conexion.getConexion();
	         PreparedStatement ps = cn.prepareStatement(sql)) {

	        ps.setString(1, persona.getNombre());
	        ps.setString(2, persona.getApellido());
	        ps.setString(3, persona.getDni());

	        int filas = ps.executeUpdate();
	        return filas > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	public Persona validarDni(String query) {
        Persona persona = new Persona();
        try (Connection cn = Conexion.getConexion()) {
            Statement st = (Statement) cn.createStatement();
             ResultSet rs = st.executeQuery(query);
                rs.next(); // LEE LAS FILAS
                persona.setNombre(rs.getString("Nombre"));
                persona.setApellido(rs.getString("Apellido"));
                persona.setDni(rs.getString("Dni"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return persona;
    }



}