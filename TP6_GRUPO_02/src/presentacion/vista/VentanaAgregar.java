package presentacion.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VentanaAgregar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JTextField tfNombre;
	private JTextField tfApellido;
	private JTextField tfDni;
	
	private JButton btnAceptar; 

	public VentanaAgregar() {
		setTitle("Programa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panel);
		panel.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Calibri", Font.BOLD, 16));
		lblNombre.setBounds(99, 42, 129, 29);
		panel.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setFont(new Font("Calibri", Font.BOLD, 16));
		lblApellido.setBounds(99, 81, 129, 29);
		panel.add(lblApellido);
		
		JLabel lblDni = new JLabel("Dni");
		lblDni.setFont(new Font("Calibri", Font.BOLD, 16));
		lblDni.setBounds(99, 117, 129, 29);
		panel.add(lblDni);
		
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
		tfNombre.setBounds(202, 45, 113, 19);
		panel.add(tfNombre);
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
		tfApellido.setBounds(202, 84, 113, 19);
		panel.add(tfApellido);
		
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
		tfDni.setBounds(202, 120, 113, 19);
		panel.add(tfDni);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		btnAceptar.setBounds(99, 156, 92, 21);
		panel.add(btnAceptar);
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
