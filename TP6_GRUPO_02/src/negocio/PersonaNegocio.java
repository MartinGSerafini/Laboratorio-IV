package negocio;

import entidad.Persona;
import java.util.List;

import dao.PersonaDao;
import daoImpl.PersonaDaoImpl;

public interface PersonaNegocio {
    List<Persona> obtenerTodas();
    boolean agregarPersona(String nombre, String apellido, String dni);
    public boolean actualizarPersona(Persona persona);
}    