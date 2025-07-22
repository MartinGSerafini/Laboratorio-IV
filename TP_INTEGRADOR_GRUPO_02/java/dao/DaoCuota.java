package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import entidades.Cuota;

public class DaoCuota {

    public boolean registrarCuota(Cuota cuota) {
        String sql = "INSERT INTO cuota (idPrestamo_cuota, numero_cuota, importe_cuota, fechaVenc_cuota, estado_cuota) " +
                     "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, cuota.getIdPrestamoCuota());
            ps.setInt(2, cuota.getNumeroCuota());
            ps.setBigDecimal(3, cuota.getImporteCuota());
            ps.setDate(4, new java.sql.Date(cuota.getFechaVencCuota().getTime()));
            ps.setInt(5, cuota.getEstadoCuota());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
