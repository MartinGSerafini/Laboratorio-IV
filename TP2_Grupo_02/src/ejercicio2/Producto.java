package ejercicio2;

public class Producto { 
    
	private String nombre;
	private int numeroLote;
	private String fechaCaducidad;
    
    // Constructores

	//Vacio
	public Producto() {
		super();
		this.nombre = "Vacio";
		this.numeroLote = -1;
		this.fechaCaducidad = "Vacio";
	}
	
	//Con parametros
	public Producto(String nombre, int numeroLote, String fechaCaducidad) {
		super();
		this.nombre = nombre;
		this.numeroLote = numeroLote;
		this.fechaCaducidad = fechaCaducidad;
	}
	
	
	//Getter and Setters
	public int getNumeroLote() {
		return numeroLote;
	}
	
	public void setNumeroLote(int numeroLote) {
		this.numeroLote = numeroLote;
	}

	public String getFechaCaducidad() {
		return fechaCaducidad;
	}

	public void setFechaCaducidad(String fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	//ToString
	@Override
	public String toString() {
		return "Nombre del producto: " + nombre + ", Numero de lote: " + numeroLote + ", Fecha de caducidad: " + fechaCaducidad;
	}


	
	
    
}
