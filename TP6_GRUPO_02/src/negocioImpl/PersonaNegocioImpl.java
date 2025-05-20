package negocioImpl;

import Dao.PersonaDao;
import DaoImpl.PersonaDaoImpl;
import negocio.PersonaNegocio;
import entidad.Persona;
import java.util.List;

public class PersonaNegocioImpl implements PersonaNegocio {

    private PersonaDao personaDao = new PersonaDaoImpl();

    public List<Persona> obtenerTodas() {
        return personaDao.obtenerTodas();
    }
}