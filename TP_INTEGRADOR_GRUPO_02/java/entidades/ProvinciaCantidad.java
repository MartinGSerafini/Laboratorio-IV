package entidades;

public class ProvinciaCantidad {
    private String nombreProvincia;
    private int cantidad;

    public ProvinciaCantidad(String nombreProvincia, int cantidad) {
        this.nombreProvincia = nombreProvincia;
        this.cantidad = cantidad;
    }

    public String getNombreProvincia() { return nombreProvincia; }
    public int getCantidad() { return cantidad; }
}

