package ejercicio1;

import java.util.ArrayList;
import java.util.ListIterator;

public class mainEjercicio1_a {

	public static void main(String[] args) {
		
		//creen 5 profesores y se guarden dentro de un arraylist. Mostrar la información utilizando un iterador.
		ArrayList<Profesor>listaProfesores = new ArrayList <Profesor>(5);
		
		listaProfesores.add(new Profesor("Gustavo Sanchez", 43, "Profesor de Historia", 10));
		listaProfesores.add(new Profesor("Silvia Nuñez", 56, "Profesora de Literatura", 6));
		listaProfesores.add(new Profesor("Cristina Villanueva", 48, "Profesora de Matematicas", 11));
		listaProfesores.add(new Profesor("Hernan Luna", 28, "Profesor de Educacion fisica", 3));
		listaProfesores.add(new Profesor("Miriam Pereira", 60, "Profesora de Ingles", 35));
		
		ListIterator<Profesor> it = listaProfesores.listIterator();
		while (it.hasNext()) {
			Profesor profesor = it.next();
			System.out.println(profesor.toString());
		}
	}

}
