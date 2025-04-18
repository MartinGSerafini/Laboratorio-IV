package ejercicio1;

import java.io.File;

public class Archivo {

	//String ruta = "\\TP3_GRUPO_02\\src\\Personas.txt";
	private String ruta; 
	
	//constructores
	public Archivo() {
		ruta = "src\\Personas.txt";		
	}
	
	public Archivo(String ruta){		
	       this.ruta = ruta;
	}
	
	//setters y getters
	public void setRuta(String ruta){
        this.ruta = ruta;
	}
	
	public String getRuta() {
		return ruta;
	}
	//metodos
	public boolean existe()
	{
		File archivo = new File(ruta); 
		if(archivo.exists())
		      return true;
		return false;
	}

	
	
	
}
