package entidades;

public class Movimiento {
    private String idMov;
    private String idCuentaMov;
    private java.sql.Date fechaMov;
    private String detalleMov;
    private java.math.BigDecimal importeMov;
    private int idTipoMovMov;
    private String idCuentaDestinoMov;

    // Constructor por defecto
    public Movimiento() {}

    // Getters y Setters
    public String getIdMov() {
        return idMov;
    }

    public void setIdMov(String idMov) {
        this.idMov = idMov;
    }

    public String getIdCuentaMov() {
        return idCuentaMov;
    }

    public void setIdCuentaMov(String idCuentaMov) {
        this.idCuentaMov = idCuentaMov;
    }

    public java.sql.Date getFechaMov() {
        return fechaMov;
    }

    public void setFechaMov(java.sql.Date fechaMov) {
        this.fechaMov = fechaMov;
    }

    public String getDetalleMov() {
        return detalleMov;
    }

    public void setDetalleMov(String detalleMov) {
        this.detalleMov = detalleMov;
    }

    public java.math.BigDecimal getImporteMov() {
        return importeMov;
    }

    public void setImporteMov(java.math.BigDecimal importeMov) {
        this.importeMov = importeMov;
    }

    public int getIdTipoMovMov() {
        return idTipoMovMov;
    }

    public void setIdTipoMovMov(int idTipoMovMov) {
        this.idTipoMovMov = idTipoMovMov;
    }

    public String getIdCuentaDestinoMov() {
        return idCuentaDestinoMov;
    }

    public void setIdCuentaDestinoMov(String idCuentaDestinoMov) {
        this.idCuentaDestinoMov = idCuentaDestinoMov;
    }
}
