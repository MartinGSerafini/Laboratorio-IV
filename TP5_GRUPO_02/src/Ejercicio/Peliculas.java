package Ejercicio;

public class Peliculas {
    private int id;
    private String nombre;
    private Generos genero;

    // Constructor, getters y setters
    public Peliculas(int id, String nombre, Generos genero) {
        this.id = id;
        this.nombre = nombre;
        this.genero = genero;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public Generos getGenero() { return genero; }

    @Override
    public String toString() {
        return id + " - " + nombre + " (" + genero + ")";
    }
}