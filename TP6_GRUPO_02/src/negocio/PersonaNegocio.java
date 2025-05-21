package negocio;

import dao.PersonaDao;
import daoImpl.PersonaDaoImpl;
import entidad.Persona;
import java.util.List;

public interface PersonaNegocio {
    List<Persona> obtenerTodas();
}