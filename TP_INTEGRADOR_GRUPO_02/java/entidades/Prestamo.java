package entidades;

public class Prestamo {
    private String idPrestamo;
    private int idClientePres;
    private java.sql.Date fechaSolicitudPres;
    private java.math.BigDecimal importeSolicitadoPres;
    private java.math.BigDecimal importeTotalPres;
    private int plazoMesesPres;
    private java.math.BigDecimal montoCuotaPres;
    private EstadoPrestamo estadoPres;
    private String idCuentaDepositoPres;

    public Prestamo() {}


    public String getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(String idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public int getIdClientePres() {
        return idClientePres;
    }

    public void setIdClientePres(int idClientePres) {
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

    public EstadoPrestamo getEstadoPres() {
        return estadoPres;
    }

    public void setEstadoPres(EstadoPrestamo estadoPres) {
        this.estadoPres = estadoPres;
    }

    public String getIdCuentaDepositoPres() {
        return idCuentaDepositoPres;
    }

    public void setIdCuentaDepositoPres(String idCuentaDepositoPres) {
        this.idCuentaDepositoPres = idCuentaDepositoPres;
    }
}

