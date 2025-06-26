package negocio;

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
	public boolean modificacion(String id, Cuenta entidad) {
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

	
	
}
