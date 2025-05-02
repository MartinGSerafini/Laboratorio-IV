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

public class VentanaEjercicio2 extends JFrame {
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
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
        textField1 = new JTextField(12);
        panelNotas.add(textField1);

        panelNotas.add(new JLabel("Nota 2:"));
        textField2 = new JTextField(12);
        panelNotas.add(textField2);

        panelNotas.add(new JLabel("Nota 3:"));
        textField3 = new JTextField(12);
        panelNotas.add(textField3);

        panelNotas.add(new JLabel("TPS:"));
        comboTP = new JComboBox<>(new String[]{"Aprobado", "Desaprobado"});
        panelNotas.add(comboTP);

        // Segundo panel Promedio y Condición
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

        panelResultado.add(new JLabel("Condición:"));
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
        JButton btnNuevo = new JButton("Nuevo");
        JButton btnSalir = new JButton("Salir");
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