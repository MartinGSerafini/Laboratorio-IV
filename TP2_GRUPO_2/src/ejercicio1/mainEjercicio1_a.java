package ejercicio1;

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
        
        System.out.println("El próximo ID será el " + Empleado.devuelveProximoID());
	}

}
