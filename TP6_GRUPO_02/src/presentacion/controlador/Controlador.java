package presentacion.controlador;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

import negocio.PersonaNegocio;
import presentacion.vista.MenuPrincipal;
import presentacion.vista.VentanaAgregar;
import presentacion.vista.VentanaListar;

public class Controlador implements ActionListener{
	
	private MenuPrincipal menu;
	private VentanaAgregar menuAgregar;
	private VentanaListar ventanaListar;

	
	public Controlador(MenuPrincipal menu, PersonaNegocio negocio) {
		
		//Guardo todas las instancias que recibo en el constructor
		this.menu = menu;
		
		//Instancio los paneles
		this.menuAgregar = new VentanaAgregar();

		
		//Enlazo todos los eventos
		
		
		
		
		//Eventos menu del Frame principal llamado MenuPrincipal
		
		this.menu.getMenuAgregar().addActionListener(e -> mostrarVentanaAgregarPersona());
		this.menu.getMenuListar().addActionListener(e -> mostrarVentanaListar());

	}
	
	private void mostrarVentanaAgregarPersona() {
		if (!menuAgregar.isVisible()) {
            ventanaAgregarPersonaValidaciones();
            menuAgregar.setVisible(true);
        }
    }
	
	private void mostrarVentanaListar() {
	    if (ventanaListar == null || !ventanaListar.isDisplayable()) {
	        ventanaListar = new VentanaListar();
	    }
	    ventanaListar.setVisible(true);
	}
	
	private void ventanaAgregarPersonaValidaciones() {
		
		if (menuAgregar.getTfNombre().getKeyListeners().length == 0) {
			
			menuAgregar.getBtnAceptar().addActionListener(new ActionListener(){
				
				public void actionPerformed(ActionEvent e) {
					
					String nombre = menuAgregar.getTfNombre().getText();
					String apellido = menuAgregar.getTfApellido().getText();
					String dni = menuAgregar.getTfDni().getText();
					if (nombre.isEmpty() || apellido.isEmpty() || dni.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Es necesario completar todos los campos");
					} 
				}
			});
		
		this.menuAgregar.getTfNombre().addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isLetter(c) && c != ' ' && c != KeyEvent.VK_BACK_SPACE) {
                    e.consume(); // Ignora la tecla si no es letra ni espacio
                    JOptionPane.showMessageDialog(null, "Sólo se deban ingresar letras");
                }
			}
		});
		
		this.menuAgregar.getTfApellido().addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isLetter(c) && c != ' ' && c != KeyEvent.VK_BACK_SPACE) {
                    e.consume(); // Ignora la tecla si no es letra ni espacio
                    JOptionPane.showMessageDialog(null, "Sólo se deban ingresar letras");
                }
			}
		});
		
		this.menuAgregar.getTfDni().addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume(); // Cancela el carácter
                    JOptionPane.showMessageDialog(null, "Solo se permiten números (sin espacios)");
                }
            }
		});
		
		}
	}
	
	public void inicializar()
	{
		this.menu.setVisible(true);;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}

}
