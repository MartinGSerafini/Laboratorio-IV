package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import presentacion.vista.MenuPrincipal;
import presentacion.vista.VentanaAgregar;

public class Controlador implements ActionListener{
	
	private MenuPrincipal menu;
	private VentanaAgregar menuAgregar;
	
	public Controlador(MenuPrincipal menu) {
		
		//Guardo todas las instancias que recibo en el constructor
		this.menu = menu;
		
		//Instancio los paneles
		this.menuAgregar = new VentanaAgregar();
		
		//Enlazo todos los eventos
		
		 //Eventos menu del Frame principal llamado MenuPrincipal
		
		this.menu.getMenuAgregar().addActionListener(e -> mostrarVentanaAgregarPersona());;
	}
	
	private void mostrarVentanaAgregarPersona() {
        VentanaAgregar ventana = new VentanaAgregar();
        ventana.setVisible(true);
    }
	
	public void inicializar()
	{
		this.menu.setVisible(true);;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}

}
