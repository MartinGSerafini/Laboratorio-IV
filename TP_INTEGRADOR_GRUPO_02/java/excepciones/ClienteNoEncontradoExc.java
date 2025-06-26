package excepciones;

public class ClienteNoEncontradoExc extends RuntimeException{
	public ClienteNoEncontradoExc(String mensaje) {
        super(mensaje);
    }
}
