package Principal;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

public class VentanaPrincipal extends JFrame {
	
	private JPanel contentPane;
	
	public VentanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("");
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("      GRUPO NRO: 2");
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 17));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(240, 240, 240));
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		VentanaEjercicio1 ventanaEj1 = new VentanaEjercicio1();
		VentanaEjercicio2 ventanaEj2 = new VentanaEjercicio2();
		
		JButton btnEj1 = new JButton("Ejercicio 1");
		btnEj1.setFont(new Font("Arial", Font.PLAIN, 14));
		btnEj1.setBounds(140, 27, 146, 32);
		btnEj1.addActionListener(new EventoBoton(ventanaEj1));
		panel_1.add(btnEj1);
		
		JButton btnEj2 = new JButton("Ejercicio 2");
		btnEj2.setFont(new Font("Arial", Font.PLAIN, 14));
		btnEj2.setBounds(140, 69, 146, 32);
		btnEj2.addActionListener(new EventoBoton(ventanaEj2));
		panel_1.add(btnEj2);
		
		JButton btnEj3 = new JButton("Ejercicio 3");
		btnEj3.setFont(new Font("Arial", Font.PLAIN, 14));
		btnEj3.setBounds(140, 111, 146, 32);
		panel_1.add(btnEj3);
		
	}

}

