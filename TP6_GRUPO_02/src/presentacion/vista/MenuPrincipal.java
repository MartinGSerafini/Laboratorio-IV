package presentacion.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

public class MenuPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnPersonas;
	private JMenuItem menuEliminar;
	private JMenuItem menuAgregar;
	private JMenuItem menuModificar;
	private JMenuItem menuListar;
	
	

	public MenuPrincipal() {
		setTitle("TP6 - Registro de Personas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 350);
        setLocationRelativeTo(null);
        
        setLayout(new FlowLayout(FlowLayout.LEFT));

        menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnPersonas = new JMenu("Personas");
		menuBar.add(mnPersonas);
		
		menuAgregar = new JMenuItem("Agregar");
		mnPersonas.add(menuAgregar);
		
		menuModificar = new JMenuItem("Modificar");
		mnPersonas.add(menuModificar);
		
		menuEliminar = new JMenuItem("Eliminar");
		mnPersonas.add(menuEliminar);
		
		menuListar = new JMenuItem("Listar");
		mnPersonas.add(menuListar);
        
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);
	}

	public JMenu getMnPersonas() {
		return mnPersonas;
	}
	public void setMnPersonas(JMenu mnPersonas) {
		this.mnPersonas = mnPersonas;
	}

	
	public JMenuItem getMenuEliminar() {
		return menuEliminar;
	}
	public void setMenuEliminar(JMenuItem menuEliminar) {
		this.menuEliminar = menuEliminar;
	}

	
	public JMenuItem getMenuAgregar() {
		return menuAgregar;
	}
	public void setMenuAgregar(JMenuItem menuAgregar) {
		this.menuAgregar = menuAgregar;
	}

	
	public JMenuItem getMenuModificar() {
		return menuModificar;
	}
	public void setMenuModificar(JMenuItem menuModificar) {
		this.menuModificar = menuModificar;
	}


	public JMenuItem getMenuListar() {
		return menuListar;
	}
	public void setMenuListar(JMenuItem menuListar) {
		this.menuListar = menuListar;
	}

}
