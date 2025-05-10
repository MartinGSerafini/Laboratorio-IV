package Ejercicio;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;

public class gestorPeliculas {
	
	private static JList listaPeliculas;
	private static DefaultListModel<Peliculas> dlModel;
	private static int idActual = 1;
	
	static {
	    dlModel = new DefaultListModel<Peliculas>();
	    listaPeliculas = new JList();
	    listaPeliculas.setModel(dlModel);
	}
	
	public static int getIdActual() {
	    return idActual;
	}
	public static void incrementarID() {
	    idActual++;
	}

	public static void agregarPelicula(Peliculas p) {
	    dlModel.addElement(p);
	}
	
	public static JList ListadoPeliculas() {
		return listaPeliculas;
	}
}


