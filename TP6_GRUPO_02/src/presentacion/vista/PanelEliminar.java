package presentacion.vista;

import javax.swing.JPanel;
import javax.swing.JLabel;

public class PanelEliminar extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelEliminar() {
		setLayout(null);
		
		JLabel lblEliminarUsuarios = new JLabel("Eliminar usuarios");
		lblEliminarUsuarios.setBounds(143, 23, 117, 14);
		add(lblEliminarUsuarios);

	}
}
