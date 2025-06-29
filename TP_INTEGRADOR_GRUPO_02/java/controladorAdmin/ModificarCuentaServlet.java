package controladorAdmin;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Cuenta;
import negocio.NegocioCuenta;


@WebServlet("/ModificarCuentaServlet")
public class ModificarCuentaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private NegocioCuenta negocioCuenta = new NegocioCuenta();
	
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
	        
	        BigDecimal saldoCuenta = null;

	        try {
	            String idCuenta = request.getParameter("id_cuenta");
	            int idClienteCuenta = Integer.parseInt(request.getParameter("idCliente_cuenta"));
	            int idTipoCuenta = Integer.parseInt(request.getParameter("idTipoCuenta"));
	            String fechaCreacionCuenta = request.getParameter("fechaCreacion_cuenta");
	            String numeroCuenta = request.getParameter("numero_cuenta");
	            String cbuCuenta = request.getParameter("cbu_cuenta");
	            String saldoStr = request.getParameter("saldo_cuenta");
	            if (saldoStr != null) {
	                saldoStr = saldoStr.replace(",", ".");
	                BigDecimal saldo = new BigDecimal(saldoStr);
	                saldoCuenta = saldo;
	            }
	            
	            String error = negocioCuenta.validarYVerificarCuenta(
	                idCuenta, idClienteCuenta, fechaCreacionCuenta, numeroCuenta, cbuCuenta,
	                saldoCuenta, idTipoCuenta
	            );

	            if (error != null) {
	                out.print("{\"success\":false,\"mensaje\":\"" + jsonEscape(error) + "\"}");
	                return;
	            }
	            
	            int cuentasObtenidas = negocioCuenta.cuentasXClientes(idClienteCuenta);
				if(cuentasObtenidas == 3) {
					out.print("{\"success\":false,\"mensaje\":\"" + jsonEscape("El cliente ya tiene 3 cuentas asignadas") + "\"}");
					return;
				}
	            
	            Cuenta cuenta = new Cuenta();
	            cuenta.setIdCuenta(idCuenta);
	            cuenta.setIdClienteCuenta(idClienteCuenta);
	            cuenta.setFechaCreacionCuenta((Date.valueOf(fechaCreacionCuenta)));
	            cuenta.setNumeroCuenta(numeroCuenta);
	            cuenta.setCbuCuenta(cbuCuenta);
	            cuenta.setSaldoCuenta(saldoCuenta);
	            cuenta.setIdTipoCuentaCuenta(idTipoCuenta);

	            boolean modificado = negocioCuenta.modificacion(idCuenta, cuenta);
	            if (modificado) {
	                out.print("{\"success\":true}");
	            } else {
	                out.print("{\"success\":false,\"mensaje\":\"" + jsonEscape("No se pudo modificar la cuenta.") + "\"}");
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
