import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Ejercicio 7: "caracteres.dat" (fuera del proyecto) con 10 palabras que tengan 'ñ',
 * cargadas por consola. Se muestran, se reemplaza la 'ñ' por su fonético
 * (ña→nia, ñe→nie, ñi→nii, ño→nio, ñu→niu, o sea ñ→"ni") y se vuelven a mostrar.
 */
public class Ejercicio7 {

    private static final int CANTIDAD = 10;
    private static final String FONETICO = "ni";   // niño → ninio, año → anio

    public static void main(String[] args) throws IOException {
        Rutas.crearCarpeta(Rutas.CARACTERES);

        // 1) Cargar las 10 palabras desde consola y guardarlas
        try (PrintWriter pw = new PrintWriter(Rutas.CARACTERES, "UTF-8")) {
            int cargadas = 0;
            while (cargadas < CANTIDAD) {
                String palabra = Consola.pedir("Palabra " + (cargadas + 1) + " con 'ñ': ");
                if (palabra == null) {
                    Consola.println("Se terminó la entrada antes de cargar las 10 palabras.");
                    return;
                }
                if (palabra.isEmpty() || palabra.contains(" ")) {
                    Consola.println("  Tiene que ser una sola palabra.");
                } else if (palabra.indexOf('ñ') < 0 && palabra.indexOf('Ñ') < 0) {
                    Consola.println("  La palabra no tiene 'ñ'. Probá de nuevo.");
                } else {
                    pw.println(palabra);
                    cargadas++;
                }
            }
        }

        // 2) Mostrar el fichero original
        Consola.println();
        Consola.println("Fichero original: ");
        mostrar();

        // 3) Editar el fichero reemplazando la ñ
        List<String> arregladas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(Rutas.CARACTERES, StandardCharsets.UTF_8))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                arregladas.add(linea.replace("ñ", FONETICO).replace("Ñ", FONETICO.toUpperCase()));
            }
        }
        try (PrintWriter pw = new PrintWriter(Rutas.CARACTERES, "UTF-8")) {
            for (String palabra : arregladas) {
                pw.println(palabra);
            }
        }

        // 4) Mostrar el fichero arreglado
        Consola.println();
        Consola.println("Fichero arreglado:");
        mostrar();
    }

    private static void mostrar() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(Rutas.CARACTERES, StandardCharsets.UTF_8))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                Consola.println("  " + linea);
            }
        }
    }
}
