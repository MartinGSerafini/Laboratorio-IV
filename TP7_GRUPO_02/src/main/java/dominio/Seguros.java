package dominio;

public class Seguros {
	
	private int idSeguro;
	private String descripcion;
	private int idTipo;
	private int costoContratacion;
	private int costoAsegurado;
	
	public Seguros() {
		
	}
	
	public Seguros(int idSeguro, String descripcion, int idTipo, int costoContratacion, int costoAsegurado) {
		this.idSeguro = idSeguro;
		this.descripcion = descripcion;
		this.idTipo = idTipo;
		this.costoContratacion = costoContratacion;
		this.costoAsegurado = costoAsegurado;
	}
	
	
	//Getters
	public int getIdSeguro() {
		return idSeguro;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public int getIdTipo() {
		return idTipo;
	}

	public int getCostoContratacion() {
		return costoContratacion;
	}

	public int getCostoAsegurado() {
		return costoAsegurado;
	}
	
	
	//Setters
	public void setIdSeguro(int idSeguro) {
		this.idSeguro = idSeguro;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setIdTipo(int idTipo) {
		this.idTipo = idTipo;
	}

	public void setCostoContratacion(int costoContratacion) {
		this.costoContratacion = costoContratacion;
	}

	public void setCostoAsegurado(int costoAsegurado) {
		this.costoAsegurado = costoAsegurado;
	}

	@Override
	public String toString() {
		return "Seguro [idSeguro=" + idSeguro + ", descripcion=" + descripcion + ", idTipo=" + idTipo
				+ ", costoContratacion=" + costoContratacion + ", costoAsegurado=" + costoAsegurado + "]";
	}
	
}
