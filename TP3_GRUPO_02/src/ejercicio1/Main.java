package ejercicio1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		Archivo archivo = new Archivo();
		
		if (!archivo.existe()) {
            System.out.println("El archivo no existe: ");
            return;
        }
		
		Set<Persona> personasSet = new HashSet<>();
        try {
            // Abrir el archivo
            BufferedReader reader = new BufferedReader(new FileReader(archivo.getRuta()));
            String linea;
            
            while ((linea = reader.readLine()) != null) {
                // Separar los datos por el guion
                String[] partes = linea.split("-");
                
                if (partes.length == 3) {
                    String nombre = partes[0].trim();
                    String apellido = partes[1].trim();
                    String dni = partes[2].trim();

                    // Validar que el DNI sea válido
                    try {
                        VerificadorDni.verificarDniInvalido(dni);
                        personasSet.add(new Persona(nombre, apellido, dni));
                    } catch (DniInvalidoException e) {
                    }
                }
            }
            reader.close();

            // Convertir el set en una lista para ordenarla
            List<Persona> listaOrdenada = new ArrayList<>(personasSet);
            listaOrdenada.sort(Persona.ordenarPorApellido);

            // Mostrar la lista ordenada sin duplicados
            for (Persona persona : listaOrdenada) {
                System.out.println(persona);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        
	}
}
