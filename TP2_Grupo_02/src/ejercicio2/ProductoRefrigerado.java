package ejercicio2;

public class ProductoRefrigerado extends Producto{
	private int codigoOrganismo;
	
	// CONSTRUCTORES
	//Vacio
	public ProductoRefrigerado() {
		super();
		this.codigoOrganismo = -1;
	}
	
	//Con parametros
	public ProductoRefrigerado(String nombre, int numeroLote, String fechaCaducidad, int codigoOrganismo) {
		super(nombre, numeroLote, fechaCaducidad);
		this.codigoOrganismo = codigoOrganismo;
	}

	// GETTERS AND SETTERS
	public int getCodigoOrganismo() {
		return codigoOrganismo;
	}

	public void setCodigoOrganismo(int codigoOrganismo) {
		this.codigoOrganismo = codigoOrganismo;
	}

	// METODO TOSTRING
	@Override
	public String toString() {
		return  super.toString() + ", Codigo del organismo de supervision alimentaria: " + codigoOrganismo;
	}
}
