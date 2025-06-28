package entidades;

public class ClienteCuentas {
    private String nombre;
    private String apellido;
    private int cantidadCuentas;

    public ClienteCuentas() {}

    public ClienteCuentas(String nombre, String apellido, int cantidadCuentas) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cantidadCuentas = cantidadCuentas;
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public int getCantidadCuentas() { return cantidadCuentas; }
    public void setCantidadCuentas(int cantidadCuentas) { this.cantidadCuentas = cantidadCuentas; }
}
