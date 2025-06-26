package entidades;

public class Localidad {
	private int id;
	private int id_pcia; 
	private String localidad; 
	
	public Localidad(){}

	public Localidad(int id_pcia, String localidad) {
		super();
		this.id_pcia = id_pcia;
		this.localidad = localidad;
	}

	public int getId_pcia() {return id_pcia;}
	public void setId_pcia(int id_pcia) {this.id_pcia = id_pcia;}

	public String getLocalidad() {return localidad;}
	public void setLocalidad(String localidad) {this.localidad = localidad;}
	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
