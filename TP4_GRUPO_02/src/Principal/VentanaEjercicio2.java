package Principal;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VentanaEjercicio2 extends JFrame {
    private JTextField txtNota1;
    private JTextField txtNota2;
    private JTextField txtNota3;
    private JTextField textPromedio;
    private JTextField textCondicion;
    private JComboBox<String> comboTP;

    public VentanaEjercicio2() {

        setTitle("Promedio");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setBounds(100, 100, 450, 280);

        JPanel contentPane = new JPanel(new BorderLayout(10, 10));
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);

        // Primer panel de ingreso de datos
        JPanel panelNotas = new JPanel(new GridLayout(4, 2, 5, 5));
        panelNotas.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.BLUE),
            "Notas del estudiante",
            TitledBorder.LEFT,
            TitledBorder.TOP
        ));

        panelNotas.add(new JLabel("Nota 1:"));
        txtNota1 = new JTextField(12);
        panelNotas.add(txtNota1);

        panelNotas.add(new JLabel("Nota 2:"));
        txtNota2 = new JTextField(12);
        panelNotas.add(txtNota2);

        panelNotas.add(new JLabel("Nota 3:"));
        txtNota3 = new JTextField(12);
        panelNotas.add(txtNota3);

        panelNotas.add(new JLabel("TPS:"));
        comboTP = new JComboBox<>(new String[]{"Aprobado", "Desaprobado"});
        panelNotas.add(comboTP);

        // Segundo panel Promedio y Condici�n
        JPanel panelResultado = new JPanel(new GridLayout(2, 2, 5, 2));
        panelResultado.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.BLUE),
            "Resultado",
            TitledBorder.LEFT,
            TitledBorder.TOP
        ));

        panelResultado.add(new JLabel("Promedio:"));
        textPromedio = new JTextField(12);
        textPromedio.setPreferredSize(new Dimension(150, 25));
        panelResultado.add(textPromedio);

        panelResultado.add(new JLabel("Condici�n:"));
        textCondicion = new JTextField(12);
        textCondicion.setPreferredSize(new Dimension(150, 25));
        panelResultado.add(textCondicion);

        // Panel que agrupa los dos paneles con BoxLayout
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));

        panelNotas.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelNotas.setMaximumSize(new Dimension(Integer.MAX_VALUE, panelNotas.getPreferredSize().height));

        panelResultado.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelResultado.setMaximumSize(new Dimension(Integer.MAX_VALUE, panelResultado.getPreferredSize().height));

        panelCentral.add(panelNotas);
        panelCentral.add(Box.createVerticalStrut(10));
        panelCentral.add(panelResultado);

        contentPane.add(panelCentral, BorderLayout.CENTER);

        // Panel con los botones
        JPanel panelBotones = new JPanel(new GridLayout(6, 1, 0, 10));
        panelBotones.setBorder(new EmptyBorder(10, 0, 10, 0));

        JButton btnCalcular = new JButton("Calcular");
        btnCalcular.addActionListener(new eventoBotonCalcular(txtNota1, txtNota2, txtNota3, comboTP, textPromedio, textCondicion));
        JButton btnNuevo = new JButton("Nuevo");
        btnNuevo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { //limpiar los campos al clickear "nuevo"
                txtNota1.setText("");
                txtNota2.setText("");
                txtNota3.setText("");
                textPromedio.setText("");
                textCondicion.setText("");
                comboTP.setSelectedIndex(0); 
            }
        });
        JButton btnSalir = new JButton("Salir");
        btnSalir.addActionListener(new ActionListener() { //dispose para cerrar la ventana
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
        panelBotones.add(btnCalcular);
        panelBotones.add(btnNuevo);
        panelBotones.add(btnSalir);

        contentPane.add(panelBotones, BorderLayout.EAST);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            try {
                VentanaEjercicio2 frame = new VentanaEjercicio2();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}


//botón Calcular para calcular el promedio y condición
class eventoBotonCalcular implements ActionListener{
	private JTextField jtNota1;
	private JTextField jtNota2;
	private JTextField jtNota3;
	private JComboBox<String> JCtps;
	private JTextField jtPromedio;
	private JTextField jtCondicion;

	eventoBotonCalcular(JTextField nota1, JTextField nota2, JTextField nota3, JComboBox<String> tps, JTextField promedio, JTextField condicion){
		jtNota1 = nota1;
		jtNota2 = nota2;
		jtNota3 = nota3;
		JCtps = tps;
		jtPromedio = promedio;
		jtCondicion = condicion;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		double promedio = 0;
		String prom;
		
		double n1 = Double.parseDouble(jtNota1.getText());
		double n2 = Double.parseDouble(jtNota2.getText());
		double n3 = Double.parseDouble(jtNota3.getText());
		
		//Promedio
		if(verificarJTxt(jtNota1) && verificarJTxt(jtNota2) && verificarJTxt(jtNota3)) {
			if(verificarNota(n1) && verificarNota(n2) && verificarNota(n3)) {
				promedio = (n1 + n2 + n3) / 3;
			}
		}
		prom = String.format("%.2f", promedio);
		jtPromedio.setText(prom);
		
		//Condición
		if(JCtps.getSelectedItem().toString() == "Desaprobado") {
			jtCondicion.setText("Libre");
		}
		else if(n1 < 6 || n2 < 6 || n3 < 6) {
			jtCondicion.setText("Libre");
		}
		else if(n1 >= 8 && n2 >= 8 && n3 >= 8 && JCtps.getSelectedItem().toString() == "Aprobado") {
			jtCondicion.setText("Promociona");
		}
		else if(n1 <= 8 && n1 >= 6 && n2 <= 8 && n2 >= 6 && n3 <= 8 && n3 >= 6 &&JCtps.getSelectedItem().toString() == "Aprobado") {
			jtCondicion.setText("Regular");
		}
	}
	
	public boolean verificarJTxt(JTextField txt) {
		if(!txt.getText().trim().isEmpty() && txt.getText().matches("\\d+(\\.\\d+)?")) {
			return true;
		}
		return false;
	}
	
	public boolean verificarNota(double nota) {
		if(nota >= 1 && nota <= 10) {
			return true;
		}
		return false;
	}
}