package entidades;

public class Seguros {
	
	private int idSeguro;
	private String descripcion;
	private TipoSeguro tipoSeguro;
	private int costoContratacion;
	private int costoAsegurado;
	
	public Seguros() {
		
	}
	
	public Seguros(int idSeguro, String descripcion, int idTipo, int costoContratacion, int costoAsegurado, TipoSeguro tipoSeguro) {
		this.idSeguro = idSeguro;
		this.descripcion = descripcion;
		this.tipoSeguro = tipoSeguro;
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

	public TipoSeguro getTipoSeguro() {
        return tipoSeguro;
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

	public void setTipoSeguro(TipoSeguro tipoSeguro) {
        this.tipoSeguro = tipoSeguro;
    }

	public void setCostoContratacion(int costoContratacion) {
		this.costoContratacion = costoContratacion;
	}

	public void setCostoAsegurado(int costoAsegurado) {
		this.costoAsegurado = costoAsegurado;
	}

	@Override
	public String toString() {
		return "Seguro [idSeguro=" + idSeguro + ", descripcion=" + descripcion + ", idTipo=" + tipoSeguro
				+ ", costoContratacion=" + costoContratacion + ", costoAsegurado=" + costoAsegurado + "]";
	}
	
}
