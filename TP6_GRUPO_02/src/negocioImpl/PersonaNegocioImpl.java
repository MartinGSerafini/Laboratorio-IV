package negocioImpl;

import negocio.PersonaNegocio;
import entidad.Persona;
import java.util.List;

import dao.PersonaDao;
import daoImpl.PersonaDaoImpl;

public class PersonaNegocioImpl implements PersonaNegocio {

    private PersonaDaoImpl personaDao = new PersonaDaoImpl();

    public List<Persona> obtenerTodas() {
        return personaDao.obtenerTodas();
    }
    
    @Override
	public boolean agregarPersona(String nombre, String apellido, String dni) {
		String query = "INSERT INTO personas(Dni, Nombre, Apellido) VALUES ('"+dni+"','"+nombre+"','"+apellido+"')";
		if(personaDao.agregarPersona(query) == 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean actualizarPersona(Persona persona) {
		return personaDao.actualizarPersona(persona);
	}
}