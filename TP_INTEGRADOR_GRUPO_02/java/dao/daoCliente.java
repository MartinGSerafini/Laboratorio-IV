package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	    String sql = "SELECT * FROM cliente WHERE usuario_cliente = ? AND contrase�a_cliente = ? AND estado_cliente = 1";

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
		String sql = "Select * From cliente";
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
				cliente.setNacionalidadCliente(rs.getString("nacionalidad_cliente"));
				cliente.setFechaNacCliente(rs.getDate("fechaNac_cliente", null));
				cliente.setDireccionCliente(rs.getString("direccion_cliente"));
				cliente.setProvinciaCliente(rs.getString("provincia_cliente"));
				cliente.setLocalidadCliente(rs.getString("localidad_cliente"));
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
	
}
