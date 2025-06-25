package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdministradorDao {
	
	public boolean validarLogin(String usuario, String contrasena) {
        String sql = "SELECT * FROM administrador WHERE usuario_admin = ? AND contraseña_admin = ?";
        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario);
            ps.setString(2, contrasena);

            ResultSet rs = ps.executeQuery();
            return rs.next(); //

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
