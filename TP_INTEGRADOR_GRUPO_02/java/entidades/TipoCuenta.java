package entidades;

public class TipoCuenta {

    private int idtipoCuenta;
    private String descripcionTipoCuenta;

    public TipoCuenta() {
    }

    public TipoCuenta(int idtipoCuenta, String descripcionTipoCuenta) {
        this.idtipoCuenta = idtipoCuenta;
        this.descripcionTipoCuenta = descripcionTipoCuenta;
    }

    public int getIdtipoCuenta() {
        return idtipoCuenta;
    }

    public void setIdtipoCuenta(int idtipoCuenta) {
        this.idtipoCuenta = idtipoCuenta;
    }

    public String getDescripcionTipoCuenta() {
        return descripcionTipoCuenta;
    }

    public void setDescripcionTipoCuenta(String descripcionTipoCuenta) {
        this.descripcionTipoCuenta = descripcionTipoCuenta;
    }
}

