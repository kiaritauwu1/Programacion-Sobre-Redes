import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Ejercicio 9: menú para cargar datos de clima identificados por fecha,
 * mostrarlos todos y borrar un registro del archivo.
 *
 * Formato de cada renglón de "clima.txt":
 *   fecha;temperatura;humedad;descripcion
 *   ej: 25/11/2026;28.5;60;Soleado
 */
public class Ejercicio9 {

    private static final String SEP = ";";
    private static final DateTimeFormatter FORMATO_FECHA =
            DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);

    public static void main(String[] args) throws IOException {
        String opcion;
        do {
            Consola.println();
            Consola.println("===== REGISTRO DE CLIMA =====");
            Consola.println("1. Cargar datos de clima");
            Consola.println("2. Mostrar todos los datos");
            Consola.println("3. Borrar un registro");
            Consola.println("0. Salir");
            opcion = Consola.pedir("Opción: ");
            if (opcion == null) {
                break;
            }
            switch (opcion) {
                case "1": cargar(); break;
                case "2": mostrar(); break;
                case "3": borrar(); break;
                case "0": Consola.println("¡Hasta luego!"); break;
                default:  Consola.println("Opción inválida.");
            }
        } while (!"0".equals(opcion));
    }

    // ---------- Opciones del menú ----------

    private static void cargar() throws IOException {
        List<String> registros = leerRegistros();

        String fecha = pedirFecha();
        if (fecha == null) return;
        if (buscarPorFecha(registros, fecha) >= 0) {
            Consola.println("Ya hay un registro para el " + fecha + ". Borralo primero si querés reemplazarlo.");
            return;
        }

        String temperatura = pedirNumero("Temperatura (°C): ", -90, 60);
        if (temperatura == null) return;
        String humedad = pedirNumero("Humedad (%): ", 0, 100);
        if (humedad == null) return;
        String descripcion = Consola.pedir("Descripción (ej: Soleado, Lluvioso): ");
        if (descripcion == null) return;
        descripcion = descripcion.replace(SEP, ",");   // el ';' es el separador del archivo
        if (descripcion.isEmpty()) descripcion = "-";

        registros.add(fecha + SEP + temperatura + SEP + humedad + SEP + descripcion);
        guardarOrdenado(registros);
        Consola.println("Registro guardado.");
    }

    private static void mostrar() throws IOException {
        List<String> registros = leerRegistros();
        if (registros.isEmpty()) {
            Consola.println("No hay datos cargados.");
            return;
        }
        Consola.printf("%-12s %12s %10s   %s%n", "FECHA", "TEMP (°C)", "HUMEDAD", "DESCRIPCIÓN");
        Consola.println("--------------------------------------------------------");
        for (String registro : registros) {
            String[] c = registro.split(SEP, -1);
            if (c.length == 4) {
                Consola.printf("%-12s %12s %9s%%   %s%n", c[0], c[1], c[2], c[3]);
            }
        }
        Consola.println("Total: " + registros.size() + " registros.");
    }

    private static void borrar() throws IOException {
        List<String> registros = leerRegistros();
        if (registros.isEmpty()) {
            Consola.println("No hay datos para borrar.");
            return;
        }
        String fecha = pedirFecha();
        if (fecha == null) return;

        int posicion = buscarPorFecha(registros, fecha);
        if (posicion < 0) {
            Consola.println("No existe un registro con fecha " + fecha + ".");
            return;
        }
        Consola.println("Registro encontrado: " + registros.get(posicion).replace(SEP, " | "));
        String confirma = Consola.pedir("¿Seguro que lo querés borrar? (S/N): ");
        if (confirma != null && confirma.equalsIgnoreCase("S")) {
            registros.remove(posicion);
            Archivos.escribirLineas(Rutas.CLIMA, registros);
            Consola.println("Registro borrado del archivo.");
        } else {
            Consola.println("No se borró nada.");
        }
    }

    // ---------- Archivo ----------

    private static List<String> leerRegistros() throws IOException {
        List<String> registros = new ArrayList<>();
        if (!Rutas.CLIMA.exists()) {
            return registros;
        }
        for (String linea : Archivos.leerLineas(Rutas.CLIMA)) {
            if (!linea.trim().isEmpty()) {
                registros.add(linea);
            }
        }
        return registros;
    }

    private static void guardarOrdenado(List<String> registros) throws IOException {
        registros.sort(Comparator.comparing(r -> LocalDate.parse(r.split(SEP)[0], FORMATO_FECHA)));
        Archivos.escribirLineas(Rutas.CLIMA, registros);
    }

    private static int buscarPorFecha(List<String> registros, String fecha) {
        for (int i = 0; i < registros.size(); i++) {
            if (registros.get(i).startsWith(fecha + SEP)) {
                return i;
            }
        }
        return -1;
    }

    // ---------- Validaciones de entrada ----------

    private static String pedirFecha() throws IOException {
        while (true) {
            String fecha = Consola.pedir("Fecha (dd/mm/aaaa): ");
            if (fecha == null) return null;
            try {
                LocalDate.parse(fecha, FORMATO_FECHA);
                return fecha;
            } catch (DateTimeParseException e) {
                Consola.println("  Fecha inválida. Ejemplo válido: 25/11/2026");
            }
        }
    }

    private static String pedirNumero(String mensaje, double minimo, double maximo) throws IOException {
        while (true) {
            String texto = Consola.pedir(mensaje);
            if (texto == null) return null;
            texto = texto.replace(',', '.');
            if (Consola.esNumero(texto)) {
                double valor = Double.parseDouble(texto);
                if (valor >= minimo && valor <= maximo) {
                    return texto;
                }
            }
            Consola.println("  Ingresá un número entre " + (int) minimo + " y " + (int) maximo + ".");
        }
    }
}
