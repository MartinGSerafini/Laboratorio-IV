package Ejercicio;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;

public class gestorPeliculas {
	
	private static ArrayList<Peliculas> listaPeliculas = new ArrayList<>();
	private static int idActual = 1;
	
	public static int getIdActual() {
	    return idActual;
	}
	public static void incrementarID() {
	    idActual++;
	}

	public static void agregarPelicula(Peliculas p) {
	    listaPeliculas.add(p);
	}
}


