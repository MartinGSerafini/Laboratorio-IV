package Principal;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaEjercicio3 extends JFrame {
	private JPanel contentPane;
	private JTextField txtHoras;
	private JRadioButton rbWindows, rbMac, rbLinux;
	private JCheckBox cbProgramacion, cbAdministracion, cbDisenio;
	
	public VentanaEjercicio3() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelGeneral = new JPanel();
		panelGeneral.setLayout(new BoxLayout(panelGeneral, BoxLayout.Y_AXIS));
        contentPane.add(panelGeneral, BorderLayout.CENTER);
        
		
		setTitle("Seleccion Multiple");
		
		//Primer Panel Sistema Operativo
		JPanel panelSO = new JPanel();
		panelSO.setBorder(BorderFactory.createTitledBorder("")); // Borde vac�o, como en la imagen
		panelSO.setLayout(new FlowLayout(FlowLayout.LEFT));
		panelSO.add(new JLabel("Elije un sistema operativo"));
		rbWindows = new JRadioButton("Windows");
		rbMac = new JRadioButton("Mac");
		rbLinux = new JRadioButton("Linux");
        ButtonGroup bgSO = new ButtonGroup();
        bgSO.add(rbWindows);
        bgSO.add(rbMac);
        bgSO.add(rbLinux);
        panelSO.add(rbWindows);
        panelSO.add(rbMac);
        panelSO.add(rbLinux);
        panelGeneral.add(panelSO);
        panelGeneral.add(Box.createVerticalStrut(10)); //agrego un espacio para el siguiente panel
        
        
        //segundo panel especialidad
        JPanel panelEsp = new JPanel();
        panelEsp.setBorder(BorderFactory.createTitledBorder("")); // Borde vac�o, como en la imagen
        panelEsp.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST; // Alinear a la izquierda
        JLabel lblEspecialidad = new JLabel("Elije una especialidad");
        panelEsp.add(lblEspecialidad, gbc);
        
        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new GridLayout(3, 1, 5, 5));
        cbProgramacion = new JCheckBox("Programación");
        cbAdministracion = new JCheckBox("Administración");
        cbDisenio = new JCheckBox("Diseño Gráfico");
        checkBoxPanel.add(cbProgramacion);
        checkBoxPanel.add(cbAdministracion);
        checkBoxPanel.add(cbDisenio);
        GridBagConstraints gbc1 = new GridBagConstraints();

        gbc1.gridx = 1;
        gbc1.gridy = 0;
        gbc1.anchor = GridBagConstraints.WEST; // Alinear al lado izquierdo dentro de su celda
        panelEsp.add(checkBoxPanel, gbc1);
        
        panelGeneral.add(panelEsp);
        panelGeneral.add(Box.createVerticalStrut(10)); //agrego un espacio para el siguiente panel
        
        
        // TERCER PANEL CANTIDAD HORAS
        JPanel panelHoras = new JPanel();
        panelHoras.setBorder(BorderFactory.createTitledBorder("")); // Borde vacio, como en la imagen
		panelHoras.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        JLabel lblCantHoras = new JLabel("Cantidad de horas en el computador: ");
        panelHoras.add(lblCantHoras);
        
        txtHoras = new JTextField();
        txtHoras.setColumns(10);
        panelHoras.add(txtHoras);
        
        panelGeneral.add(panelHoras);
        
        JPanel panelBoton = new JPanel();
        contentPane.add(panelBoton, BorderLayout.SOUTH);
        
        JButton btnAceptar = new JButton("Aceptar\r\n");
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            	String sistemaOperativo = "";
                if (rbWindows.isSelected()) {
                    sistemaOperativo = "Windows";
                } else if (rbMac.isSelected()) {
                    sistemaOperativo = "Mac";
                } else if (rbLinux.isSelected()) {
                    sistemaOperativo = "Linux";
                }
                
                String especialidad = "";
                if (cbProgramacion.isSelected()) {
                    especialidad = "Programación";
                } else if (cbAdministracion.isSelected()) {
                    especialidad = "Administración";
                } else if (cbDisenio.isSelected()) {
                    especialidad = "Diseño Gráfico";
                }
                
                eventoBotonAceptar evento = new eventoBotonAceptar(sistemaOperativo, especialidad, txtHoras);
                evento.actionPerformed(e);  // Llama al método del evento con la información actualizada
            }
        });
        panelBoton.add(btnAceptar);
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


class eventoBotonAceptar implements ActionListener{

	private String sistemaOp;
	private String especialidad;
	private JTextField cantHoras;
	
	eventoBotonAceptar(String sistema, String espec, JTextField cantHor){
		sistemaOp = sistema;
		especialidad = espec;
		cantHoras = cantHor;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	    String horas = cantHoras.getText();
	    String mensaje = sistemaOp + " - " + especialidad + " - " + horas + " Hs";

	    JOptionPane.showOptionDialog(
	            null,
	            mensaje,
	            "Mensaje",
	            JOptionPane.DEFAULT_OPTION,
	            JOptionPane.INFORMATION_MESSAGE,
	            null,
	            new Object[] { "Aceptar" }, 
	            "Aceptar"
	        );
	}
}

