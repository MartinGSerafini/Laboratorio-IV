package ejercicio1;

public class DniInvalidoException extends Exception{
	
	public DniInvalidoException() {
		
	}

	@Override
	public String getMessage() {
		return "El DNI no es valido";
	}
	
	
}
