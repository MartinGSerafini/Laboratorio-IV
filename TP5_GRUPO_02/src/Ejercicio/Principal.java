package Ejercicio;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static DefaultListModel<Peliculas> listModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					listModel = new DefaultListModel<Peliculas>();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setTitle("Programa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menuPeliculas = new JMenu("Peliculas");
		menuBar.add(menuPeliculas);
		
		JMenuItem menuItemAgregar = new JMenuItem("Agregar");
		menuPeliculas.add(menuItemAgregar);
		menuItemAgregar.addActionListener(e -> {
			ventanaAgregar ventana = new ventanaAgregar();
			ventana.setListModel(listModel);
			ventana.setVisible(true);
		});
		
		JMenuItem menuItemListar = new JMenuItem("Listar");
		menuItemListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventanaListar listado = new ventanaListar();
				listado.setListModel(listModel);
				listado.setVisible(true);
			}
		});
		menuPeliculas.add(menuItemListar);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
	}

}
