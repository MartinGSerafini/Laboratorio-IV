package ejercicio1;

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

    // Métodos
    public static int devuelveProximoID() {
        return cont;  // Devuelve el próximo ID disponible
    }

    // Método toString()
    @Override
    public String toString() {
        return "Empleado: " + nombre + ", Edad: " + edad + ", Legajo: " + id;
    }
}