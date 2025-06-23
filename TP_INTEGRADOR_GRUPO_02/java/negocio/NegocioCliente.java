package negocio;

import java.util.List;

import dao.daoCliente;
import entidades.Cliente;
import interfaces.servicioABML;

public class NegocioCliente implements servicioABML<Cliente>{

	daoCliente daoCliente = new daoCliente();
	
	
	@Override
	public boolean alta(Cliente cliente) {
		
		return daoCliente.altaCliente(cliente);
		
	}
 
	@Override
	public boolean baja(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modificacion(String id, Cliente cliente) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean lectura(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Cliente> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}


}
