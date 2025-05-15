package main;

import presentacion.controlador.Controlador;
import presentacion.vista.MenuPrincipal;

public class Principal {

	public static void main(String[] args) {
		
		MenuPrincipal menu = new MenuPrincipal();
		Controlador controlador = new Controlador(menu);
		controlador.inicializar(); 
	}

}

