package ejercicio1;

<<<<<<< HEAD
public class mainEjercicio1_a {

	public static void main(String[] args) {
		Profesor emp1 = new Profesor();
        System.out.println(emp1.toString());
        
        Profesor emp2 = new Profesor("Patricio", 36, "Principal", 2);
        System.out.println(emp2.toString());
        
        Profesor emp3 = new Profesor("Maria", 35, "Asistente", 1);
        System.out.println(emp3.toString());
        
        Profesor emp4 = new Profesor();
        System.out.println(emp4.toString());
        
        Profesor emp5 = new Profesor("Renee", 29, "Jefe de Trabajos", 3);
        System.out.println(emp5.toString());
        
        System.out.println("El prÛximo ID ser· el " + Empleado.devuelveProximoID());
=======
import java.util.ArrayList;
import java.util.ListIterator;

public class mainEjercicio1_a {

	public static void main(String[] args) {
		
		//creen 5 profesores y se guarden dentro de un arraylist. Mostrar la informaci√≥n utilizando un iterador.
		ArrayList<Profesor>listaProfesores = new ArrayList <Profesor>(5);
		
		listaProfesores.add(new Profesor("Gustavo Sanchez", 43, "Profesor de Historia", 10));
		listaProfesores.add(new Profesor("Silvia Nu√±ez", 56, "Profesora de Literatura", 6));
		listaProfesores.add(new Profesor("Cristina Villanueva", 48, "Profesora de Matematicas", 11));
		listaProfesores.add(new Profesor("Hernan Luna", 28, "Profesor de Educacion fisica", 3));
		listaProfesores.add(new Profesor("Miriam Pereira", 60, "Profesora de Ingles", 35));
		
		ListIterator<Profesor> it = listaProfesores.listIterator();
		while (it.hasNext()) {
			Profesor profesor = it.next();
			System.out.println(profesor.toString());
		}
>>>>>>> 6b41eebf0ff235e5ebf36f8975fa44e0d89b914a
	}

}
