package ejercicio2;

public class Producto {
	private static int cont = 0;  
	private final int codigo;
    private String nombre;
    
    // Constructores
    
    //	Vacio
    public Producto(){
        this.codigo = cont++;  // Asigna el Codigo y luego incrementa el contador
        this.nombre = "Sin Nombre";
    }
    
    //	Con Parametros
    public Producto(String nombre){
        this.codigo = cont++;  // Asigna el Codigo y luego incrementa el contador
        this.nombre = nombre;
    }

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCodigo() {
		return codigo;
	}
    
}
