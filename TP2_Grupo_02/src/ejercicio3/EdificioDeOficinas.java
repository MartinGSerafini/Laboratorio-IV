package ejercicio3;

public class EdificioDeOficinas extends  Edificio{

	private int numOficinas;
	private int blocEdificio;
	
	public EdificioDeOficinas(double superficie, int numOficinas, int blocEdificio) {
		super(superficie);
		this.numOficinas = numOficinas;
		this.blocEdificio = blocEdificio;
	}
	//gets y sets
	public int getNumOficinas() {
		return numOficinas;
	}
	public int setNumOficinas(int numOficinas) {
		this.numOficinas = numOficinas;
		return numOficinas;
	}
	@Override
	public String toString() {
		return "Edificio de oficinas. Bloque: "+ blocEdificio + ", Numero de oficinas: "+ numOficinas;
	}
}
