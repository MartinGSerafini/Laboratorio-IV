package ejercicio1;

import java.util.Objects;

public class Empleado {

    private static int cont = 1000;  

    // Atributos
    private final int id;  // El ID es constante y no se debe cambiar
    private String nombre;
    private int edad;  

    // Constructores
    public Empleado(){
        this.id = cont++;  // Asigna el ID y luego incrementa el contador
        this.nombre = "Sin Nombre";
        this.edad = 99;
    }

    public Empleado(String nombre, int edad){
        this.id = cont++;  // Asigna el ID y luego incrementa el contador
        this.nombre = nombre;
        this.edad = edad;
    }

    // Getters y Setters
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

    // M�todos
    public static int devuelveProximoID() {
        return cont;  // Devuelve el pr�ximo ID disponible
    }

    // M�todo toString()
    @Override
    public String toString() {
        return "Empleado: " + nombre + ", Edad: " + edad + ", Legajo: " + id;
    }

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empleado other = (Empleado) obj;
		return edad == other.edad && id == other.id && Objects.equals(nombre, other.nombre);
	}
    
}