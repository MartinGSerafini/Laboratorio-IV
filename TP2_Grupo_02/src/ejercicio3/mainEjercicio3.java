package ejercicio3;

import java.util.ArrayList;
import java.util.ListIterator;

import ejercicio1.Profesor;

public class mainEjercicio3 {

	public static void main(String[] args) {
		 ArrayList<Edificio> Lista = new ArrayList<Edificio>();
		 Lista.add(new PoliDeportivo("Polideportivo Tigre", 1500.6, 1));
		 Lista.add(new PoliDeportivo("Polideportivo Pacheco", 1100.7, 2));
		 Lista.add(new PoliDeportivo("Polideportivo El Talar", 1300.8, 3));
		 Lista.add(new EdificioDeOficinas(50, 30, 1));
		 Lista.add(new EdificioDeOficinas(65, 40, 2));
		 
		 ListIterator<Edificio> iterator = Lista.listIterator();
		 while (iterator.hasNext()) {
			Edificio edificio = (Edificio) iterator.next();
			System.out.println(edificio.toString());
		}
	}
}
