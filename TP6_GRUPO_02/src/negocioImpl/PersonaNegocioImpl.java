package negocioImpl;

import dao.PersonaDao;
import daoImpl.PersonaDaoImpl;
import negocio.PersonaNegocio;
import entidad.Persona;
import java.util.List;

public class PersonaNegocioImpl implements PersonaNegocio {

    private PersonaDaoImpl personaDao = new PersonaDaoImpl();

    public List<Persona> obtenerTodas() {
        return personaDao.obtenerTodas();
    }
}