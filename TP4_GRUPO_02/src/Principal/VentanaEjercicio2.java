package Principal;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.SystemColor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaEjercicio2 extends JFrame {
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField1;
	private JTextField textField2;
	private JTextField textField3;
	private JComboBox<String> comboTP;
	
	public VentanaEjercicio2() {
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		setTitle("Promedio");
		
		JLabel lblNota1 = new JLabel("Nota 1:");
        lblNota1.setBounds(30, 20, 80, 25);
        panel.add(lblNota1);

        textField1 = new JTextField();
        textField1.setBounds(100, 20, 150, 25);
        panel.add(textField1);

        JLabel lblNota2 = new JLabel("Nota 2:");
        lblNota2.setBounds(30, 60, 80, 25);
        panel.add(lblNota2);

        textField2 = new JTextField();
        textField2.setBounds(100, 60, 150, 25);
        panel.add(textField2);

        JLabel lblNota3 = new JLabel("Nota 3:");
        lblNota3.setBounds(30, 100, 80, 25);
        panel.add(lblNota3);

        textField3 = new JTextField();
        textField3.setBounds(100, 100, 150, 25);
        panel.add(textField3);
        
        JLabel lblTP = new JLabel("TPS");
        lblTP.setBounds(30, 140, 100, 25);
        panel.add(lblTP);

        comboTP = new JComboBox<>(new String[]{"Aprobado", "Desaprobado"});
        comboTP.setBounds(100, 140, 150, 25);
        panel.add(comboTP);
		
	}
}
