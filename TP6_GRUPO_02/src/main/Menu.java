package main;

import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame {
	private static final long serialVersionUID = 1L;
    public Menu() {
        setTitle("TP6 - Registro de Personas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        
        setLayout(new FlowLayout(FlowLayout.LEFT));

        JButton btnPersonas = new JButton("Personas");
        add(btnPersonas);

        JPopupMenu popupMenu = new JPopupMenu();

        JMenuItem agregar = new JMenuItem("Agregar");
        JMenuItem modificar = new JMenuItem("Modificar");
        JMenuItem eliminar = new JMenuItem("Eliminar");
        JMenuItem listar = new JMenuItem("Listar");

        popupMenu.add(agregar);
        popupMenu.add(modificar);
        popupMenu.add(eliminar);
        popupMenu.add(listar);

        btnPersonas.addActionListener(e -> {
            popupMenu.show(btnPersonas, 0, btnPersonas.getHeight());
        });

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Menu().setVisible(true);
        });
    }
}