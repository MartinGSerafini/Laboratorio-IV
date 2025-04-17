package ejercicio1;

import java.util.Comparator;
import java.util.Objects;

public class Persona {
	
	//Atributos
    private String nombre;
    private String apellido;
    private String dni;

    //Constructor
    public Persona(String nombre, String apellido, String dni){
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }

    //Getters
    public String getNombre(){
        return nombre;
    }

    public String getApellido(){
        return apellido;
    }

    public String getDni(){
        return dni;
    }
    
    // Mostrar la Cadena de Caracteres
    public String toString() {
        return nombre + " " + apellido + ": " + dni;
    }
    
    //Metodo para comparar los objetos de la clase persona por el Dni
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return dni.equals(persona.dni);
    }
    
    public int hashCode() {
        return Objects.hash(dni);
    }
    
    public static Comparator<Persona> ordenarPorNombreApellido = new Comparator<Persona>() {
        public int compare(Persona p1, Persona p2) {
            int apellidoComp = p1.getApellido().compareTo(p2.getApellido());
            if (apellidoComp != 0) {
                return apellidoComp;
            }
            return p1.getNombre().compareTo(p2.getNombre());
        }
    };
}