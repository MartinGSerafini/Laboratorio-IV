package presentacion.controlador;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JList;
import javax.swing.JOptionPane;

import entidad.Persona;
import negocio.PersonaNegocio;
import negocioImpl.PersonaNegocioImpl;
import presentacion.vista.MenuPrincipal;
import presentacion.vista.PanelAgregar;
import presentacion.vista.PanelEliminar;
import presentacion.vista.PanelListar;
import presentacion.vista.PanelModificar;

public class Controlador implements ActionListener{
	
	private MenuPrincipal menu;
	private PanelAgregar menuAgregar;
	private PanelListar menuListar;
	private PanelModificar menuModificar;
	private PanelEliminar menuEliminar;
	private PersonaNegocio negocio;

	
	public Controlador(MenuPrincipal menu, PersonaNegocio negocio) {
		
		//Guardo todas las instancias que recibo en el constructor
		this.menu = menu;
		this.negocio = negocio;
		
		//Instancio los paneles
		this.menuAgregar = new PanelAgregar();
		this.menuListar = new PanelListar();
		this.menuModificar = new PanelModificar();
		this.menuEliminar = new PanelEliminar();
		
		//Enlazo todos los eventos
		
		//Eventos menu del Frame principal llamado MenuPrincipal
		
		this.menu.getMenuAgregar().addActionListener(e -> mostrarPanelAgregarPersona());
		this.menu.getMenuListar().addActionListener(e -> mostrarPanelListar());
		this.menu.getMenuModificar().addActionListener(e -> mostrarPanelModificar(negocio));
		this.menu.getMenuEliminar().addActionListener(e -> mostrarPanelEliminar());
		
		//EVENTOS DE menuAgregar
		this.menuAgregar.getBtnAceptar().addActionListener(e -> agregarPersona());
		panelAgregarPersonaEventos();
		
		//EVENTOS DE menuModificar
		List<Persona> personas = negocio.obtenerTodas();
		this.menuModificar.setJListPersonas(personas);
		
		this.menuModificar.getJListPersonas().addListSelectionListener(e -> {
		    if (!e.getValueIsAdjusting()) {
		        Persona seleccionada = this.menuModificar.getJListPersonas().getSelectedValue();
		        if (seleccionada != null) {
		            this.menuModificar.setTfNombre(seleccionada.getNombre());
		            this.menuModificar.setTfApellido(seleccionada.getApellido());
		            this.menuModificar.setTfDni(seleccionada.getDni());
		        }
		    }
		});
		
		this.menuModificar.getBtnModificar().addActionListener(e -> {
			String nuevoNombre = menuModificar.getTfNombre().getText();
		    String nuevoApellido = menuModificar.getTfApellido().getText();
		    String dni = menuModificar.getTfDni().getText();
			
			modificarPersona(nuevoNombre, nuevoApellido, dni);
		});
	}

	private void mostrarPanelAgregarPersona() {
		this.menu.getContentPane().removeAll();
		this.menu.getContentPane().add(menuAgregar);
		this.menu.getContentPane().repaint();
		this.menu.getContentPane().revalidate();
    }
	
	private void mostrarPanelListar() {
		this.menu.getContentPane().removeAll();
		this.menu.getContentPane().add(menuListar);
		this.menu.getContentPane().repaint();
		this.menu.getContentPane().revalidate();
	}
	
	private void mostrarPanelModificar(PersonaNegocio negocio) {
		this.menu.getContentPane().removeAll();
		this.menu.getContentPane().add(menuModificar);
		this.menu.getContentPane().repaint();
		this.menu.getContentPane().revalidate();
	}
	
	private void mostrarPanelEliminar() {
		this.menu.getContentPane().removeAll();
		this.menu.getContentPane().add(menuEliminar);
		this.menu.getContentPane().repaint();
		this.menu.getContentPane().revalidate();
	}
	
	
	//Metodos panel agregar persona
	private void panelAgregarPersonaEventos() {
		if (menuAgregar.getTfNombre().getKeyListeners().length == 0) {
		
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
	
	private boolean validacionesAgregarPersona() {
		if(menuAgregar.getTfNombre().getText().trim().isEmpty() || menuAgregar.getTfApellido().getText().trim().isEmpty() || menuAgregar.getTfDni().getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Es necesario completar todos los campos");
			menuAgregar.getTfNombre().setText("");
			menuAgregar.getTfApellido().setText("");
			menuAgregar.getTfDni().setText("");
			return false;
		}
		
		if(menuAgregar.getTfDni().getText().length() != 8) {
			JOptionPane.showMessageDialog(null, "Ingrese un DNI válido");
			menuAgregar.getTfDni().setText("");
			return false;
		}
		return true;
	}
	
	private void agregarPersona() {
		 if(validacionesAgregarPersona()){
			String nombre = menuAgregar.getTfNombre().getText().trim();
			String apellido = menuAgregar.getTfApellido().getText().trim();
			String dni = menuAgregar.getTfDni().getText().trim();
			
			if(negocio.agregarPersona(nombre, apellido, dni)) {
				JOptionPane.showMessageDialog(null, "Se agregó con éxito");
				menuAgregar.getTfNombre().setText("");
				menuAgregar.getTfApellido().setText("");
				menuAgregar.getTfDni().setText("");
			}
			else {
				JOptionPane.showMessageDialog(null, "Error al agregar persona");
			}
		}
	}
	
	//Metodos modificar persona
	
	private void modificarPersona(String nombre, String apellido, String dni) {
		 Persona p = new Persona();
		    p.setNombre(nombre);
		    p.setApellido(apellido);
		    p.setDni(dni);

		    boolean exito = negocio.actualizarPersona(p);

		    if (exito) {
		        JOptionPane.showMessageDialog(null, "Persona modificada con éxito.");
		    } else {
		        JOptionPane.showMessageDialog(null, "Error al modificar la persona.");
		    }

		    //Refresco la lista
		    List<Persona> personasActualizadas = negocio.obtenerTodas();
		    menuModificar.setJListPersonas(personasActualizadas);
		}
	
	
	public void inicializar()
	{
		this.menu.setVisible(true);;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}

}
