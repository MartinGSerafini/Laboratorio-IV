package ejercicio1;

public class Main {

	public static void main(String[] args) {
		
		
		String DNI1 = "43920AAA";
		
		try {
			VerificadorDni.verificarDniInvalido(DNI1);
			System.out.println("DNI valido.");
		
		} catch(DniInvalidoException e){
			System.out.println("Error: " + e.getMessage());
		}
	}

}
