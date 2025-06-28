package entidades;

public class GeneroClientes {
	private String genero;
    private int cantidad;

    public GeneroClientes(String genero, int cantidad) {
        this.genero= genero;
        this.cantidad = cantidad;
    }

    public String getGenero() {
        return genero;
    }

    public int getCantidad() {
        return cantidad;
    }
}
