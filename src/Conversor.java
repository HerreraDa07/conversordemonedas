import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Conversor {
    List<String> historial = new ArrayList<>();

    public void convertir() {
        Scanner teclado = new Scanner(System.in);
        Http http = new Http();
        Map<Integer, String[]> conversiones = new HashMap<>();
        conversiones.put(1, new String[]{"USD", "BRL"});
        conversiones.put(2, new String[]{"BRL", "USD"});
        conversiones.put(3, new String[]{"USD", "MXN"});
        conversiones.put(4, new String[]{"MXN", "USD"});
        conversiones.put(5, new String[]{"USD", "COP"});
        conversiones.put(6, new String[]{"COP", "USD"});
        conversiones.put(7, new String[]{"USD", "ARS"});
        conversiones.put(8, new String[]{"ARS", "USD"});
        conversiones.put(9, new String[]{"USD", "BOB"});
        conversiones.put(10, new String[]{"BOB", "USD"});
        conversiones.put(11, new String[]{"USD", "CLP"});
        conversiones.put(12, new String[]{"CLP", "USD"});
        while (true) {
            try {
                System.out.println("""
                        Bienvenido al conversor de monedas
                        1. Dólar a real brasileño
                        2. Real brasileño a dólar
                        3. Dólar a peso mexicano
                        4. Peso mexicano a dólar
                        5. Dólar a peso colombiano
                        6. Peso colombiano a dólar
                        7. Dólar a peso argentino
                        8. Peso argentino a dólar
                        9. Dólar a boliviano
                        10. Boliviano a dólar
                        11. Dólar a peso chileno
                        12. Peso chileno a dólar
                        13. Para visualizar el historial
                        14. Para finalizar el programa
                        ** Elige una opción para continuar **
                        """);
                int opcion = teclado.nextInt();
                if (opcion > 0 && opcion < 13) {
                    System.out.println("Digite el valor que desea convertir.");
                    double cantidad;
                    try {
                        cantidad = teclado.nextDouble();
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Debe ingresar un número válido para hacer la conversión.");
                        teclado.nextLine();
                        continue;
                    }
                    String[] conversion = conversiones.get(opcion);
                    Moneda moneda = http.api(conversion[0], conversion[1], cantidad);
                    System.out.println(moneda);
                    LocalDateTime tiempo = LocalDateTime.now();
                    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                    historial.add("Moneda de cambio: " + conversion[0] + " a " + "moneda a cambiar: " + conversion[1] + "\n" + "Valor de la moneda de cambio: " + cantidad + " valor de la conversión: " + moneda.conversion_result() + "\n" + "Fecha y hora que se realizó la conversión: " + tiempo.format(formato) + "\n");
                } else if (opcion == 13) {
                    System.out.println("Historial de las conversiones de monedas que ha realizado:\n");
                    for (String datos : historial) {
                        System.out.println(datos);
                    }
                } else if (opcion == 14) {
                    System.out.println("El programa va a finalizar.");
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    try (FileWriter escritura = new FileWriter("historial.json")) {
                        escritura.write(gson.toJson(historial));
                    } catch (IOException e) {
                        System.out.println("Error al guardar el historial");
                    }
                    teclado.close();
                    break;
                } else {
                    System.out.println("La opción que escogió no es válida en el menú.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Opción no válida. Debe ingresar un número entero.");
                teclado.nextLine();
            }
        }
    }
}