import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.nio.charset.Charset;

/**
 * Entrada y salida por consola.
 *  - Lectura: SOLO con los métodos de la clase Reader (read()).
 *  - Escritura: SOLO con PrintStream (System.out).
 */
public class Consola {

    private static final Reader ENTRADA = new InputStreamReader(System.in, charsetConsola());
    private static final PrintStream SALIDA = System.out;

    /** Intenta usar la codificación real de la consola para que la 'ñ' llegue bien. */
    private static Charset charsetConsola() {
        String[] propiedades = {"stdin.encoding", "sun.stdout.encoding"};
        for (String p : propiedades) {
            String valor = System.getProperty(p);
            if (valor != null) {
                try {
                    return Charset.forName(valor);
                } catch (IllegalArgumentException e) {
                    // si no se reconoce, se prueba con la siguiente
                }
            }
        }
        return Charset.defaultCharset();
    }

    /** Lee una línea carácter por carácter con Reader.read(). Devuelve null si se terminó la entrada. */
    public static String leerLinea() throws IOException {
        StringBuilder linea = new StringBuilder();
        int c = ENTRADA.read();
        if (c == -1) {
            return null;
        }
        while (c != -1 && c != '\n') {
            if (c != '\r') {
                linea.append((char) c);
            }
            c = ENTRADA.read();
        }
        return linea.toString();
    }

    /** Muestra un mensaje y lee la respuesta (sin espacios sobrantes). */
    public static String pedir(String mensaje) throws IOException {
        SALIDA.print(mensaje);
        SALIDA.flush();
        String linea = leerLinea();
        return linea == null ? null : linea.trim();
    }

    public static void println(String texto) {
        SALIDA.println(texto);
    }

    public static void println() {
        SALIDA.println();
    }

    public static void print(String texto) {
        SALIDA.print(texto);
    }

    public static void printf(String formato, Object... args) {
        SALIDA.printf(formato, args);
    }

    /** true si el texto es un número entero o decimal (ej: 5, -12, 3.5, 3,5). */
    public static boolean esNumero(String texto) {
        if (texto == null || texto.isEmpty()) {
            return false;
        }
        int i = 0;
        if (texto.charAt(0) == '-' || texto.charAt(0) == '+') {
            i = 1;
        }
        boolean hayDigito = false;
        boolean haySeparador = false;
        for (; i < texto.length(); i++) {
            char c = texto.charAt(i);
            if (c >= '0' && c <= '9') {
                hayDigito = true;
            } else if ((c == '.' || c == ',') && !haySeparador) {
                haySeparador = true;
            } else {
                return false;
            }
        }
        return hayDigito;
    }

    /** true si el texto es un número entero. */
    public static boolean esEntero(String texto) {
        return esNumero(texto) && texto.indexOf('.') < 0 && texto.indexOf(',') < 0;
    }
}
