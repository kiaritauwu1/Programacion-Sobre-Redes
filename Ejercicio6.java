import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * Ejercicio 6: tomar "números.txt" (sin impares) y copiar a "primos.dat"
 * (en otra carpeta, fuera del proyecto y distinta a la de "números.txt")
 * todos los números que sean primos.
 */
public class Ejercicio6 {

    public static void main(String[] args) throws IOException {
        if (!Rutas.NUMEROS.exists()) {
            Consola.println("No existe " + Rutas.NUMEROS.getAbsolutePath() + ". Ejecutá primero el Ejercicio 3.");
            return;
        }
        Rutas.crearCarpeta(Rutas.PRIMOS);

        int encontrados = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(Rutas.NUMEROS, StandardCharsets.UTF_8));
             PrintWriter pw = new PrintWriter(Rutas.PRIMOS, "UTF-8")) {

            String linea;
            while ((linea = br.readLine()) != null) {
                String valor = linea.trim();
                if (!Consola.esEntero(valor)) {
                    continue;
                }
                int numero = Integer.parseInt(valor);
                if (numero % 2 != 0) {
                    continue;   // el enunciado pide trabajar con los impares eliminados
                }
                if (esPrimo(numero)) {
                    pw.println(numero);
                    encontrados++;
                    Consola.println("Primo encontrado: " + numero);
                }
            }
        }

        Consola.println("Se guardaron " + encontrados + " primos en: " + Rutas.PRIMOS.getAbsolutePath());
    }

    public static boolean esPrimo(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
