package Dominio;

public class Empleado {
	
	private static int cont = 1000;  
	
	///Atributos
	
	private int Id;
	private String Nombre;
	private int Edad;  
	
	///Constructores
	
	public Empleado(){
		cont++; 
		Id = cont;
		Nombre  = "Sin Nombre";
		Edad = 99;
	}
	public Empleado(String Nombre, int Edad){
		cont++; 
		this.Id = cont;
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
	
	///Metodos
	
	public static int devuelveProximoID() {
		return cont+1;
	}
	
	
}
