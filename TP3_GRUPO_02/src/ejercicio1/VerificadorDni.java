package ejercicio1;

public class VerificadorDni {

	public static void verificarDniInvalido(String dni) throws DniInvalidoException {
		
		for(int i=0; i<dni.length(); i++) {
			char c = dni.charAt(i);
			if(Character.isLetter(c)) {
				// EL DNI CONTIENE UNA LETRA
			}
			else {
				// EL DNI SOLO CONTIENE NUMEROS
			}
		}
		
	}
}
