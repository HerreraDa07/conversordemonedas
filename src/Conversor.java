import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Conversor {
    private int opcion = 0;

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
        while (opcion != 9) {
            System.out.println("""
                    Bienvenido al conversor de monedas
                    1. Dolar a Real Brasileño
                    2. Real Brasileño a Dolar
                    3. Dolar a Peso Mexicano
                    4. Peso Mexicano a Dolar
                    5. Dolar a Peso Colombiano
                    6. Peso Colombiano a Dolar
                    7. Dolar a Peso Argentino
                    8. Peso Argentino a Dolar
                    9. Para Finalizar el programa
                    ** Elige una opcion para continuar **
                    """);
            opcion = teclado.nextInt();
            if (opcion > 0 && opcion < 9) {
                System.out.println("Digite el valor que desea convertir");
                double cantidad = teclado.nextDouble();
                String[] conversion = conversiones.get(opcion);
                Moneda moneda = http.api(conversion[0], conversion[1], cantidad);
                System.out.println(moneda);
            } else if (opcion == 9) {
                System.out.println("El programa va a finalizar");
                break;
            } else {
                System.out.println("Opcion no valida");
                break;
            }
        }
    }
}