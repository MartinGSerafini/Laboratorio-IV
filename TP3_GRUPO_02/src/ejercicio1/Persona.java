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
        @Override
        public int compare(Persona p1, Persona p2) {
            // 1) Normalizar y comparar nombres
            String n1 = normalizar(p1.getNombre());
            String n2 = normalizar(p2.getNombre());
            int cmpNombre = n1.compareTo(n2);
            if (cmpNombre != 0) return cmpNombre;

            // 2) Si el nombre coincide, normalizar y comparar apellidos
            String a1 = normalizar(p1.getApellido());
            String a2 = normalizar(p2.getApellido());
            return a1.compareTo(a2);
        }

        private String normalizar(String texto) {
            // trim, pasar a minúsculas y quitar tildes/diacríticos
            return java.text.Normalizer
                    .normalize(texto.trim().toLowerCase(), java.text.Normalizer.Form.NFD)
                    .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        }
    };

}