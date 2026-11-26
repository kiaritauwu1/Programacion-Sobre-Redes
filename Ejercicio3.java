import java.io.IOException;
import java.io.PrintWriter;

/**
 * Ejercicio 3: archivo "números.txt" (fuera de la carpeta del proyecto)
 * con los números pares del 0 al 1000, uno por renglón.
 */
public class Ejercicio3 {

    public static void main(String[] args) throws IOException {
        Rutas.crearCarpeta(Rutas.NUMEROS);

        try (PrintWriter pw = new PrintWriter(Rutas.NUMEROS, "UTF-8")) {
            for (int i = 0; i <= 1000; i += 2) {
                pw.println(i);
            }
        }

        Consola.println("Archivo creado: " + Rutas.NUMEROS.getAbsolutePath());
    }
}
