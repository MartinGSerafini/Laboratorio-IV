package entidades;

public class TipoMovimiento {

    private int idTipoMovimiento;
    private String descripcionTipoMov;

    public TipoMovimiento() {
    }

    public TipoMovimiento(int idTipoMovimiento, String descripcionTipoMov) {
        this.idTipoMovimiento = idTipoMovimiento;
        this.descripcionTipoMov = descripcionTipoMov;
    }

    public int getIdTipoMovimiento() {
        return idTipoMovimiento;
    }

    public void setIdTipoMovimiento(int idTipoMovimiento) {
        this.idTipoMovimiento = idTipoMovimiento;
    }

    public String getDescripcionTipoMov() {
        return descripcionTipoMov;
    }

    public void setDescripcionTipoMov(String descripcionTipoMov) {
        this.descripcionTipoMov = descripcionTipoMov;
    }
}

