import java.io.File;

/**
 * Ubicación de todos los archivos del TP.
 * La carpeta del proyecto es la carpeta desde donde se ejecuta el programa
 * (en NetBeans / Eclipse / IntelliJ es la raíz del proyecto).
 */
public class Rutas {

    // Carpeta del proyecto y la carpeta que la contiene
    public static final File PROYECTO = new File(System.getProperty("user.dir")).getAbsoluteFile();
    public static final File FUERA_DEL_PROYECTO = PROYECTO.getParentFile();

    // --- Dentro de la carpeta del proyecto ---
    public static final File ULTIMO_DATO = new File(PROYECTO, "ultimo_dato.txt");          // Ej. 1
    public static final File NUMEROS_INGRESADOS = new File(PROYECTO, "numeros_ingresados.txt"); // Ej. 2
    public static final File HTML_LOREM = new File(PROYECTO, "lorem.html");               // Ej. 8 (hecho a mano)
    public static final File CLIMA = new File(PROYECTO, "clima.txt");                     // Ej. 9

    // --- Fuera de la carpeta del proyecto ---
    public static final File CARPETA_NUMEROS = new File(FUERA_DEL_PROYECTO, "ArchivosNumeros");
    public static final File NUMEROS = new File(CARPETA_NUMEROS, "números.txt");          // Ej. 3, 4, 5, 6

    // Otra carpeta distinta: ni la del proyecto ni la de "números.txt"
    public static final File CARPETA_PRIMOS = new File(FUERA_DEL_PROYECTO, "ArchivosPrimos");
    public static final File PRIMOS = new File(CARPETA_PRIMOS, "primos.dat");             // Ej. 6

    public static final File CARPETA_CARACTERES = new File(FUERA_DEL_PROYECTO, "ArchivosCaracteres");
    public static final File CARACTERES = new File(CARPETA_CARACTERES, "caracteres.dat"); // Ej. 7

    /** Crea la carpeta que contiene al archivo, si todavía no existe. */
    public static void crearCarpeta(File archivo) {
        File carpeta = archivo.getParentFile();
        if (carpeta != null && !carpeta.exists()) {
            carpeta.mkdirs();
        }
    }
}
