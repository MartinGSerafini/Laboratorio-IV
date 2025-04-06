package ejercicio1;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

public class mainEjercicio1_b {

	public static void main(String[] args) {
		 TreeSet<Profesor> profesoresSet = new TreeSet<>(new Comparator<Profesor>() {
	            public int compare(Profesor p1, Profesor p2) {
	                return p1.getNombre().compareTo(p2.getNombre());
	            }
	        });

	        profesoresSet.add(new Profesor("Gustavo Sanches", 43, "Profesor de Historia", 10));
	        profesoresSet.add(new Profesor("Silvia Nu�ez", 56, "Profesora de Literatura", 6));
	        profesoresSet.add(new Profesor("Cristina Villanueva", 48, "Profesora de Matem�ticas", 11));
	        profesoresSet.add(new Profesor("Hern�n Luna", 28, "Profesor de Educaci�n F�sica", 3));
	        profesoresSet.add(new Profesor("Gustavo Sanches", 43, "Profesor de Historia", 10));

	        Iterator<Profesor> it = profesoresSet.iterator();
	        while (it.hasNext()) {
	            System.out.println(it.next());
	        }
	}

}
