package ejercicio1;

import java.io.File;

public class Archivo {

	String ruta;
	
	public boolean existe()
	{
		File archivo = new File(ruta); 
		if(archivo.exists())
		      return true;
		return false;
	}
}
