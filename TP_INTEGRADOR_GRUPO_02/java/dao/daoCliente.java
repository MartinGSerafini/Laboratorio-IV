package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

import entidades.Cliente;

public class daoCliente {
	
	public boolean altaCliente(Cliente cliente) {
		
		 String sql = "INSERT INTO Cliente (id_cliente, dni_cliente, cuil_cliente, nombre_cliente, apellido_cliente, sexo_cliente, nacionalidad_cliente, fechaNac_cliente, direccion_cliente, localidad_cliente, provincia_cliente, correo_cliente, telefono_cliente, usuario_cliente, contraseña_cliente)" +
                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		 
		 try (Connection conn = Conexion.getConexion();
		         PreparedStatement ps = conn.prepareStatement(sql)) {

		        ps.setString(1, cliente.getIdCliente());
		        ps.setInt(2, cliente.getDniCliente());
		        ps.setString(3, cliente.getCuilCliente());
		        ps.setString(4, cliente.getNombreCliente());
		        ps.setString(5, cliente.getApellidoCliente());
		        ps.setString(6, cliente.getSexoCliente());
		        ps.setString(7, cliente.getNacionalidadCliente());
		        ps.setDate(8, cliente.getFechaNacCliente()); // java.sql.Date
		        ps.setString(9, cliente.getDireccionCliente());
		        ps.setString(10, cliente.getLocalidadCliente());
		        ps.setString(11, cliente.getProvinciaCliente());
		        ps.setString(12, cliente.getCorreoCliente());
		        ps.setString(13, cliente.getTelefonoCliente());
		        ps.setString(14, cliente.getUsuarioCliente());
		        ps.setString(15, cliente.getContrasenaCliente());
		        
		        int filasInsertadas = ps.executeUpdate();
		        return filasInsertadas > 0;

		    } catch (SQLException e) {
		        e.printStackTrace();
		        return false;
		    }
		
	} 
	
	public boolean verificarCliente(String usuario, String contrasena) {
	    String sql = "SELECT * FROM cliente WHERE usuario_cliente = ? AND contraseña_cliente = ? AND estado_cliente = 1";

	    try (Connection conn = Conexion.getConexion();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        
	        ps.setString(1, usuario);
	        ps.setString(2, contrasena);

	        try (ResultSet rs = ps.executeQuery()) {
	            return rs.next();
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public ArrayList<Cliente> obtenerClientes(){
		String sql = "SELECT c.*, p.nombre_provincia, l.nombre_localidad, n.nombre_nacionalidad " +
                "FROM cliente c " +
                "LEFT JOIN provincia p ON c.provincia_cliente = p.id_provincia " +
                "LEFT JOIN localidad l ON c.localidad_cliente = l.id_localidad " +
                "LEFT JOIN nacionalidad n ON c.nacionalidad_cliente = n.id_nacionalidad " +
                "WHERE c.estado_cliente = 1";
		ArrayList<Cliente> lista = new ArrayList<Cliente>();
		try (Connection conn = Conexion.getConexion();
				Statement st = conn.createStatement();) {
			    ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()) {
				int estado = rs.getInt("estado_cliente");
				if(estado == 1) {	
				
				Cliente cliente = new Cliente();
				cliente.setIdCliente(rs.getString("id_cliente"));
				cliente.setDniCliente(rs.getInt("dni_cliente"));
				cliente.setCuilCliente(rs.getString("cuil_cliente"));
				cliente.setNombreCliente(rs.getString("nombre_cliente"));
				cliente.setApellidoCliente(rs.getString("apellido_cliente"));
				cliente.setSexoCliente(rs.getString("sexo_cliente"));
                cliente.setNacionalidadCliente(rs.getString("nombre_nacionalidad"));
				cliente.setFechaNacCliente(rs.getDate("fechaNac_cliente", null));
				cliente.setDireccionCliente(rs.getString("direccion_cliente"));
				cliente.setProvinciaCliente(rs.getString("nombre_provincia"));
				cliente.setLocalidadCliente(rs.getString("nombre_localidad"));
				cliente.setCorreoCliente(rs.getString("correo_cliente"));
				cliente.setTelefonoCliente(rs.getString("telefono_cliente"));
				cliente.setUsuarioCliente(rs.getString("usuario_cliente"));
				cliente.setContrasenaaCliente(rs.getString("contraseña_cliente"));
				
				lista.add(cliente);
				}
			}
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {}
		return lista;
	}
	
	public ArrayList<String> obtenerColumnas() {
        ArrayList<String> columnas = new ArrayList<>();
        try (Connection conn = Conexion.getConexion()) {
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet rs = metaData.getColumns(null, null, "cliente", null);
            while (rs.next()) {
                columnas.add(rs.getString("COLUMN_NAME"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columnas;
    }

	
	public List<String> obtenerColumnasTablaClientes() {
	        List<String> columnas = new ArrayList<>();
	        try (Connection con = Conexion.getConexion()) {
	            DatabaseMetaData metaData = con.getMetaData();
	            ResultSet rs = metaData.getColumns(null, null, "clientes", null); // Asegurate que el nombre coincida con tu tabla
	            while (rs.next()) {
	                columnas.add(rs.getString("COLUMN_NAME"));
	            }
	            rs.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return columnas;
	}
	
	 public ArrayList<Cliente> obtenerClientesPorFiltro(String columna, String valor) {
	        ArrayList<Cliente> listaFiltrada = new ArrayList<>();
	        String sql = "SELECT c.*, p.nombre_provincia, l.nombre_localidad, n.nombre_nacionalidad " +
	                     "FROM cliente c " +
	                     "INNER JOIN provincia p ON c.provincia_cliente = p.id_provincia " +
	                     "INNER JOIN localidad l ON c.localidad_cliente = l.id_localidad " +
	                     "INNER JOIN nacionalidad n ON c.nacionalidad_cliente = n.id_nacionalidad " +
	                     "WHERE c." + columna + " LIKE ? AND c.estado_cliente = 1";

	        try (Connection conn = Conexion.getConexion();
	             PreparedStatement ps = conn.prepareStatement(sql)) {

	            ps.setString(1, "%" + valor + "%");
	            try (ResultSet rs = ps.executeQuery()) {
	                while (rs.next()) {
	                    Cliente cliente = new Cliente();
	                    cliente.setIdCliente(rs.getString("id_cliente"));
	                    cliente.setDniCliente(rs.getInt("dni_cliente"));
	                    cliente.setCuilCliente(rs.getString("cuil_cliente"));
	                    cliente.setNombreCliente(rs.getString("nombre_cliente"));
	                    cliente.setApellidoCliente(rs.getString("apellido_cliente"));
	                    cliente.setSexoCliente(rs.getString("sexo_cliente"));
	                    cliente.setNacionalidadCliente(rs.getString("nombre_nacionalidad"));
	                    cliente.setFechaNacCliente(rs.getDate("fechaNac_cliente"));
	                    cliente.setDireccionCliente(rs.getString("direccion_cliente"));
	                    cliente.setProvinciaCliente(rs.getString("nombre_provincia"));
	                    cliente.setLocalidadCliente(rs.getString("nombre_localidad"));
	                    cliente.setCorreoCliente(rs.getString("correo_cliente"));
	                    cliente.setTelefonoCliente(rs.getString("telefono_cliente"));
	                    cliente.setUsuarioCliente(rs.getString("usuario_cliente"));
	                    cliente.setContrasenaaCliente(rs.getString("contraseña_cliente"));

	                    listaFiltrada.add(cliente);
	                }
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return listaFiltrada;
	    }

	    public ArrayList<String> obtenerColumnasClientes() {
	        ArrayList<String> columnas = new ArrayList<>();
	        String sql = "SHOW COLUMNS FROM cliente";
	        try (Connection conn = Conexion.getConexion();
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery(sql)) {
	            while (rs.next()) {
	                String nombreColumna = rs.getString("Field");
	                if (!nombreColumna.equalsIgnoreCase("estado_cliente")) {
	                    columnas.add(nombreColumna);
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return columnas;
	    }
 
	    public boolean bajaLogicaCliente(String idCliente) {
	        String sql = "UPDATE cliente SET estado_cliente = 0 WHERE id_cliente = ?";
	        try (Connection conn = Conexion.getConexion();
	             PreparedStatement ps = conn.prepareStatement(sql)) {
	            
	            ps.setString(1, idCliente);
	            int filasActualizadas = ps.executeUpdate();
	            return filasActualizadas > 0;
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	    } 
}
