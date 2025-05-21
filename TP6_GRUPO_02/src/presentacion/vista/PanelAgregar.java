package presentacion.vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PanelAgregar extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField tfNombre;
	private JTextField tfApellido;
	private JTextField tfDni;
	
	private JButton btnAceptar; 

	public PanelAgregar() {
		setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Calibri", Font.BOLD, 16));
		lblNombre.setBounds(111, 27, 56, 20);
		add(lblNombre);
		
		JLabel lblDni = new JLabel("Dni");
		lblDni.setFont(new Font("Calibri", Font.BOLD, 16));
		lblDni.setBounds(111, 113, 23, 20);
		add(lblDni);
		
		tfNombre = new JTextField();
		tfNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isLetter(c) && c != ' ') {
					e.consume(); 
				}
			}
		});
		tfNombre.setBounds(201, 25, 117, 20);
		add(tfNombre);
		tfNombre.setColumns(10);
		
		tfApellido = new JTextField();
		tfApellido.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isLetter(c) && c != ' ') {
					e.consume(); 
				}
			}
		});
		tfApellido.setColumns(10);
		tfApellido.setBounds(201, 68, 117, 20);
		add(tfApellido);
		
		tfDni = new JTextField();
		tfDni.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume(); 
				}
			}
		});
		tfDni.setColumns(10);
		tfDni.setBounds(201, 111, 117, 20);
		add(tfDni);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setFont(new Font("Calibri", Font.BOLD, 16));
		lblApellido.setBounds(111, 70, 57, 20);
		add(lblApellido);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		btnAceptar.setBounds(111, 157, 117, 27);
		add(btnAceptar);
	}
	
	public JButton getBtnAceptar() {
		return btnAceptar;
	}
	
	public JTextField getTfNombre(){
		return tfNombre;
	}
	
	public JTextField getTfApellido(){
		return tfApellido;
	}
	
	public JTextField getTfDni(){
		return tfDni;
	}
}
