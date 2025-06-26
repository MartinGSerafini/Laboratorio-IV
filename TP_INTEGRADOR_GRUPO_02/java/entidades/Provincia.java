package entidades;

public class Provincia { 
	private String provicia; 
	private int id;
	
	public Provincia (){}

	public Provincia(int id, String provicia) {
		
		this.provicia = provicia;
		this.id = id;
	}

	public String getProvicia() {return provicia;}
	public void setProvicia(String provicia) {this.provicia = provicia;}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
