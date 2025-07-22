package negocio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import dao.DaoTipoCuenta;
import dao.daoCuenta;
import entidades.Cuenta;
import excepciones.OperacionNoEfectuadaExc;
import interfaces.servicioABML;

public class NegocioCuenta implements servicioABML<Cuenta>{

	daoCuenta daoCuenta = new daoCuenta();
	DaoTipoCuenta daoTipoCuenta = new DaoTipoCuenta();
	
	@Override
	public boolean alta(Cuenta cuenta) {
		try {
			boolean filas= daoCuenta.altaCuenta(cuenta);
			
			if(filas==false) {
				throw new OperacionNoEfectuadaExc("No se insertó ningúna cuenta.");
			}
			
			return true;
		}
		catch(OperacionNoEfectuadaExc e) {
			e.printStackTrace();
			System.out.println("Error: "+e.getMessage());
			return false;
		}
	}

	@Override
	public boolean baja(String id) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean lectura(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Cuenta> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Cuenta> ObtenerListadoCuentas(){
		ArrayList<Cuenta> lista = new ArrayList<Cuenta>();
		lista = daoCuenta.obtenerCuentas();
		return lista;
	}
	
	public ArrayList<Cuenta> obtenerCuentasPorFiltro(String columna, String valor){
		ArrayList<Cuenta> lista = new ArrayList<Cuenta>();
		lista = daoCuenta.obtenerCuentasPorFiltro(columna,valor);
		return lista;
	}
	
	public ArrayList<String> obtenerColumnasCuentas(){
		ArrayList<String> columnas = new ArrayList<String>();
		columnas = daoCuenta.obtenerColumnasCuentas();
		return columnas;
	}
	
	public Cuenta obtenerCuentaCBU(String cbu){
		Cuenta cuenta = new Cuenta();
		cuenta = daoCuenta.obtenerCuentaCBU(cbu);
		return cuenta;
	} 
	
	public String obtenerTipoXid(int id) {
		String desc = daoTipoCuenta.obtenerTipoXid(id);
		return desc;
	}
	
	public boolean realizarTransferencia(String cbuOrigen, String cbuDestino, BigDecimal monto) {
		return daoCuenta.realizarTransferencia(cbuOrigen, cbuDestino, monto);
	}
	 
	public List<Cuenta> obtenerCuentasPorCliente(int idCliente) {
		return daoCuenta.obtenerCuentasPorCliente(idCliente);
	}
	
	public boolean bajaCuenta(String id) {
	    return daoCuenta.bajaLogicaCuenta(id);
	}
	
	public boolean modificacion(String id, Cuenta cuenta) {
	    return daoCuenta.modificarCuenta(cuenta);
	}
	
	public int contarCuentasXClientes(int cliente) {
		return daoCuenta.contarCuentasXClientes(cliente);
	}
	
	public int buscarCuentaXIdCliente(String idCuenta) {
		return daoCuenta.buscarCuentaXIdCliente(idCuenta);
	}
	
	public String validarYVerificarCuenta(String idCuenta, int idClienteCuenta, String fechaCreacionCuenta, String numeroCuenta,
            String cbuCuenta, BigDecimal saldoCuenta, int idTipoCuenta) {

		System.out.println("ValidarCliente - Parámetros recibidos: " +
		"idCuenta=" + idCuenta +
		", idClienteCuenta=" + idClienteCuenta +
		", fechaCreacionCuenta=" + fechaCreacionCuenta +
		", numeroCuenta=" + numeroCuenta +
		", cbuCuenta=" + cbuCuenta +
		", saldoCuenta=" + saldoCuenta +
		", idTipoCuenta=" + idTipoCuenta);
		
		
		StringBuilder errores = new StringBuilder();
		
		if (numeroCuenta == null || numeroCuenta.trim().isEmpty()) {
		errores.append("El Numero de Cuenta es obligatorio.\n");
		}
		if(cbuCuenta == null || cbuCuenta.trim().isEmpty()) {
		errores.append("El 	CBU es obligatorio y debe tener sólo números.\n");
		}
				if (idClienteCuenta <= 0) {
		errores.append("El id Cliente Cuenta es obligatorio y debe ser un n�mero v�lido.\n");
		}
		if (idTipoCuenta <= 0) {
		errores.append("El id Tipo de Cuenta Cuenta es obligatorio y debe ser un n�mero v�lido.\n");
		}
		if (saldoCuenta.compareTo(BigDecimal.ZERO) < 0) {
			errores.append("El saldo no debe ser negativo.\n");
		}
		if (fechaCreacionCuenta != null && !fechaCreacionCuenta.trim().isEmpty()) {
		try {
		java.sql.Date.valueOf(fechaCreacionCuenta);
		} catch (IllegalArgumentException e) {
			errores.append("Formato de fecha de creación inv�lido.\n");
			}
		}
		if (cbuCuenta == null || cbuCuenta.trim().isEmpty() || !cbuCuenta.matches("\\d{12}")) {
		    errores.append("El CBU debe tener exactamente 22 dígitos numéricos.\n");
		}
		
		if (daoCuenta.existeCBU(cbuCuenta)) {
		    errores.append("El CBU ya existe en otra cuenta.\n");
		}
		
		if(daoCuenta.existeNumeroCuenta(numeroCuenta)) {
			 errores.append("Ya existe ese nro. de cuenta.\n");
		}
			
		return errores.length() > 0 ? errores.toString() : null;
		}
			
		public ArrayList<Cuenta> cuentasXCliente(String idCliente){
			int id = Integer.parseInt(idCliente);
			return daoCuenta.cuentasXCliente(id);
		}
		public int obtenerIdCuentaPorNumero(String numeroCuenta) {
		    return daoCuenta.obtenerIdCuentaPorNumero(numeroCuenta);
		}
	}
