package Ejercicio;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;

public class ventanaListar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblPeliculas;
	private JList list;
	private DefaultListModel<Peliculas> listModel;

	public ventanaListar() {
		setTitle("Peliculas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblPeliculas = new JLabel("Peliculas");
		lblPeliculas.setBounds(36, 111, 53, 14);
		contentPane.add(lblPeliculas);
		
		list = new JList();
		list = gestorPeliculas.ListadoPeliculas();
		list.setBounds(99, 11, 268, 239);
		contentPane.add(list);

	}
}
