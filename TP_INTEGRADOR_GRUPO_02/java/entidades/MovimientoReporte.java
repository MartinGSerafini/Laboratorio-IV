package entidades;

import java.math.BigDecimal;
import java.util.Date;

public class MovimientoReporte {
    private int idMov;
    private int idCuenta;
    private Date fechaMov;
    private String detalleMov;
    private BigDecimal importeMov;
    private int idTipoMov;
    private Integer idCuentaDestino;

    public MovimientoReporte() {
    }

    public int getIdMov() {
        return idMov;
    }

    public void setIdMov(int idMov) {
        this.idMov = idMov;
    }

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public Date getFechaMov() {
        return fechaMov;
    }

    public void setFechaMov(Date fechaMov) {
        this.fechaMov = fechaMov;
    }

    public String getDetalleMov() {
        return detalleMov;
    }

    public void setDetalleMov(String detalleMov) {
        this.detalleMov = detalleMov;
    }

    public BigDecimal getImporteMov() {
        return importeMov;
    }

    public void setImporteMov(BigDecimal importeMov) {
        this.importeMov = importeMov;
    }

    public int getIdTipoMov() {
        return idTipoMov;
    }

    public void setIdTipoMov(int idTipoMov) {
        this.idTipoMov = idTipoMov;
    }

    public Integer getIdCuentaDestino() {
        return idCuentaDestino;
    }

    public void setIdCuentaDestino(Integer idCuentaDestino) {
        this.idCuentaDestino = idCuentaDestino;
    }
}
