package dao;

import entidades.TipoSeguro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TiposeguroDao {

    public List<TipoSeguro> obtenerTodos() {
        List<TipoSeguro> lista = new ArrayList<TipoSeguro>();

        String sql = "SELECT idTipo, descripcion FROM tipoSeguros";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                TipoSeguro tipo = new TipoSeguro(rs.getInt("idTipo"), rs.getString("descripcion"));
                lista.add(tipo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}