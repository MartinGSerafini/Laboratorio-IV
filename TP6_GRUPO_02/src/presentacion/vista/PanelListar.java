package presentacion.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import javax.swing.JPanel;
import entidad.Persona;
import negocio.PersonaNegocio;
import negocioImpl.PersonaNegocioImpl;

import java.awt.*;
import java.util.List;

public class PanelListar extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTable tabla;
    private DefaultTableModel modeloTabla;

    private PersonaNegocio personaNegocio;

    public PanelListar() {
        setLayout(new BorderLayout());

        personaNegocio = new PersonaNegocioImpl();

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("DNI");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Apellido");

        tabla = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tabla);

        this.add(scrollPane, BorderLayout.CENTER);

        cargarDatos();

        //this.setLocationRelativeTo(null);
    }

    private void cargarDatos() {
        List<Persona> lista = personaNegocio.obtenerTodas();

        modeloTabla.setRowCount(0);

        // Agrego cada persona a la tabla
        for (Persona p : lista) {
            Object[] fila = { p.getDni(), p.getNombre(), p.getApellido() };
            modeloTabla.addRow(fila);
        }
    }
}