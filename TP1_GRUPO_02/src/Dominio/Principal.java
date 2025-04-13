package Dominio;

public class Principal {

    public static void main(String[] args) {
        
        Empleado emp1 = new Empleado();
        System.out.println(emp1.toString());
        
        Empleado emp2 = new Empleado("Patricio", 36);
        System.out.println(emp2.toString());
        
        Empleado emp3 = new Empleado("Maria", 35);
        System.out.println(emp3.toString());
        
        Empleado emp4 = new Empleado();
        System.out.println(emp4.toString());
        
        Empleado emp5 = new Empleado("Renee", 29);
        System.out.println(emp5.toString());
        
        System.out.println("El próximo ID será el " + Empleado.devuelveProximoID());
    }
}
