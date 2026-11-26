import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Ejercicio 4: leer "números.txt" y mostrar sus valores por consola.
 */
public class Ejercicio4 {

    public static void main(String[] args) throws IOException {
        if (!Rutas.NUMEROS.exists()) {
            Consola.println("No existe " + Rutas.NUMEROS.getAbsolutePath() + ". Ejecutá primero el Ejercicio 3.");
            return;
        }

        Consola.println("Contenido de " + Rutas.NUMEROS.getName() + ":");
        try (BufferedReader br = new BufferedReader(new FileReader(Rutas.NUMEROS, StandardCharsets.UTF_8))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                Consola.println(linea);
            }
        }
    }
}
