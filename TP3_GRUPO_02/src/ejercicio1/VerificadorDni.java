package ejercicio1;

public class VerificadorDni {

	public static void verificarDniInvalido(String dni) throws DniInvalidoException {
		
		if (!dni.matches("\\d+")) {
	        throw new DniInvalidoException();
	    }
	}
}
