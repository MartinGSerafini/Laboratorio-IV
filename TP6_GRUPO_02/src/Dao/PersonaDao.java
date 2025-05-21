package dao;

import java.util.List;
import entidad.Persona;

public interface PersonaDao {
	
    List<Persona> obtenerTodas();
}