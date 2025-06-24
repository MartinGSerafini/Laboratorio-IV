package entidades;

public class Provincia { 
	private String provicia; 
	
	public Provincia (){}

	public Provincia(int id, String provicia) {
		super();
		this.provicia = provicia;
	}

	public String getProvicia() {return provicia;}
	public void setProvicia(String provicia) {this.provicia = provicia;}
}
