package Principal;

import java.awt.BorderLayout;
import java.awt.Color;
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
	private JTextField nombreField, apellidoField, telefonoField, fechaField;
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
		
		setTitle("Contactos");
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(50, 20, 100, 25);
		panel_1.add(lblNombre);

		nombreField = new JTextField();
		nombreField.setBounds(150, 20, 200, 25);
		panel_1.add(nombreField);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(50, 60, 100, 25);
		panel_1.add(lblApellido);

		apellidoField = new JTextField();
		apellidoField.setBounds(150, 60, 200, 25);
		panel_1.add(apellidoField);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(50, 100, 100, 25);
		panel_1.add(lblTelefono);

		telefonoField = new JTextField();
		telefonoField.setBounds(150, 100, 200, 25);
		panel_1.add(telefonoField);
		
		JLabel lblFecha = new JLabel("Fecha Nac:");
		lblFecha.setBounds(50, 140, 100, 25);
		panel_1.add(lblFecha);

		fechaField = new JTextField();
		fechaField.setBounds(150, 140, 200, 25);
		panel_1.add(fechaField);
		
		JLabel lblDatos = new JLabel("Los datos ingresados fueron: ");
		lblDatos.setBounds(50, 218, 312, 25);
		panel_1.add(lblDatos);
		
		JButton btnMostrar = new JButton("Mostrar");
		btnMostrar.addActionListener(new eventoBotonMostrar(nombreField, apellidoField, telefonoField, fechaField, lblDatos));
		btnMostrar.setBounds(265, 186, 85, 21);
		panel_1.add(btnMostrar);
	}
}

class eventoBotonMostrar implements ActionListener{

	private JTextField jtNombre;
	private JTextField jtApellido;
	private JTextField jtTelefono;
	private JTextField jtFecha;
	private JLabel lblDatos;
	
	
	eventoBotonMostrar(JTextField nombre, JTextField apellido, JTextField telefono, JTextField fecha, JLabel datos){
		jtNombre = nombre;
		jtApellido = apellido;
		jtTelefono = telefono;
		jtFecha = fecha;
		lblDatos= datos;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int vacio = 0;
		
		if(TxtVacio(jtNombre))
			vacio ++;
		if(TxtVacio(jtApellido))
			vacio ++;
		if(TxtVacio(jtTelefono))
			vacio ++;
		if(TxtVacio(jtFecha))
			vacio++;
		
		if(vacio == 0) {
			lblDatos.setText("Los datos ingresados fueron: " + jtNombre.getText() + ", " + jtApellido.getText() + ", " + jtTelefono.getText()+ ", " + jtFecha.getText());
			jtNombre.setText("");
			jtApellido.setText("");
			jtTelefono.setText("");
			jtFecha.setText("");
		}
	}
	
	private boolean TxtVacio(JTextField txt) {
	    if (txt.getText().trim().isEmpty()) {
	        txt.setBackground(Color.RED);
	        return true;
	    } else {
	        txt.setBackground(Color.WHITE);
	        return false;
	    }
	}
	
}

