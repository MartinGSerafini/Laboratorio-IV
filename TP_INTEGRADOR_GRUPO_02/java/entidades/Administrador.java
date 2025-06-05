package entidades;

public class Administrador {
	
	private String idAdmin;
    private String usuarioAdmin;
    private String contraseñaAdmin;

    // Constructor vacío
    public Administrador() {}

    // Constructor completo
    public Administrador(String idAdmin, String usuarioAdmin, String contraseñaAdmin) {
        this.idAdmin = idAdmin;
        this.usuarioAdmin = usuarioAdmin;
        this.contraseñaAdmin = contraseñaAdmin;
    }

    // Getters y Setters

    public String getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(String idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getUsuarioAdmin() {
        return usuarioAdmin;
    }

    public void setUsuarioAdmin(String usuarioAdmin) {
        this.usuarioAdmin = usuarioAdmin;
    }

    public String getContraseñaAdmin() {
        return contraseñaAdmin;
    }

    public void setContraseñaAdmin(String contraseñaAdmin) {
        this.contraseñaAdmin = contraseñaAdmin;
    }
	
}
