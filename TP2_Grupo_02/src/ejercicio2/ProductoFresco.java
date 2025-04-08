package ejercicio2;

public class ProductoFresco extends Producto{
	private String fechaEnvasado;
	private String paisOrigen; 
	
	// Constructores
	//Vacio
	public ProductoFresco() {
		super();
		this.fechaEnvasado = "Sin Ingresar";
		this.paisOrigen = "Sin Ingresar";
	}
	//Con parametros
	public ProductoFresco(String nombre, int numeroLote, String fechaCaducidad, String fechaEnvasado, String paisOrigen) {
		super(nombre, numeroLote, fechaCaducidad);
		this.fechaEnvasado = fechaEnvasado;
		this.paisOrigen = paisOrigen;
	}
	
	//Getters and setters
	public String getFechaEnvasado() {
		return fechaEnvasado;
	}
	public void setFechaEnvasado(String fechaEnvasado) {
		this.fechaEnvasado = fechaEnvasado;
	}
	public String getPaisOrigen() {
		return paisOrigen;
	}
	public void setPaisOrigen(String paisOrigen) {
		this.paisOrigen = paisOrigen;
	}
	
	
}
