package ejercicio2;

public class ProductoCongelado extends Producto{
		//Atributos
	    private float temperaturaRecomendada;
		
	    //Constructores
	    public ProductoCongelado() {
			super();
			this.temperaturaRecomendada = -1;
		}
		public ProductoCongelado(String nombre, int numeroLote, String fechaCaducidad, float temperaturaRecomendada) {
			super(nombre, numeroLote, fechaCaducidad);
			this.temperaturaRecomendada = temperaturaRecomendada;
		}
	    
		
		// Getters and Setters
		public float getTemperaturaRecomendada() {
			return temperaturaRecomendada;
		}
		public void setTemperaturaRecomendada(float temperaturaRecomendada) {
			this.temperaturaRecomendada = temperaturaRecomendada;
		}
		
		
		//ToString
		@Override
		public String toString() {
			return super.toString() +" Temperatura de congelación recomendada: " + temperaturaRecomendada;
		}
		
		
}
