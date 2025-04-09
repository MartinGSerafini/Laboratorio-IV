package ejercicio3;

public class EdificioDeOficinas extends  Edificio{

	private int numOficinas;
	
	public EdificioDeOficinas(double superficie, int numOficinas) {
		super(superficie);
		this.numOficinas = numOficinas;
	}
	//gets y sets
	public int getNumOficinas() {
		return numOficinas;
	}
	public int setNumOficinas(int numOficinas) {
		this.numOficinas = numOficinas;
		return numOficinas;
	}
	//to string
	@Override
	public String toString() {
		return "EdificioDeOficinas [numero de oficinas =" + numOficinas +  "]";
	}
}
