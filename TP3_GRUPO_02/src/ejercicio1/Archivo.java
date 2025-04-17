package ejercicio1;

import java.io.File;

public class Archivo {

	//String ruta = "\\TP3_GRUPO_02\\src\\Personas.txt";
	String ruta = "src\\Personas.txt";
	
	public boolean existe()
	{
		File archivo = new File(ruta); 
		if(archivo.exists())
		      return true;
		return false;
	}

	public String getRuta() {
		return ruta;
	}
	
	
}
