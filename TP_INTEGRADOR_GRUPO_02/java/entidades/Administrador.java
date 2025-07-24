package entidades;

public class Administrador {
	
	private String idAdmin;
    private String usuarioAdmin;
    private String contrasenaAdmin;

    public Administrador() {}

    public Administrador(String idAdmin, String usuarioAdmin, String contrasenaAdmin) {
        this.idAdmin = idAdmin;
        this.usuarioAdmin = usuarioAdmin;
        this.contrasenaAdmin = contrasenaAdmin;
    }
    

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

    public String getcontrasenaAdmin() {
        return contrasenaAdmin;
    }

    public void setcontrasenaAdmin(String contrasenaAdmin) {
        this.contrasenaAdmin = contrasenaAdmin;
    }
	
}
