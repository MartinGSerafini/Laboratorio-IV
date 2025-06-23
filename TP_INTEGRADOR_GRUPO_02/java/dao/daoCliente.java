package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
	
}
