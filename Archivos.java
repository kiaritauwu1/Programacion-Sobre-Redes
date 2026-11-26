import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Utilidades de archivos.
 *  - Escritura: SOLO PrintWriter.
 *  - Lectura: SOLO BufferedReader.
 */
public class Archivos {

    /** Lee todas las líneas de un archivo con BufferedReader. */
    public static List<String> leerLineas(File archivo) throws IOException {
        List<String> lineas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo, StandardCharsets.UTF_8))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.add(linea);
            }
        }
        return lineas;
    }

    /** Sobrescribe el archivo con las líneas dadas, usando PrintWriter. */
    public static void escribirLineas(File archivo, List<String> lineas) throws IOException {
        Rutas.crearCarpeta(archivo);
        try (PrintWriter pw = new PrintWriter(archivo, "UTF-8")) {
            for (String linea : lineas) {
                pw.println(linea);
            }
        }
    }
}
