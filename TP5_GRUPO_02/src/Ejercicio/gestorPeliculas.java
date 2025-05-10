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
	
	public static void ordenarListaAlfabeticamente() {
		// Paso 1: Copiar los elementos del modelo original a una lista temporal
		ArrayList<Peliculas> copia = new ArrayList<>();
		for (int i = 0; i < dlModel.size(); i++) {
		copia.add(dlModel.getElementAt(i));
		}
		// Paso 2: Ordenar la lista temporal por nombre
		copia.sort((p1, p2) -> p1.getNombre().compareToIgnoreCase(p2.getNombre()));

		// Paso 3: Limpiar el modelo original y volver a agregar los elementos ordenados
		dlModel.clear();
		for (Peliculas p : copia) {
		    dlModel.addElement(p);
		}
	}
	
	public static JList ListadoPeliculas() {
		return listaPeliculas;
	}
}


