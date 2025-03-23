package Dominio;

public class Empleado {
	
	///Atributos
	
	private int Id;
	private String Nombre;
	private int Edad;  
	
	///Constructores
	
	public Empleado()
	{
		Id = 0;
		Nombre  = "Sin Nombre";
		Edad = 0;
	}
	public Empleado(int Id, String Nombre, int Edad)
	{
		this.Id = Id;
		this.Nombre  = Nombre;
		this.Edad = Edad;
	}
	
	///Getters y Setters
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public int getEdad() {
		return Edad;
	}
	public void setEdad(int edad) {
		Edad = edad;
	} 
	
}
