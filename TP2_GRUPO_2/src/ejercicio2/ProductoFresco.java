package ejercicio2;

public class ProductoFresco extends Producto{
	private String fechaEnvasado;
	private String paisOrigen; 
	
	// Constructores
	//vacio
	public ProductoFresco() {
		super();
		this.fechaEnvasado = "Sin Ingresar";
		this.paisOrigen = "Sin Ingresar";
	}
	//con parametros
	public ProductoFresco(String nombre, String fechaEnvasado, String paisOrigen) {
		super(nombre);
		this.fechaEnvasado = fechaEnvasado;
		this.paisOrigen = paisOrigen;
	}
	
	//getters and setters
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
