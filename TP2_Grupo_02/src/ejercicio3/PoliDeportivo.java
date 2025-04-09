package ejercicio3;

public class PoliDeportivo extends Edificio implements InstalacionDeportiva {
	
	private String nombre;
	private int tipoInstalacion;

	public PoliDeportivo(String nombre, double superficie, int tipoInstalacion) {
		super(superficie);
		this.nombre = nombre;
		this.tipoInstalacion = tipoInstalacion;
	}
	//sets y gets
	public int getTipoDeInstalacion() {
		return tipoInstalacion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setTipoInstalacion(int tipoInstalacion) {
		this.tipoInstalacion = tipoInstalacion;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	//to string
	@Override
	public String toString() {
		return "PoliDeportivo [nombre =" + nombre + ", tipo de instalacion =" + tipoInstalacion + ", superficie=" + superficie + "]";
	}
}
