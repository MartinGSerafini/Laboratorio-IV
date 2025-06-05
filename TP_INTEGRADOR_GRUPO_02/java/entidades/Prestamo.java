package entidades;

public class Prestamo {
    private String idPrestamo;
    private String idClientePres;
    private java.sql.Date fechaSolicitudPres;
    private java.math.BigDecimal importeSolicitadoPres;
    private java.math.BigDecimal importeTotalPres;
    private int plazoMesesPres;
    private java.math.BigDecimal montoCuotaPres;
    private int estadoPres;
    private String idCuentaDepositoPres;

    // Constructor por defecto
    public Prestamo() {}

    // Getters y Setters
    public String getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(String idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public String getIdClientePres() {
        return idClientePres;
    }

    public void setIdClientePres(String idClientePres) {
        this.idClientePres = idClientePres;
    }

    public java.sql.Date getFechaSolicitudPres() {
        return fechaSolicitudPres;
    }

    public void setFechaSolicitudPres(java.sql.Date fechaSolicitudPres) {
        this.fechaSolicitudPres = fechaSolicitudPres;
    }

    public java.math.BigDecimal getImporteSolicitadoPres() {
        return importeSolicitadoPres;
    }

    public void setImporteSolicitadoPres(java.math.BigDecimal importeSolicitadoPres) {
        this.importeSolicitadoPres = importeSolicitadoPres;
    }

    public java.math.BigDecimal getImporteTotalPres() {
        return importeTotalPres;
    }

    public void setImporteTotalPres(java.math.BigDecimal importeTotalPres) {
        this.importeTotalPres = importeTotalPres;
    }

    public int getPlazoMesesPres() {
        return plazoMesesPres;
    }

    public void setPlazoMesesPres(int plazoMesesPres) {
        this.plazoMesesPres = plazoMesesPres;
    }

    public java.math.BigDecimal getMontoCuotaPres() {
        return montoCuotaPres;
    }

    public void setMontoCuotaPres(java.math.BigDecimal montoCuotaPres) {
        this.montoCuotaPres = montoCuotaPres;
    }

    public int getEstadoPres() {
        return estadoPres;
    }

    public void setEstadoPres(int estadoPres) {
        this.estadoPres = estadoPres;
    }

    public String getIdCuentaDepositoPres() {
        return idCuentaDepositoPres;
    }

    public void setIdCuentaDepositoPres(String idCuentaDepositoPres) {
        this.idCuentaDepositoPres = idCuentaDepositoPres;
    }
}

