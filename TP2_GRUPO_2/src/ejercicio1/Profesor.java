package ejercicio1;

public class Profesor extends Empleado {
     
	//Atributos
	private String cargo;
    private int antiguedadDocente;
	
    //Constructores
    
    //	Vacio
    public Profesor() {
		super();
		this.cargo = "Sin cargo";
		this.antiguedadDocente = 0;
	}
    //	Con parámetros
	public Profesor(String nombre, int edad, String cargo, int antiguedadDocente) {
		super(nombre, edad);
		this.cargo = cargo;
		this.antiguedadDocente = antiguedadDocente;
	}
	
	//Getters y setters
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public int getAntiguedadDocente() {
		return antiguedadDocente;
	}
	public void setAntiguedadDocente(int antiguedadDocente) {
		this.antiguedadDocente = antiguedadDocente;
	}
	
	@Override
	public String toString() {
		return super.toString()+" Cargo: " + cargo + ", Antiguedad docente: " + antiguedadDocente;
	}
	
	
	
	
	
	
}
