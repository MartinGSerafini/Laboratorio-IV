package negocio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import dao.daoCuenta;
import entidades.Cuenta;
import excepciones.OperacionNoEfectuadaExc;
import interfaces.servicioABML;

public class NegocioCuenta implements servicioABML<Cuenta>{

	daoCuenta daoCuenta = new daoCuenta();
	
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
	
	public boolean bajaCuenta(String id) {
	    return daoCuenta.bajaLogicaCuenta(id);
	}
	
	public boolean modificacion(String id, Cuenta cuenta) {
	    return daoCuenta.modificarCuenta(cuenta);
	}
	
	public String validarYVerificarCuenta(String idCuenta, int idClienteCuenta, String fechaCreacionCuenta, String numeroCuenta,
            String cbuCuenta, BigDecimal saldoCuenta, int idTipoCuenta) {

System.out.println("ValidarCliente - Par�metros recibidos: " +
"idCuenta=" + idCuenta +
", idClienteCuenta=" + idClienteCuenta +
", fechaCreacionCuenta=" + fechaCreacionCuenta +
", numeroCuenta=" + numeroCuenta +
", cbuCuenta=" + cbuCuenta +
", saldoCuenta=" + saldoCuenta +
", idTipoCuenta=" + idTipoCuenta);


StringBuilder errores = new StringBuilder();

// Validar campos String
if (idCuenta == null || idCuenta.trim().isEmpty()) {
errores.append("El idCuenta es obligatorio.\n");
}
if (numeroCuenta == null || numeroCuenta.trim().isEmpty()) {
errores.append("El Numero de Cuenta es obligatorio.\n");
}
if(cbuCuenta == null || cbuCuenta.trim().isEmpty()) {
errores.append("El 	CBU es obligatorio y debe tener sólo números.\n");
}

// Validar campos num�ricos
if (idClienteCuenta <= 0) {
errores.append("El id Cliente Cuenta es obligatorio y debe ser un n�mero v�lido.\n");
}
if (idTipoCuenta <= 0) {
errores.append("El id Tipo de Cuenta Cuenta es obligatorio y debe ser un n�mero v�lido.\n");
}

// Validar formatos espec�ficos
if (saldoCuenta.compareTo(BigDecimal.ZERO) <= 0) {
	errores.append("El saldo no debe ser negativo.\n");
}

// Validar fecha
if (fechaCreacionCuenta != null && !fechaCreacionCuenta.trim().isEmpty()) {
try {
java.sql.Date.valueOf(fechaCreacionCuenta);
} catch (IllegalArgumentException e) {
errores.append("Formato de fecha de creación inv�lido.\n");
}
}

// Validar longitud CBU solo si no hay errores anteriores en esos campos
if (cbuCuenta == null || !String.valueOf(cbuCuenta).matches("\\d{12}") || Integer.parseInt(cbuCuenta) <=0) {
errores.append("El CBU debe tener exactamente 22 d�gitos num�ricos.\n");
}

// Validar unicidad solo si no hay errores con esos campos

if (errores.length() == 0) {
if (daoCuenta.existeCBU(cbuCuenta)) {
errores.append("El CBU ya est� registrado para otro cliente.\n");
}
}

return errores.length() > 0 ? errores.toString() : null;
}
}
