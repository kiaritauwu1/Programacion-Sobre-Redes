import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * Ejercicio 8: abrir "lorem.html" (hecho a mano, en la carpeta del proyecto)
 * y borrar todo el texto "Lorem ipsum..." que tenga.
 *
 * Algoritmo: se busca cada aparición de "lorem ipsum" (sin importar mayúsculas)
 * y se borra desde ahí hasta el próximo '<' (donde empieza la etiqueta siguiente).
 * Así se elimina el texto aunque ocupe varios renglones, y las etiquetas quedan intactas.
 */
public class Ejercicio8 {

    private static final String INICIO_LOREM = "lorem ipsum";

    public static void main(String[] args) throws IOException {
        if (!Rutas.HTML_LOREM.exists()) {
            Consola.println("No existe " + Rutas.HTML_LOREM.getAbsolutePath());
            return;
        }

        // 1) Leer todo el HTML
        StringBuilder html = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(Rutas.HTML_LOREM, StandardCharsets.UTF_8))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                html.append(linea).append('\n');
            }
        }

        // 2) Borrar cada bloque de lorem
        int borrados = 0;
        int inicio = html.toString().toLowerCase().indexOf(INICIO_LOREM);
        while (inicio >= 0) {
            int fin = html.indexOf("<", inicio);
            if (fin < 0) {
                fin = html.length();
            }
            html.delete(inicio, fin);
            borrados++;
            inicio = html.toString().toLowerCase().indexOf(INICIO_LOREM, inicio);
        }

        // 3) Guardar el HTML sin lorem
        try (PrintWriter pw = new PrintWriter(Rutas.HTML_LOREM, "UTF-8")) {
            pw.print(html);
        }

        Consola.println("Se borraron " + borrados + " bloques de lorem de " + Rutas.HTML_LOREM.getName());
    }
}
