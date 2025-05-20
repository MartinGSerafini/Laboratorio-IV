package main;

import presentacion.controlador.Controlador;
import presentacion.vista.MenuPrincipal;
import negocio.PersonaNegocio;
import negocioImpl.PersonaNegocioImpl;

public class Principal {

	public static void main(String[] args) {
		
		MenuPrincipal menu = new MenuPrincipal();
		
		PersonaNegocio negocio = new PersonaNegocioImpl();
		
		Controlador controlador = new Controlador(menu, negocio);
		controlador.inicializar(); 
	}

}

