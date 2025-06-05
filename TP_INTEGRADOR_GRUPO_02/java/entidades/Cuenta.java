package entidades;

import java.math.BigDecimal;
import java.util.Date;

public class Cuenta {
	
	private String idCuenta;
    private String idClienteCuenta;
    private int idTipoCuentaCuenta;
    private Date fechaCreacionCuenta;
    private String numeroCuenta;
    private String cbuCuenta;
    private BigDecimal saldoCuenta;

    // Constructor vacío
    public Cuenta() {}

    // Constructor completo
    public Cuenta(String idCuenta, String idClienteCuenta, int idTipoCuentaCuenta, Date fechaCreacionCuenta,
                  String numeroCuenta, String cbuCuenta, BigDecimal saldoCuenta) {
        this.idCuenta = idCuenta;
        this.idClienteCuenta = idClienteCuenta;
        this.idTipoCuentaCuenta = idTipoCuentaCuenta;
        this.fechaCreacionCuenta = fechaCreacionCuenta;
        this.numeroCuenta = numeroCuenta;
        this.cbuCuenta = cbuCuenta;
        this.saldoCuenta = saldoCuenta;
    }

    // Getters y Setters

    public String getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(String idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getIdClienteCuenta() {
        return idClienteCuenta;
    }

    public void setIdClienteCuenta(String idClienteCuenta) {
        this.idClienteCuenta = idClienteCuenta;
    }

    public int getIdTipoCuentaCuenta() {
        return idTipoCuentaCuenta;
    }

    public void setIdTipoCuentaCuenta(int idTipoCuentaCuenta) {
        this.idTipoCuentaCuenta = idTipoCuentaCuenta;
    }

    public Date getFechaCreacionCuenta() {
        return fechaCreacionCuenta;
    }

    public void setFechaCreacionCuenta(Date fechaCreacionCuenta) {
        this.fechaCreacionCuenta = fechaCreacionCuenta;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getCbuCuenta() {
        return cbuCuenta;
    }

    public void setCbuCuenta(String cbuCuenta) {
        this.cbuCuenta = cbuCuenta;
    }

    public BigDecimal getSaldoCuenta() {
        return saldoCuenta;
    }

    public void setSaldoCuenta(BigDecimal saldoCuenta) {
        this.saldoCuenta = saldoCuenta;
    }
	
}
