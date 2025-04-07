package ejercicio2;

public class mainEjercicio2 {

	public static void main(String[] args) {
		
		ProductoCongelado prodCong = new ProductoCongelado("Brocoli", 123, "01/01/2026", -10);
		ProductoFresco prodFresco = new ProductoFresco("Carne de cerdo", 345, "1/05/2025", "1/03/2025", "Argentina");
		ProductoRefrigerado prodRefri = new ProductoRefrigerado("Yogurt", 567, "1/06/2025", 13);
		
		System.out.println(prodCong);
		System.out.println(prodFresco);
		System.out.println(prodRefri);
		
	}

}
