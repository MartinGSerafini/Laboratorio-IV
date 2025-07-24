package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
    
    public void insertarCuotas(int idPrestamo, int cantidadCuotas, BigDecimal montoCuota, Date fechaInicio) {
        for (int i = 1; i <= cantidadCuotas; i++) {
            Date fechaVencimiento = calcularFechaVencimiento(fechaInicio, i);
            Cuota cuota = new Cuota();
            cuota.setIdPrestamoCuota(idPrestamo);
            cuota.setNumeroCuota(i);
            cuota.setImporteCuota(montoCuota);
            cuota.setFechaVencCuota(fechaVencimiento);
            cuota.setEstadoCuota(1); 
            cuota.setFechaPagoCuota(null);

            registrarCuota(cuota);
        }
    }

    private Date calcularFechaVencimiento(Date fechaInicio, int mesesASumar) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaInicio);
        calendar.add(Calendar.MONTH, mesesASumar);
        return new Date(calendar.getTimeInMillis());
    }
    
    public ArrayList<Cuota> obtenerCuotasPorPrestamo(int idPrestamo) {
        String sql = "SELECT * FROM cuota WHERE idPrestamo_cuota = ? AND estado_cuota = 3";
        ArrayList<Cuota> lista = new ArrayList<>();
        try (Connection conn = Conexion.getConexion();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, idPrestamo); 
            ResultSet rs = pst.executeQuery();

            while(rs.next()) {
                Cuota cuota = new Cuota();
                cuota.setIdCuota(rs.getInt("id_cuota"));
                cuota.setIdPrestamoCuota(rs.getInt("idPrestamo_cuota"));
                cuota.setNumeroCuota(rs.getInt("numero_cuota"));
                cuota.setImporteCuota(rs.getBigDecimal("importe_cuota"));
                cuota.setFechaVencCuota(rs.getDate("fechaVenc_cuota"));
                cuota.setFechaPagoCuota(rs.getDate("fechaPago_cuota"));
                cuota.setEstadoCuota(rs.getInt("estado_cuota"));
                lista.add(cuota);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public ArrayList<Cuota> obtenerCuotasPendientesPorPrestamo(int idPrestamo) {
    	ArrayList<Cuota> cuotas = new ArrayList<>();
        String sql = "SELECT * FROM Cuotas WHERE idPrestamo = ? AND estado = 'pendiente' ORDER BY numeroCuota";
        try (Connection conn = Conexion.getConexion();
				Statement st = conn.createStatement();) {
			    ResultSet rs = st.executeQuery(sql);
			    
			while(rs.next()) {
                Cuota cuota = new Cuota();
                cuota.setIdCuota(rs.getInt("idCuota"));
                cuota.setIdPrestamoCuota(rs.getInt("idPrestamo"));
                cuota.setNumeroCuota(rs.getInt("numeroCuota"));
                cuota.setImporteCuota(rs.getBigDecimal("importeCuota"));
                cuota.setFechaVencCuota(rs.getDate("fechaVencimiento"));
                cuota.setEstadoCuota(rs.getInt("estado_cuota"));
                cuotas.add(cuota);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cuotas;
    }

    public Cuota obtenerCuotaPorId(int idCuota) {
        Cuota cuota = null;
        String sql = "SELECT * FROM cuota WHERE id_cuota = ?";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idCuota);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                cuota = new Cuota();
                cuota.setIdCuota(rs.getInt("id_cuota"));
                cuota.setIdPrestamoCuota(rs.getInt("idPrestamo_cuota"));
                cuota.setNumeroCuota(rs.getInt("numero_cuota"));
                cuota.setImporteCuota(rs.getBigDecimal("importe_cuota"));
                cuota.setFechaVencCuota(rs.getDate("fechaVenc_cuota"));
                cuota.setEstadoCuota(rs.getInt("estado_cuota"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cuota;
    }

    public boolean marcarCuotaComoPagada(int idCuota) {
        String sql = "UPDATE cuota SET estado_cuota = 1, fechaPago_cuota = CURDATE() WHERE id_cuota = ?";
        boolean resultado = false;

        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idCuota);
            int filasAfectadas = ps.executeUpdate();
            resultado = filasAfectadas > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultado;
    }


    public boolean pagarPrestamoCompleto(int idPrestamo) {
        String sql = "UPDATE Cuotas SET estado = 'pagada' WHERE idPrestamo = ?";
        try (Connection conn = Conexion.getConexion();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idPrestamo);
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
