package dao;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

import entidades.Prestamo;
import entidades.EstadoPrestamo;

public class daoPrestamo {

    public ArrayList<EstadoPrestamo> obtenerEstados() {
        String sql = "SELECT * from estadoprestamos";
        ArrayList<EstadoPrestamo> lista = new ArrayList<>();
        try (Connection conn = Conexion.getConexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                EstadoPrestamo estadoPrestamo = new EstadoPrestamo(
                        rs.getInt("id_estadoPrestamos"),
                        rs.getString("desc_estadoPrestamo"));
                lista.add(estadoPrestamo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public EstadoPrestamo obtenerEstadoPrestamo(int id) {
        EstadoPrestamo estadoPrestamo = new EstadoPrestamo();
        String sql = "SELECT * FROM estadoprestamos WHERE id_estadoPrestamos=" + id;
        try (Connection con = Conexion.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            if (rs.next()) {
                estadoPrestamo.setId(rs.getInt("id_estadoPrestamos"));
                estadoPrestamo.setDescripcion(rs.getString("desc_estadoPrestamo"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return estadoPrestamo;
    }

    private void cargar(ArrayList<Prestamo> lista, ResultSet rs) throws SQLException {
        while (rs.next()) {
            Prestamo prestamo = new Prestamo();
            prestamo.setIdPrestamo(rs.getString("id_prestamo"));
            prestamo.setIdClientePres(rs.getInt("idCliente_pres"));
            prestamo.setFechaSolicitudPres(rs.getDate("fechaSolicitud_pres"));
            prestamo.setImporteSolicitadoPres(rs.getBigDecimal("importeSolicitado_pres"));
            prestamo.setImporteTotalPres(rs.getBigDecimal("importeTotal_pres"));
            prestamo.setPlazoMesesPres(rs.getInt("plazoMeses_pres"));
            prestamo.setMontoCuotaPres(rs.getBigDecimal("montoCuota_pres"));
            prestamo.setEstadoPres(obtenerEstadoPrestamo(rs.getInt("estado_pres")));
            prestamo.setIdCuentaDepositoPres(rs.getString("idCuentaDeposito_pres"));
            lista.add(prestamo);
        }
    }

    public ArrayList<Prestamo> obtenerTodos() {
        String sql = "SELECT * FROM prestamo WHERE estado_prestamo = TRUE " +
                     "ORDER BY (estado_pres = 3) DESC, fechaSolicitud_pres DESC;";
        ArrayList<Prestamo> lista = new ArrayList<>();
        try (Connection conn = Conexion.getConexion();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            cargar(lista, rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public int modificarEstadoPrestamo(String estado, String id) {
        String sql = "UPDATE prestamo SET estado_pres =" + estado + " WHERE id_prestamo='" + id + "';";
        int filas = 0;
        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            filas = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filas;
    }

    public ArrayList<Prestamo> filtrar(String sql) {
        ArrayList<Prestamo> lista = new ArrayList<>();
        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            cargar(lista, rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public ArrayList<String> obtenerColumnasPrestamo() {
        ArrayList<String> columnas = new ArrayList<>();
        String sql = "SHOW COLUMNS FROM prestamo";
        try (Connection conn = Conexion.getConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String nombreColumna = rs.getString("Field");
                if (!nombreColumna.equalsIgnoreCase("id_prestamo") &&
                    !nombreColumna.equalsIgnoreCase("estado_prestamo") &&
                    !nombreColumna.equalsIgnoreCase("fechaSolicitud_pres") &&
                    !nombreColumna.equalsIgnoreCase("importeTotal_pres") &&
                    !nombreColumna.equalsIgnoreCase("montoCuota_pres")) {
                    columnas.add(nombreColumna);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return columnas;
    }

    public int registrarPrestamo(Prestamo prestamo) {
        String sql = "INSERT INTO PRESTAMO (" +
                     "idCliente_pres, fechaSolicitud_pres, importeSolicitado_pres, " +
                     "importeTotal_pres, plazoMeses_pres, montoCuota_pres, estado_pres, idCuentaDeposito_pres) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, prestamo.getIdClientePres());
            ps.setDate(2, prestamo.getFechaSolicitudPres());
            ps.setBigDecimal(3, prestamo.getImporteSolicitadoPres());
            ps.setBigDecimal(4, prestamo.getImporteTotalPres());
            ps.setInt(5, prestamo.getPlazoMesesPres());
            ps.setBigDecimal(6, prestamo.getMontoCuotaPres());
            ps.setInt(7, prestamo.getEstadoPres().getId());
            ps.setString(8, prestamo.getIdCuentaDepositoPres());

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public int insertarPrestamoYObtenerId(int idCliente, int idCuenta, BigDecimal importeSolicitado,
                                          BigDecimal importeTotal, int plazoMeses, BigDecimal montoCuota, Date fechaSolicitud) {
        int idGenerado = 0;
        String sql = "INSERT INTO prestamo (idCliente_pres, idCuentaDeposito_pres, importeSolicitado_pres, " +
                     "importeTotal_pres, plazoMeses_pres, montoCuota_pres, fechaSolicitud_pres, estado_pres, estado_prestamo) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, 3, TRUE)";

        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, idCliente);
            ps.setInt(2, idCuenta);
            ps.setBigDecimal(3, importeSolicitado);
            ps.setBigDecimal(4, importeTotal);
            ps.setInt(5, plazoMeses);
            ps.setBigDecimal(6, montoCuota);
            ps.setDate(7, new java.sql.Date(fechaSolicitud.getTime()));

            int filas = ps.executeUpdate();
            if (filas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        idGenerado = rs.getInt(1);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idGenerado;
    }

    public ArrayList<Prestamo> obtenerPrestamosConNumeroCuentaPorCliente(int idCliente) {
        String sql = "SELECT p.*, c.Numero_Cuenta FROM prestamo p " +
                     "JOIN cuenta c ON p.idCuentaDeposito_pres = c.id_cuenta " +
                     "WHERE p.idCliente_pres = ? AND p.estado_pres = 1 AND p.estado_prestamo = 1 " +
                     "ORDER BY p.fechaSolicitud_pres DESC";
        ArrayList<Prestamo> lista = new ArrayList<>();
        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idCliente);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Prestamo p = new Prestamo();
                    p.setIdPrestamo(rs.getString("id_prestamo"));
                    p.setIdClientePres(rs.getInt("idCliente_pres"));
                    p.setFechaSolicitudPres(rs.getDate("fechaSolicitud_pres"));
                    p.setImporteSolicitadoPres(rs.getBigDecimal("importeSolicitado_pres"));
                    p.setImporteTotalPres(rs.getBigDecimal("importeTotal_pres"));
                    p.setPlazoMesesPres(rs.getInt("plazoMeses_pres"));
                    p.setMontoCuotaPres(rs.getBigDecimal("montoCuota_pres"));
                    p.setIdCuentaDepositoPres(rs.getString("idCuentaDeposito_pres"));
                    p.setNumeroCuentaDeposito(rs.getString("Numero_Cuenta"));
                    lista.add(p);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }


    public boolean actualizarEstadoPrestamo(int idPrestamo, boolean estado) {
        String sql = "UPDATE prestamo SET estado_prestamo = ? WHERE id_prestamo = ?";
        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setBoolean(1, estado);
            ps.setInt(2, idPrestamo);
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }


    public BigDecimal obtenerImporteTotalPrestamo(int idPrestamo) {
        BigDecimal importeTotal = BigDecimal.ZERO;
        String sql = "SELECT importe_total_pres FROM prestamos WHERE id_prestamo = ?";
        
        try (Connection conn = Conexion.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
             ps.setInt(1, idPrestamo);
             ResultSet rs = ps.executeQuery();
             
             if (rs.next()) {
                 importeTotal = rs.getBigDecimal("importe_total_pres");
             }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return importeTotal;
    }

}

