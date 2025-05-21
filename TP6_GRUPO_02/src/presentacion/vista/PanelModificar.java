package presentacion.vista;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.BorderLayout;
import javax.swing.JTextField;

import entidad.Persona;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class PanelModificar extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField tfNombre;
	private JTextField tfApellido;
	private JTextField tfDni;
	private DefaultListModel<Persona> modeloLista;
	private JList<Persona> JListPersonas;
	private ArrayList<Persona> personas;
	DefaultListModel<Persona> modelo = new DefaultListModel<>();
	JButton btnModificar;

	/**
	 * Create the panel.
	 */
	public PanelModificar() {
		
		setLayout(null);
		
		JLabel lblSeleccionarPersona = new JLabel("Seleccione la persona que desea modificar");
		lblSeleccionarPersona.setBounds(27, 11, 304, 14);
		add(lblSeleccionarPersona);
		
		
		JListPersonas = new JList<>(modelo);
		JListPersonas.setBounds(27, 36, 397, 162);
		add(JListPersonas);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(27, 209, 86, 20);
		tfNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isLetter(c) && c != ' ') {
					e.consume(); 
				}
			}
		});
		add(tfNombre);
		tfNombre.setColumns(10);
		
		tfApellido = new JTextField();
		tfApellido.setBounds(123, 209, 86, 20);
		tfApellido.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isLetter(c) && c != ' ') {
					e.consume(); 
				}
			}
		});
		add(tfApellido);
		tfApellido.setColumns(10);
		
		tfDni = new JTextField();
		tfDni.setBounds(219, 209, 86, 20);
		tfDni.setEditable(false); //para no modificar el dni
		add(tfDni);
		tfDni.setColumns(10);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setBounds(332, 205, 92, 29);
		add(btnModificar);
		

	}

	//Getters y setters
	
	public JTextField getTfNombre() {
		return tfNombre;
	}

	public JTextField getTfApellido() {
		return tfApellido;
	}

	public JTextField getTfDni() {
		return tfDni;
	}

	public DefaultListModel<Persona> getModeloLista() {
		return modeloLista;
	}

	public JList<Persona> getJListPersonas() {
		return JListPersonas;
	}

	public ArrayList<Persona> getPersonas() {
		return personas;
	}

	public JButton getBtnModificar() {
		return btnModificar;
	}
	
	public void setTfNombre(String tfNombre) {
		this.tfNombre.setText(tfNombre);;
	}

	public void setTfApellido(String tfApellido) {
		this.tfApellido.setText(tfApellido);
	}

	public void setTfDni(String tfDni) {
		this.tfDni.setText(tfDni);
	}

	public void setModeloLista(DefaultListModel<Persona> modeloLista) {
		this.modeloLista = modeloLista;
	}

	public void setJListPersonas(List<Persona> personas) {
		modelo.clear();
	    for (Persona p : personas) {
	        modelo.addElement(p);
	    }
	}

	public void setPersonas(ArrayList<Persona> personas) {
		this.personas = personas;
	}
	
	
}
