package ejercicio3;

public class PoliDeportivo extends Edificio implements InstalacionDeportiva {
	
	private String nombre;
	private int tipoInstalacion;

	public PoliDeportivo(double superficie, String nombre, int tipoInstalacion) {
		super(superficie);
		this.nombre = nombre;
		this.tipoInstalacion = tipoInstalacion;
	}
	
	@Override
	public int getTipoDeInstalacion() {
		return tipoInstalacion;
	}
	
	public String getNombre() {
		return nombre;
	}
}
