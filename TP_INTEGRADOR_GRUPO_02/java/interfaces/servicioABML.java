package interfaces;

import java.util.List;

public interface servicioABML<T> {
	
	boolean alta(T entidad);
	boolean baja(String id);
	boolean modificacion(String id, T entidad);
	boolean lectura(String id);
	List<T> listarTodos(); 
}
 