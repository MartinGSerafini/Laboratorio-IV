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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accion = request.getParameter("accion");
		
	
		DaoTipoCuenta daoTipoCuenta = new DaoTipoCuenta();
		ArrayList<TipoCuenta> listaTipos = daoTipoCuenta.obtenerListaTipos();
		request.setAttribute("listaTipos", listaTipos);
		
		if ("agregarCuenta".equals(accion)) {
			try {
			
				String dniCliente = request.getParameter("dniCliente");
				String tipoCuentaStr = request.getParameter("tipo_Cuenta");
				String nroCuenta = request.getParameter("nroCuenta");
				String cuil = request.getParameter("cuil");
				
				// Pasar datos para rellenar en caso de error (mantener en form)
				request.setAttribute("dniCliente", dniCliente);
				request.setAttribute("tipo_Cuenta", tipoCuentaStr);
				request.setAttribute("nroCuenta", nroCuenta);
				request.setAttribute("cuil", cuil);
				
				// Validar y convertir
				NegocioCliente negCliente = new NegocioCliente();
				int idCliente = negCliente.obtenerIdCliente(dniCliente);
				if (idCliente == 0) {
					request.setAttribute("mensaje", "error");
					request.setAttribute("errorDetalle", "No se encontró cliente con ese DNI");
					forwardForm(request, response);
					return;
				}
				
				int idTipo = Integer.parseInt(tipoCuentaStr);
				java.util.Date fechaCreacion = new java.util.Date();
				BigDecimal saldoFicticio = new BigDecimal("0.00");
				
				
				Cuenta cuenta = new Cuenta(" ", idCliente, idTipo, fechaCreacion, nroCuenta, cuil, saldoFicticio);
				NegocioCuenta negCuenta = new NegocioCuenta();
				
				int cuentasObtenidas = negCuenta.contarCuentasXClientes(idCliente);
				if(cuentasObtenidas == 3) {
					request.setAttribute("mensaje", "error");
					request.setAttribute("errorDetalle", "El cliente ya tiene 3 cuentas asignadas");
					forwardForm(request, response);
					return;
				}
				
				boolean exito = negCuenta.alta(cuenta);

	            if (exito) {
	                request.setAttribute("mensaje", "ok"); 
	            } else {
	                request.setAttribute("mensaje", "error");
	                request.setAttribute("errorDetalle", "No se pudo agregar la cuenta.");
	            }
			} catch (Exception e) {
	            e.printStackTrace();
	            request.setAttribute("mensaje", "error");
	            request.setAttribute("errorDetalle", "Error inesperado: " + e.getMessage());
	        }
		}
		
	
		forwardForm(request, response);
	}
	
	private void forwardForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/Formularios/ModoBanco/ABMLCuentas/AgregarCuentas.jsp");
		rd.forward(request, response);
	}
}
