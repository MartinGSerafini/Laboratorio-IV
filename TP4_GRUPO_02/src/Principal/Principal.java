package Principal;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Principal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
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
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		JButton btnEj1 = new JButton("Ejercicio 1");
		btnEj1.setFont(new Font("Arial", Font.PLAIN, 14));
		btnEj1.setBounds(140, 27, 146, 32);
		panel_1.add(btnEj1);
		
		JButton btnEj2 = new JButton("Ejercicio 2");
		btnEj2.setFont(new Font("Arial", Font.PLAIN, 14));
		btnEj2.setBounds(140, 69, 146, 32);
		panel_1.add(btnEj2);
		
		JButton btnEj3 = new JButton("Ejercicio 3");
		btnEj3.setFont(new Font("Arial", Font.PLAIN, 14));
		btnEj3.setBounds(140, 111, 146, 32);
		panel_1.add(btnEj3);
	}
}
