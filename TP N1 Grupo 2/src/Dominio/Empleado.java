package Dominio;

public class Empleado {
	
	private static int cont = 1000;  
	
	///Atributos
	
	private int id;
	private String nombre;
	private int edad;  
	
	///Constructores
	
	public Empleado(){
		this.id = cont++;
		nombre  = "Sin Nombre";
		edad = 99;
	}
	public Empleado(String nombre, int edad){
		this.id = cont++;
		this.nombre  = nombre;
		this.edad = edad;
	}
	
	
	///Getters y Setters
	
	public int getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	///Metodos
	
	public static int devuelveProximoID() {
        return cont;
    }
	
	///metodo toString()
	@Override
	public String toString() {
		 return "Empleado: " + nombre + ", Edad: " + edad + ", Legajo: " + id;
    }
	
}
