package presentacion.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import entidad.Persona;
import negocio.PersonaNegocio;
import negocioImpl.PersonaNegocioImpl;

import java.awt.*;
import java.util.List;

public class VentanaListar extends JFrame {
    private JTable tabla;
    private DefaultTableModel modeloTabla;

    private PersonaNegocio personaNegocio;

    public VentanaListar() {
        super("Listado de Personas");

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(500, 300);
        this.setLayout(new BorderLayout());

        personaNegocio = new PersonaNegocioImpl();

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("DNI");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Apellido");

        tabla = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tabla);

        this.add(scrollPane, BorderLayout.CENTER);

        cargarDatos();

        this.setLocationRelativeTo(null);
        this.setVisible(true);
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