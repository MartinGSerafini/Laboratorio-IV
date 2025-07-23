package negocio;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;

import dao.DaoCuota;
import dao.daoPrestamo;
import entidades.Cuota;
import entidades.EstadoPrestamo;
import entidades.Prestamo;

public class NegocioPrestamo {
    daoPrestamo daoPrestamo = new daoPrestamo();
    DaoCuota daoCuota = new DaoCuota();
    
	public ArrayList<Prestamo> listarPrestamos() {
		return daoPrestamo.obtenerTodos();
	}
	
	public int modificarEstadoPrestamo(String estado, String idPrestamo) {
		return daoPrestamo.modificarEstadoPrestamo(estado, idPrestamo);
	}
	
	public ArrayList<EstadoPrestamo> obtenerEstados(){
		return daoPrestamo.obtenerEstados();
	}
	
	public ArrayList<String> obtenerColumnas(){
		return daoPrestamo.obtenerColumnasPrestamo();
	}
	
	public ArrayList<Prestamo> filtrarPorEstado(String estado) {
		String sql = "SELECT * FROM prestamo where estado_pres="+estado;
		return daoPrestamo.filtrar(sql);
	}
	
	public ArrayList<Prestamo> filtrarBusqueda(String columna, String valor) {
		if(validarDatos(valor)) {
			String sql = "SELECT * FROM prestamo WHERE prestamo." + columna + " = '" + valor + "' AND estado_prestamo = 1";
		return daoPrestamo.filtrar(sql);
		}
		return null;
	}
	
	private boolean validarDatos(String valor) {
		if(valor.matches("\\d+")) {
			return true;
		}
		return false;
	}
	
	public int registrarPrestamo(Prestamo prestamo) {
	    return daoPrestamo.registrarPrestamo(prestamo);
	}
	public int insertarPrestamo(int idCliente, int idCuenta, BigDecimal importeSolicitado, BigDecimal importeTotal, int plazoMeses, BigDecimal montoCuota, Date fechaSolicitud) {
	    return daoPrestamo.insertarPrestamoYObtenerId(idCliente, idCuenta, importeSolicitado, importeTotal, plazoMeses, montoCuota, fechaSolicitud);
	}
	public void insertarCuotas(int idPrestamo, int cantidadCuotas, BigDecimal montoCuota, Date fechaInicio) {
        daoCuota.insertarCuotas(idPrestamo, cantidadCuotas, montoCuota, fechaInicio);
    }
	private Date calcularFechaVencimiento(Date fechaInicio, int mesesSumar) {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.setTime(fechaInicio);
        cal.add(java.util.Calendar.MONTH, mesesSumar);
        return new Date(cal.getTimeInMillis());
    }
	public ArrayList<Prestamo> obtenerPrestamosPendientesPorCliente(int idCliente) {
	    return daoPrestamo.obtenerPrestamosActivosPorCliente(idCliente);
	}
	
	public boolean pagarPrestamoCompleto(int idPrestamo) {
		return daoPrestamo.pagarPrestamoCompleto(idPrestamo);
	}
}
