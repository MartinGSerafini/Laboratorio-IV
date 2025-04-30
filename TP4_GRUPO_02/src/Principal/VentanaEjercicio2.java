package Principal;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

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
		
		setTitle("Promedio");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setBounds(100, 100, 450, 300);

        JPanel contentPane = new JPanel(new BorderLayout(10, 10));
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);

        //Panel de ingreso de datos
        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.BLUE),
            "Notas del estudiante",
            TitledBorder.LEFT,
            TitledBorder.TOP
        ));

        // Fila 1
        panel.add(new JLabel("Nota 1:"));
        textField1 = new JTextField(12);
        panel.add(textField1);

        // Fila 2
        panel.add(new JLabel("Nota 2:"));
        textField2 = new JTextField(12);
        panel.add(textField2);

        // Fila 3
        panel.add(new JLabel("Nota 3:"));
        textField3 = new JTextField(12);
        panel.add(textField3);

        // Fila 4
        panel.add(new JLabel("TPS:"));
        comboTP = new JComboBox<>(new String[]{"Aprobado", "Desaprobado"});
        panel.add(comboTP);

        contentPane.add(panel, BorderLayout.CENTER);

        //panel con los botones
        JPanel panelBotones = new JPanel(new GridLayout(3, 1, 0, 10));
        panelBotones.setBorder(new EmptyBorder(10, 0, 10, 0));

        JButton btnCalcular = new JButton("Calcular");
        JButton btnNuevo = new JButton("Nuevo");
        JButton btnSalir = new JButton("Salir");
        panelBotones.add(btnCalcular);
        panelBotones.add(btnNuevo);
        panelBotones.add(btnSalir);

        contentPane.add(panelBotones, BorderLayout.EAST);

        // Ajustamos al tamaño óptimo
        pack();
		
	}
}