package negocioImpl;

import negocio.PersonaNegocio;
import entidad.Persona;
import java.util.List;

import javax.swing.JOptionPane;

import dao.PersonaDao;
import daoImpl.PersonaDaoImpl;

public class PersonaNegocioImpl implements PersonaNegocio {

    private PersonaDaoImpl personaDao = new PersonaDaoImpl();

    public List<Persona> obtenerTodas() {
        return personaDao.obtenerTodas();
    }
    
    @Override
	public boolean agregarPersona(String nombre, String apellido, String dni) {
    	if(validarDni(dni)) {
            String query = "INSERT INTO personas(Dni, Nombre, Apellido) VALUES ('"+dni+"','"+nombre+"','"+apellido+"')";
            if(personaDao.agregarPersona(query) == 1) {
                return true;
            } 
        }
    	JOptionPane.showMessageDialog(null, "El DNI ya existe");
		return false;
	}

	@Override
	public boolean actualizarPersona(Persona persona) {
		return personaDao.actualizarPersona(persona);
	}

	@Override
	public boolean validarDni(String dni) {
		String query = "SELECT * FROM personas WHERE Dni = '"+dni+"'";
		Persona persona = personaDao.validarDni(dni); 
		if (persona.getDni() != dni) {
			
			return true;
		} 
		return false;
	}
	
	public boolean eliminarPersona(Persona persona) {
		return personaDao.eliminarPersona(persona);
	}
	
}