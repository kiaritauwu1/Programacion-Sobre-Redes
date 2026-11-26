import java.io.IOException;
import java.io.PrintWriter;

/**
 * Ejercicio 2: archivo (en la carpeta del proyecto) que guarda TODOS los valores
 * NUMÉRICOS que ingresa el usuario, uno por renglón. Lo que no es número se ignora.
 */
public class Ejercicio2 {

    public static void main(String[] args) throws IOException {
        Consola.println("Ingresá datos (números u otra cosa). Escribí FIN para terminar:");

        int guardados = 0;
        try (PrintWriter pw = new PrintWriter(Rutas.NUMEROS_INGRESADOS, "UTF-8")) {
            String dato = Consola.pedir("> ");
            while (dato != null && !dato.equalsIgnoreCase("FIN")) {
                if (Consola.esNumero(dato)) {
                    pw.println(dato);
                    pw.flush();
                    guardados++;
                } else if (!dato.isEmpty()) {
                    Consola.println("  \"" + dato + "\" no es un número, no se guarda.");
                }
                dato = Consola.pedir("> ");
            }
        }

        Consola.println("Se guardaron " + guardados + " números en: "
                + Rutas.NUMEROS_INGRESADOS.getAbsolutePath());
    }
}
