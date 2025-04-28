package Principal;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

public class VentanaEjercicio1 extends JFrame {
	
	private JPanel contentPane;
	private JTextField nombreField, telefonoField, emailField, fechaField;
	private JLabel resultadoLabel;
	
	public VentanaEjercicio1() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		setTitle("Contactos"); //Cambia el titolo de la ventana
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(50, 20, 100, 25);
		panel_1.add(lblNombre);

		nombreField = new JTextField();
		nombreField.setBounds(150, 20, 200, 25);
		panel_1.add(nombreField);

		JLabel lblTelefono = new JLabel("Teléfono:");
		lblTelefono.setBounds(50, 60, 100, 25);
		panel_1.add(lblTelefono);

		telefonoField = new JTextField();
		telefonoField.setBounds(150, 60, 200, 25);
		panel_1.add(telefonoField);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(50, 100, 100, 25);
		panel_1.add(lblEmail);

		emailField = new JTextField();
		emailField.setBounds(150, 100, 200, 25);
		panel_1.add(emailField);
		
		JLabel lblFecha = new JLabel("Fecha Nac:");
		lblFecha.setBounds(50, 140, 100, 25);
		panel_1.add(lblFecha);

		fechaField = new JTextField();
		fechaField.setBounds(150, 140, 200, 25);
		panel_1.add(fechaField);
	}
}
