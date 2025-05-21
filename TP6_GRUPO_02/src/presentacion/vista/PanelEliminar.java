package presentacion.vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JButton;

public class PanelEliminar extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelEliminar() {
		setLayout(null);
		
		JLabel lblEliminarUsuarios = new JLabel("Eliminar usuarios");
		lblEliminarUsuarios.setBounds(113, 30, 117, 14);
		add(lblEliminarUsuarios);
		
		JList JListPersonas = new JList();
		JListPersonas.setBounds(113, 55, 189, 162);
		add(JListPersonas);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(162, 228, 89, 23);
		add(btnEliminar);

	}
}
