package negocio;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import entidades.Cuota;
import dao.DaoCuota;

public class NegocioCuota {

    DaoCuota daoCuota = new DaoCuota();

    public boolean registrarCuota(Cuota cuota) {
        return daoCuota.registrarCuota(cuota);
    }
    
    public boolean generarCuotasParaPrestamo(int idPrestamo, int cantidadCuotas, BigDecimal importeCuota, Date fechaInicio) {
        boolean exito = true;
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fechaInicio);

        for (int i = 1; i <= cantidadCuotas; i++) {
            calendario.add(Calendar.MONTH, 1);
            Date fechaVencimiento = new Date(calendario.getTimeInMillis());

            Cuota cuota = new Cuota();
            cuota.setIdPrestamoCuota(idPrestamo);
            cuota.setNumeroCuota(i);
            cuota.setImporteCuota(importeCuota);
            cuota.setFechaVencCuota(fechaVencimiento);
            cuota.setEstadoCuota(1); 

            if (!daoCuota.registrarCuota(cuota)) {
                exito = false;
                break;
            }
        }

        return exito;
    }
    
    public ArrayList<Cuota> obtenerCuotasPorPrestamo(int idPrestamo){
    	return daoCuota.obtenerCuotasPorPrestamo(idPrestamo);
    }
    
    public boolean pagarPrestamoCompleto(int idPrestamo) {
    	return daoCuota.pagarPrestamoCompleto(idPrestamo);
    }
}
