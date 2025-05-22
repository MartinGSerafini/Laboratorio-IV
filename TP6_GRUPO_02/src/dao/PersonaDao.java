package dao;

import java.util.List;
import entidad.Persona;

public interface PersonaDao {
	
    List<Persona> obtenerTodas();
    int agregarPersona(String query);
    
    public boolean actualizarPersona(Persona persona);
    public Persona validarDni(String dni);
    
    public boolean eliminarPersona(Persona persona);
}