package controladorAdmin;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoTipoCuenta;
import entidades.Cuenta;
import entidades.TipoCuenta;
import excepciones.ClienteNoEncontradoExc;
import negocio.NegocioCliente;
import negocio.NegocioCuenta;

@WebServlet("/AgregarCuentaServlet")
public class AgregarCuentaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DaoTipoCuenta daoTipoCuenta = new DaoTipoCuenta();
		ArrayList<TipoCuenta> listaTipos = daoTipoCuenta.obtenerListaTipos();
		request.setAttribute("listaTipos", listaTipos);
		
		RequestDispatcher rd = request.getRequestDispatcher("/Formularios/ModoBanco/ABMLCuentas/AgregarCuentas.jsp");
		rd.forward(request, response); 
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
	        String accion = request.getParameter("accion");

	        DaoTipoCuenta daoTipoCuenta = new DaoTipoCuenta();
	        ArrayList<TipoCuenta> listaTipos = daoTipoCuenta.obtenerListaTipos();
	        request.setAttribute("listaTipos", listaTipos);

	        if ("agregarCuenta".equals(accion)) {
	            // Obtener parámetros
	            String dniCliente = request.getParameter("dniCliente");
	            String tipoCuentaStr = request.getParameter("tipo_Cuenta");
	            String nroCuenta = request.getParameter("nroCuenta");
	            String cuil = request.getParameter("cuil");

	            request.setAttribute("dniCliente", dniCliente);
	            request.setAttribute("tipo_Cuenta", tipoCuentaStr);
	            request.setAttribute("nroCuenta", nroCuenta);
	            request.setAttribute("cuil", cuil);

	            NegocioCliente negCliente = new NegocioCliente();
	            int idCliente = negCliente.obtenerIdCliente(dniCliente);
	            if (idCliente == 0) {
	                request.setAttribute("mensaje", "error");
	                request.setAttribute("errorDetalle", "No se encontró cliente con ese DNI");
	                forwardForm(request, response);
	                return;
	            }

	            int idTipo = Integer.parseInt(tipoCuentaStr);
	            String fechaStr = new java.sql.Date(System.currentTimeMillis()).toString(); // formato SQL date
	            BigDecimal saldoFicticio = BigDecimal.ZERO;

	            NegocioCuenta negCuenta = new NegocioCuenta();
	            String errores = negCuenta.validarYVerificarCuenta(" ", idCliente, fechaStr, nroCuenta, cuil, saldoFicticio, idTipo);

	            if (errores != null) {
	                request.setAttribute("mensaje", "error");
	                request.setAttribute("errorDetalle", errores);
	                forwardForm(request, response);
	                return;
	            }

	            if (negCuenta.contarCuentasXClientes(idCliente) >= 3) {
	                request.setAttribute("mensaje", "error");
	                request.setAttribute("errorDetalle", "El cliente ya tiene 3 cuentas asignadas.");
	                forwardForm(request, response);
	                return;
	            }

	            Cuenta cuenta = new Cuenta(" ", idCliente, idTipo, java.sql.Date.valueOf(fechaStr), nroCuenta, cuil, saldoFicticio);
	            boolean exito = negCuenta.alta(cuenta);

	            request.setAttribute("mensaje", exito ? "ok" : "error");
	            if (!exito) {
	                request.setAttribute("errorDetalle", "No se pudo agregar la cuenta.");
	            }
	        }

	    } catch (ClienteNoEncontradoExc | NumberFormatException e) {
	        request.setAttribute("mensaje", "error");
	        request.setAttribute("errorDetalle", "Error en entrada: " + e.getMessage());
	    } catch (Exception e) {
	        e.printStackTrace();
	        request.setAttribute("mensaje", "error");
	        request.setAttribute("errorDetalle", "Error inesperado: " + e.getMessage());
	    }

	    forwardForm(request, response);
	}


	
	private void forwardForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/Formularios/ModoBanco/ABMLCuentas/AgregarCuentas.jsp");
		rd.forward(request, response);
	}
}
