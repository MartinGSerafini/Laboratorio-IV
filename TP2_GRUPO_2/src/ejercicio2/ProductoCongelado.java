package ejercicio2;

public class ProductoCongelado extends Producto{
	//Atributos
		private String tipoProducto;
	    private int mesesAntesCaducidad;
	    private float temperaturaRecomendada;
	    
	    //Constructores
	    
	    //	Vacio
	    public ProductoCongelado() {
			super();
			this.tipoProducto = "Sin Ingresar";
			this.mesesAntesCaducidad = 0;
			this.temperaturaRecomendada = 0;
		}
	    //	Con parametros
		public ProductoCongelado(String nombre, String tipoProducto, int mesesAntesCaducidad, float temperaturaRecomendada) {
			super(nombre);
			this.tipoProducto = tipoProducto;
			this.mesesAntesCaducidad = mesesAntesCaducidad;
			this.temperaturaRecomendada = temperaturaRecomendada;
		}
		
		// Getters and Setters
		public String getTipoProducto() {
			return tipoProducto;
		}
		public void setTipoProducto(String tipoProducto) {
			this.tipoProducto = tipoProducto;
		}
		public int getMesesAntesCaducidad() {
			return mesesAntesCaducidad;
		}
		public void setMesesAntesCaducidad(int mesesAntesCaducidad) {
			this.mesesAntesCaducidad = mesesAntesCaducidad;
		}
		public float getTemperaturaRecomendada() {
			return temperaturaRecomendada;
		}
		public void setTemperaturaRecomendada(float temperaturaRecomendada) {
			this.temperaturaRecomendada = temperaturaRecomendada;
		}
		
		
		
}
