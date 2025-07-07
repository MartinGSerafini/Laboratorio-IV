package controladorCliente;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Cliente;
import entidades.Cuenta;
import negocio.NegocioCliente;
import negocio.NegocioCuenta;
import negocio.NegocioMovimientos;


@WebServlet("/TransferenciasClienteServlet")
public class TransferenciasClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String cbu = request.getParameter("cbuInput");
		    
	    // Validar que el CBU no sea nulo ni vacío
	    if (cbu == null || cbu.trim().isEmpty()) {
	        request.setAttribute("error", "Debe ingresar un CBU para buscar la cuenta");
	        cargarDatosVista(request, cbu);
	        request.getRequestDispatcher("Formularios/ModoCliente/Transferencias.jsp").forward(request, response);
	        return;
	    }

	    NegocioCuenta negocioCuenta = new NegocioCuenta();
	    Cuenta cuentaDestino = negocioCuenta.obtenerCuentaCBU(cbu);

	    if (cuentaDestino.getCbuCuenta() == null) {
	        request.setAttribute("error", "No se encontró ninguna cuenta con ese CBU");
	        request.getRequestDispatcher("Formularios/ModoCliente/Transferencias.jsp").forward(request, response);
	        return;
	    }

	    cargarDatosVista(request, cbu);
		RequestDispatcher rd = request.getRequestDispatcher("Formularios/ModoCliente/Transferencias.jsp");
        rd.forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		NegocioCuenta negocioCuenta = new NegocioCuenta();
		
		// Obtener parámetros del formulario
		String cbuOrigen = request.getParameter("cbuOrigen");
        String cbuDestino = request.getParameter("cbuDestino");
        String montoStr = request.getParameter("monto");
        
 
        // Validar inputs básicos
        if (cbuOrigen == null || cbuDestino == null || montoStr == null) {
            request.setAttribute("error", "Datos incompletos");
            cargarDatosVista(request, cbuDestino);
            request.getRequestDispatcher("Formularios/ModoCliente/Transferencias.jsp").forward(request, response);
            return;
        }

        BigDecimal monto;
        try {
            monto = new BigDecimal(montoStr);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Monto inválido");
            cargarDatosVista(request, cbuDestino);
            request.getRequestDispatcher("Formularios/ModoCliente/Transferencias.jsp").forward(request, response);
            return;
        }


        // Obtener cuenta origen
        Cuenta cuentaOrigen = negocioCuenta.obtenerCuentaCBU(cbuOrigen);
        
        if (cuentaOrigen == null){
            request.setAttribute("error", "Cuenta de origen inválida");
            cargarDatosVista(request, cbuDestino);
            request.getRequestDispatcher("Formularios/ModoCliente/Transferencias.jsp").forward(request, response);
            return;
        }

        // Verificar saldo suficiente
        if (cuentaOrigen.getSaldoCuenta().compareTo(monto) < 0) {
            request.setAttribute("error", "Saldo insuficiente");
            cargarDatosVista(request, cbuDestino);
            request.getRequestDispatcher("Formularios/ModoCliente/Transferencias.jsp").forward(request, response);
            return;
        }

        // Obtener cuenta destino por CBU
        Cuenta cuentaDestino = negocioCuenta.obtenerCuentaCBU(cbuDestino);
        if (cuentaDestino == null){
            request.setAttribute("error", "Cuenta de destino inválida");
            cargarDatosVista(request, cbuDestino);
            request.getRequestDispatcher("Formularios/ModoCliente/Transferencias.jsp").forward(request, response);
            return;
        }

        // Ejecutar transferencia
        boolean exito = negocioCuenta.realizarTransferencia(cuentaOrigen.getCbuCuenta(), cuentaDestino.getCbuCuenta(), monto);
        if (exito) {
        	NegocioMovimientos negocioMov = new NegocioMovimientos();
        	negocioMov.registrarMovimientoTransferencia(
        		    Integer.parseInt(cuentaOrigen.getIdCuenta()),
        		    Integer.parseInt(cuentaDestino.getIdCuenta()),
        		    monto,
        		    "Transferencia"
        		);
        	request.setAttribute("mensaje", "Transferencia realizada con éxito");
        } else {
            request.setAttribute("error", "Error al procesar la transferencia");
        }
        
        
        cargarDatosVista(request, cbuDestino);
        request.getRequestDispatcher("Formularios/ModoCliente/Transferencias.jsp").forward(request, response);
    }
		
	
	private void cargarDatosVista(HttpServletRequest request, String cbuDestino) {
	    NegocioCliente negocioCliente = new NegocioCliente();
	    NegocioCuenta negocioCuenta = new NegocioCuenta();

	    Cuenta cuentaDestino = negocioCuenta.obtenerCuentaCBU(cbuDestino);
	    request.setAttribute("cuenta", cuentaDestino);

	    int idCliente = cuentaDestino.getIdClienteCuenta();
	    Cliente cliente = negocioCliente.obtenerClienteXid(idCliente);
	    request.setAttribute("nombre", cliente.getNombreCliente());
	    request.setAttribute("apellido", cliente.getApellidoCliente());

	    String descTipoCuenta = negocioCuenta.obtenerTipoXid(cuentaDestino.getIdTipoCuentaCuenta());
	    request.setAttribute("descTipoCuenta", descTipoCuenta);

	    HttpSession session = request.getSession();
	    String usuario = (String) session.getAttribute("nombreUsuario");
	    Cliente clienteLogueado = negocioCliente.obtenerCliente(usuario);
	    ArrayList<Cuenta> cuentasCliente = negocioCuenta.cuentasXCliente(clienteLogueado.getIdCliente());
	    request.setAttribute("cuentasCliente", cuentasCliente);
	}
	
	
		
}
