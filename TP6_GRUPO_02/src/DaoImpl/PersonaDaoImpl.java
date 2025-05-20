package DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Dao.PersonaDao;
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
}