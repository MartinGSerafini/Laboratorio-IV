package entidades;

import java.math.BigDecimal;
import java.util.Date;

public class Cuota {

    private int idCuota;
    private int idPrestamoCuota;
    private int numeroCuota;
    private BigDecimal importeCuota;
    private Date fechaVencCuota;
    private Date fechaPagoCuota;
    private int estadoCuota;

    public Cuota() {}

    public Cuota(int idCuota, int idPrestamoCuota, int numeroCuota, BigDecimal importeCuota,
                 Date fechaVencCuota, Date fechaPagoCuota, int estadoCuota) {
        this.idCuota = idCuota;
        this.idPrestamoCuota = idPrestamoCuota;
        this.numeroCuota = numeroCuota;
        this.importeCuota = importeCuota;
        this.fechaVencCuota = fechaVencCuota;
        this.fechaPagoCuota = fechaPagoCuota;
        this.estadoCuota = estadoCuota;
    }


    public int getIdCuota() {
        return idCuota;
    }

    public void setIdCuota(int idCuota) {
        this.idCuota = idCuota;
    }

    public int getIdPrestamoCuota() {
        return idPrestamoCuota;
    }

    public void setIdPrestamoCuota(int idPrestamoCuota) {
        this.idPrestamoCuota = idPrestamoCuota;
    }


    public int getNumeroCuota() {
        return numeroCuota;
    }

    public void setNumeroCuota(int numeroCuota) {
        this.numeroCuota = numeroCuota;
    }

    public BigDecimal getImporteCuota() {
        return importeCuota;
    }

    public void setImporteCuota(BigDecimal importeCuota) {
        this.importeCuota = importeCuota;
    }

    public Date getFechaVencCuota() {
        return fechaVencCuota;
    }

    public void setFechaVencCuota(Date fechaVencCuota) {
        this.fechaVencCuota = fechaVencCuota;
    }

    public Date getFechaPagoCuota() {
        return fechaPagoCuota;
    }

    public void setFechaPagoCuota(Date fechaPagoCuota) {
        this.fechaPagoCuota = fechaPagoCuota;
    }

    public int getEstadoCuota() {
        return estadoCuota;
    }

    public void setEstadoCuota(int estadoCuota) {
        this.estadoCuota = estadoCuota;
    }
}

