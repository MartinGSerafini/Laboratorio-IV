package entidades;

public class Seguros {
	
	private int idSeguro;
	private String descripcion;
	private TipoSeguro tipoSeguro;
	private double costoContratacion;
	private double costoAsegurado;
	
	public Seguros() {
		
	}
	
	public Seguros(int idSeguro, String descripcion, int idTipo, double costoContratacion, double costoAsegurado, TipoSeguro tipoSeguro) {
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

	public double getCostoContratacion() {
		return costoContratacion;
	}

	public double getCostoAsegurado() {
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

	public void setCostoContratacion(double costoContratacion) {
		this.costoContratacion = costoContratacion;
	}

	public void setCostoAsegurado(double costoAsegurado) {
		this.costoAsegurado = costoAsegurado;
	}

	@Override
	public String toString() {
		return "Seguro [idSeguro=" + idSeguro + ", descripcion=" + descripcion + ", idTipo=" + tipoSeguro
				+ ", costoContratacion=" + costoContratacion + ", costoAsegurado=" + costoAsegurado + "]";
	}
	
}
