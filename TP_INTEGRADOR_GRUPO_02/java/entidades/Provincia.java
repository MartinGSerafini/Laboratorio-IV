package entidades;

public class Provincia {
    private String provincia;
    private int id;

    public Provincia() {}

    public Provincia(int id, String provincia) {
        this.provincia = provincia;
        this.id = id;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
