package Dominio;

public class Empleado {
	
	private static int cont = 1000;  
	
	///Atributos
	
	private int id;
	private String nombre;
	private int edad;  
	
	///Constructores
	
	public Empleado(){
<<<<<<< HEAD:TP N1 Grupo 2/src/Dominio/Empleado.java
		this.id = cont++;
		nombre  = "Sin Nombre";
		edad = 99;
	}
	public Empleado(String nombre, int edad){
		this.id = cont++;
		this.nombre  = nombre;
		this.edad = edad;
=======
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
>>>>>>> 18589bbfb234be0b0a6da89d58a51d4f022da919:TP N1 Grupo 2.v3/src/Dominio/Empleado.java
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
<<<<<<< HEAD:TP N1 Grupo 2/src/Dominio/Empleado.java
		this.edad = edad;
=======
		Edad = edad;
>>>>>>> 18589bbfb234be0b0a6da89d58a51d4f022da919:TP N1 Grupo 2.v3/src/Dominio/Empleado.java
	}
	
	///Metodos
	
	public static int devuelveProximoID() {
<<<<<<< HEAD:TP N1 Grupo 2/src/Dominio/Empleado.java
        return cont;
    }
	
	///metodo toString()
	@Override
	public String toString() {
		 return "Empleado: " + nombre + ", Edad: " + edad + ", Legajo: " + id;
    }
=======
		return cont+1;
	}
	
>>>>>>> 18589bbfb234be0b0a6da89d58a51d4f022da919:TP N1 Grupo 2.v3/src/Dominio/Empleado.java
	
}
