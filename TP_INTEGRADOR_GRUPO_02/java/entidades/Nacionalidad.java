package entidades;

public class Nacionalidad {
	private String nacionalidad; 
	
	public Nacionalidad(){}

	public Nacionalidad(int id, String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getNacionalidad() {return nacionalidad;}
	public void setNacionalidad(String nacionalidad) {this.nacionalidad = nacionalidad;}
}
