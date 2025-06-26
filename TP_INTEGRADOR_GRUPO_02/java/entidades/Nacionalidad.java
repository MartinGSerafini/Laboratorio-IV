package entidades;

public class Nacionalidad {
	private String nacionalidad;
	private int id;
	
	public Nacionalidad(){}

	public Nacionalidad(int id, String nacionalidad) {
		this.nacionalidad = nacionalidad;
		this.id = id;
	}

	public String getNacionalidad() {return nacionalidad;}
	public void setNacionalidad(String nacionalidad) {this.nacionalidad = nacionalidad;}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
