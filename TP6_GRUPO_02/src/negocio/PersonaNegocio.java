package negocio;

import Dao.PersonaDao;
import DaoImpl.PersonaDaoImpl;
import entidad.Persona;
import java.util.List;

public interface PersonaNegocio {
    List<Persona> obtenerTodas();
    boolean agregarPersona(String nombre, String apellido, String dni);
}