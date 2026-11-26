import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Ejercicio 5: borrar de "números.txt" todos los renglones que contengan
 * números múltiplos de 3.
 * Se lee el archivo con BufferedReader, se filtra y se vuelve a escribir con PrintWriter.
 */
public class Ejercicio5 {

    public static void main(String[] args) throws IOException {
        if (!Rutas.NUMEROS.exists()) {
            Consola.println("No existe " + Rutas.NUMEROS.getAbsolutePath() + ". Ejecutá primero el Ejercicio 3.");
            return;
        }

        List<String> lineas = Archivos.leerLineas(Rutas.NUMEROS);
        List<String> quedan = new ArrayList<>();
        int borrados = 0;

        for (String linea : lineas) {
            String valor = linea.trim();
            if (Consola.esEntero(valor) && Integer.parseInt(valor) % 3 == 0) {
                borrados++;           // múltiplo de 3 (el 0 también lo es): se borra
            } else {
                quedan.add(linea);
            }
        }

        Archivos.escribirLineas(Rutas.NUMEROS, quedan);
        Consola.println("Se borraron " + borrados + " renglones múltiplos de 3. Quedaron " + quedan.size() + ".");
    }
}
