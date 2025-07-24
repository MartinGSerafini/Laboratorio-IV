package controladorAdmin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Cliente;
import negocio.NegocioCliente;

@WebServlet("/ModificarClienteServlet")
public class ModificarClienteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private NegocioCliente negocioCliente = new NegocioCliente();
    
    private String jsonEscape(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            String idCliente = request.getParameter("idCliente");
            String usuario = request.getParameter("usuario_Cliente");
            String contrasena = request.getParameter("contraseña_cliente");
            String dniStr = request.getParameter("dni_cliente");
            String cuil = request.getParameter("cuil_cliente");
            String nombre = request.getParameter("nombre_cliente");
            String apellido = request.getParameter("apellido_cliente");
            String sexo = request.getParameter("sexo_cliente");
            String fechaNacStr = request.getParameter("fechaNac_cliente");
            String direccion = request.getParameter("direccion_cliente");
            String correo = request.getParameter("correo_cliente");
            String telefono = request.getParameter("telefono_cliente");

            int nacionalidad = Integer.parseInt(request.getParameter("nacionalidad_cliente"));
            int localidad = Integer.parseInt(request.getParameter("localidad_cliente"));
            int provincia = Integer.parseInt(request.getParameter("provincia_cliente"));

            int dni;
            try {
                dni = Integer.parseInt(dniStr);
            } catch (NumberFormatException e) {
                out.print("{\"success\":false,\"mensaje\":\"" + jsonEscape("DNI inválido.") + "\"}");
                return;
            }

            String error = negocioCliente.validarYVerificarCliente(
                idCliente, usuario, contrasena, dni, cuil,
                nombre, apellido, sexo, nacionalidad,
                fechaNacStr, direccion, localidad, provincia,
                correo, telefono
            );

            if (error != null) {
                out.print("{\"success\":false,\"mensaje\":\"" + jsonEscape(error) + "\"}");
                return;
            }

            Cliente cliente = new Cliente();
            cliente.setIdCliente(idCliente);
            cliente.setUsuarioCliente(usuario);
            cliente.setContrasenaaCliente(contrasena);
            cliente.setDniCliente(dni);
            cliente.setCuilCliente(cuil);
            cliente.setNombreCliente(nombre);
            cliente.setApellidoCliente(apellido);
            cliente.setSexoCliente(sexo);
            cliente.setFechaNacCliente(Date.valueOf(fechaNacStr));
            cliente.setDireccionCliente(direccion);
            cliente.setCorreoCliente(correo);
            cliente.setTelefonoCliente(telefono);
            cliente.setNacionalidadCliente(nacionalidad);
            cliente.setLocalidadCliente(localidad);
            cliente.setProvinciaCliente(provincia);

            boolean modificado = negocioCliente.modificacion(idCliente, cliente);
            if (modificado) {
                out.print("{\"success\":true}");
            } else {
                out.print("{\"success\":false,\"mensaje\":\"" + jsonEscape("No se pudo modificar el cliente.") + "\"}");
            }

        } catch (Exception e) {
            e.printStackTrace();
            out.print("{\"success\":false,\"mensaje\":\"" + jsonEscape("Error interno del servidor.") + "\"}");
        } finally {
            out.flush();
            out.close();
        }
    }
}

