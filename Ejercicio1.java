import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Ejercicio 1: archivo (en la carpeta del proyecto) que guarda SOLO el último
 * dato que el usuario escribe por consola.
 * Cada dato nuevo sobrescribe el archivo, así siempre queda el último.
 */
public class Ejercicio1 {

    public static void main(String[] args) throws IOException {
        File archivo = Rutas.ULTIMO_DATO;
        Consola.println("Escribí los datos que quieras (línea vacía para terminar):");

        String dato = Consola.pedir("> ");
        while (dato != null && !dato.isEmpty()) {
            // PrintWriter sin modo "agregar": borra el contenido anterior
            try (PrintWriter pw = new PrintWriter(archivo, "UTF-8")) {
                pw.println(dato);
            }
            dato = Consola.pedir("> ");
        }

        if (archivo.exists()) {
            Consola.println("Último dato guardado en: " + archivo.getAbsolutePath());
        } else {
            Consola.println("No se ingresó ningún dato.");
        }
    }
}
