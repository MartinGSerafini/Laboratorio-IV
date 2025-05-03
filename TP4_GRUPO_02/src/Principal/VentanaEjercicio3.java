package Principal;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.*;
import java.awt.FlowLayout;

public class VentanaEjercicio3 extends JFrame {
	private JPanel contentPane;
	
	public VentanaEjercicio3() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		
		setTitle("Seleccion Multiple");
		
		//Primer Panel Sistema Operativo
		JPanel panelSO = new JPanel();
		panelSO.setBorder(BorderFactory.createTitledBorder("")); // Borde vacío, como en la imagen
		panelSO.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelSO.add(new JLabel("Elije un sistema operativo"));
        JRadioButton rbWindows = new JRadioButton("Windows");
        JRadioButton rbMac = new JRadioButton("Mac");
        JRadioButton rbLinux = new JRadioButton("Linux");
        ButtonGroup bgSO = new ButtonGroup();
        bgSO.add(rbWindows);
        bgSO.add(rbMac);
        bgSO.add(rbLinux);
        panelSO.add(rbWindows);
        panelSO.add(rbMac);
        panelSO.add(rbLinux);
        contentPane.add(panelSO);
		
	}
	public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            try {
                VentanaEjercicio3 frame = new VentanaEjercicio3();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
