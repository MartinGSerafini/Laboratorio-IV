package Ejercicio;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

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
	
	public static void ordenarListaAlfabeticamente() {
		//Copia los elementos del modelo a una lista temporal
		ArrayList<Peliculas> copia = new ArrayList<>();
		for (int i = 0; i < dlModel.size(); i++) {
			copia.add(dlModel.getElementAt(i));
		}
		//Ordena la lista temporal alfabéticamente
		copia.sort((p1, p2) -> p1.getNombre().compareToIgnoreCase(p2.getNombre()));

		// Limpia el modelo de la lista y lo reemplaza con la lista ordenada
		dlModel.clear();
		for (Peliculas p : copia) {
		    dlModel.addElement(p);
		}
	}
	
	public static boolean verificarRepetidos(String pelicula) {
		//pelicula.toLowerCase();
		for (int i = 0; i < dlModel.size(); i++) {
			Peliculas peliculas = dlModel.getElementAt(i);
			if(peliculas.getNombre().toLowerCase().equals(pelicula.toLowerCase())) {
				return true;
			}
		}
		return false;
	}
	
	public static JList ListadoPeliculas() {
		return listaPeliculas;
	}
}


