package presentacion.vista;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.BorderLayout;
import javax.swing.JTextField;

public class PanelModificar extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelModificar() {
		setLayout(null);
		
		JLabel lblSeleccionarPersona = new JLabel("Seleccione la persona que desea modificar");
		lblSeleccionarPersona.setBounds(109, 11, 222, 14);
		add(lblSeleccionarPersona);

	}

}
