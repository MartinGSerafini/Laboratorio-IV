package entidades;

public class Cliente {
	
	private String idCliente;
    private int dniCliente;
    private String cuilCliente;
    private String nombreCliente;
    private String apellidoCliente;
    private String sexoCliente;
    private String nacionalidadCliente;
    private java.sql.Date fechaNacCliente;
    private String direccionCliente;
    private String localidadCliente;
    private String provinciaCliente;
    private String correoCliente;
    private String telefonoCliente;
    private String usuarioCliente;
    private String contrasenaCliente;

    // Constructor vacío
    public Cliente() {}

    // Constructor completo
    public Cliente(String idCliente, int dniCliente, String cuilCliente, String nombreCliente, String apellidoCliente,
                   String sexoCliente, String nacionalidadCliente, java.sql.Date fechaNacCliente,
                   String direccionCliente, String localidadCliente, String provinciaCliente,
                   String correoCliente, String telefonoCliente, String usuarioCliente, String contrasenaCliente) {
        this.idCliente = idCliente;
        this.dniCliente = dniCliente;
        this.cuilCliente = cuilCliente;
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.sexoCliente = sexoCliente;
        this.nacionalidadCliente = nacionalidadCliente;
        this.fechaNacCliente = fechaNacCliente;
        this.direccionCliente = direccionCliente;
        this.localidadCliente = localidadCliente;
        this.provinciaCliente = provinciaCliente;
        this.correoCliente = correoCliente;
        this.telefonoCliente = telefonoCliente;
        this.usuarioCliente = usuarioCliente;
        this.contrasenaCliente = contrasenaCliente;
    }

    // Getters y Setters

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public int getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(int dniCliente) {
        this.dniCliente = dniCliente;
    }

    public String getCuilCliente() {
        return cuilCliente;
    }

    public void setCuilCliente(String cuilCliente) {
        this.cuilCliente = cuilCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getApellidoCliente() {
        return apellidoCliente;
    }

    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    public String getSexoCliente() {
        return sexoCliente;
    }

    public void setSexoCliente(String sexoCliente) {
        this.sexoCliente = sexoCliente;
    }

    public String getNacionalidadCliente() {
        return nacionalidadCliente;
    }

    public void setNacionalidadCliente(String nacionalidadCliente) {
        this.nacionalidadCliente = nacionalidadCliente;
    }

    public java.sql.Date getFechaNacCliente() {
        return fechaNacCliente;
    }

    public void setFechaNacCliente(java.sql.Date fechaNacCliente) {
        this.fechaNacCliente = fechaNacCliente;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public String getLocalidadCliente() {
        return localidadCliente;
    }

    public void setLocalidadCliente(String localidadCliente) {
        this.localidadCliente = localidadCliente;
    }

    public String getProvinciaCliente() {
        return provinciaCliente;
    }

    public void setProvinciaCliente(String provinciaCliente) {
        this.provinciaCliente = provinciaCliente;
    }

    public String getCorreoCliente() {
        return correoCliente;
    }

    public void setCorreoCliente(String correoCliente) {
        this.correoCliente = correoCliente;
    }

    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    public String getUsuarioCliente() {
        return usuarioCliente;
    }

    public void setUsuarioCliente(String usuarioCliente) {
        this.usuarioCliente = usuarioCliente;
    }

    public String getContrasenaCliente() {
        return contrasenaCliente;
    }

    public void setContrasenaaCliente(String contrasenaCliente) {
        this.contrasenaCliente = contrasenaCliente;
    }
	
	
}
