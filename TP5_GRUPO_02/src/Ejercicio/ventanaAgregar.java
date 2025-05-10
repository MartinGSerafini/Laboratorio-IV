package Ejercicio;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;

import java.awt.CardLayout;
import javax.swing.SpringLayout;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class ventanaAgregar extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private DefaultListModel<Peliculas> listModel;
	
	
	public ventanaAgregar() {
		setTitle("Peliculas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblID = new JLabel("ID");
		lblID.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblID.setBounds(88, 49, 46, 14);
		contentPane.add(lblID);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNombre.setBounds(85, 87, 64, 14);
		contentPane.add(lblNombre);
		
		JLabel lblGenero = new JLabel("Genero");
		lblGenero.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGenero.setBounds(88, 120, 46, 14);
		contentPane.add(lblGenero);
		
		JTextField nombre = new JTextField();
		nombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		nombre.setBounds(185, 85, 109, 20);
		contentPane.add(nombre);
		nombre.setColumns(10);	
		
		JComboBox<Generos> comboBox = new JComboBox<Generos>();
		comboBox.setBounds(185, 117, 109, 22);
		contentPane.add(comboBox);
		comboBox.addItem(new Generos(0, "Genero"));
		comboBox.addItem(new Generos(1, "Terror"));
		comboBox.addItem(new Generos(2, "Accion"));
		comboBox.addItem(new Generos(3, "Suspenso"));
		comboBox.addItem(new Generos(4, "Romantica"));
		
		JLabel lblMostrarID = new JLabel("");
		lblMostrarID.setBounds(185, 50, 46, 14);
		contentPane.add(lblMostrarID);
		lblMostrarID.setText(gestorPeliculas.getIdActual()+"");
		
		JButton btnAceptar = new JButton("Acceptar");
		btnAceptar.setBounds(88, 165, 123, 23);
		contentPane.add(btnAceptar);
		
		Generos g = new Generos(1, "genero");
		Peliculas x = new Peliculas(0, "a", g);
		
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombrePelicula = nombre.getText().trim();
				Generos generoSeleccionado = (Generos) comboBox.getSelectedItem();
				
				if (nombrePelicula.isEmpty() && generoSeleccionado.getId() == 0) {
					JOptionPane.showMessageDialog(null, "Debe completar el nombre y seleccionar el género");
					return;
				}
				if (nombrePelicula.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Ingrese el nombre de la película");
					return;
				}
				if (generoSeleccionado.getId() == 0) {
					JOptionPane.showMessageDialog(null, "Seleccione un género");
					return;
				}
				
				Peliculas nueva = new Peliculas(
					gestorPeliculas.getIdActual(),
					nombrePelicula,
					generoSeleccionado
				);
				
				if (listModel != null) {
					listModel.addElement(nueva);
				}
				
				gestorPeliculas.incrementarID();
				lblMostrarID.setText(String.valueOf(gestorPeliculas.getIdActual()));
				nombre.setText("");
				comboBox.setSelectedIndex(0);
			}
		});
		
		setVisible(true);
	}
	
	public void setListModel(DefaultListModel<Peliculas> listaPeliculas) {
		this.listModel = listaPeliculas;
	}
}