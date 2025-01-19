import java.util.Scanner;

public class ConversorDeMonedas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConsultaAPI consultaAPI = new ConsultaAPI();
        int opcion;

        do {
            System.out.println("********************************************************************************");
            System.out.println("¡Bienvenid@ a mi conversor de monedas! Elije una de las siguientes opciones:\n");
            System.out.println("1) Dólar estadounidense (USD) >>> peso argentino (ARS)");
            System.out.println("2) Dólar estadounidense (USD) >>> real brasileño (BRL)");
            System.out.println("3) Peso argentino (ARS) >>> dólar estadounidense (USD)");
            System.out.println("4) Peso argentino (ARS) >>> real brasileño (BRL)");
            System.out.println("5) Real brasileño (BRL) >>> dólar estadounidense (USD)");
            System.out.println("6) Real brasileño (BRL) >>> peso argentino (ARS)");
            System.out.println("7) Salir");
            System.out.println("********************************************************************************");

            opcion = scanner.nextInt();

            if (opcion >= 1 && opcion <= 6) {
                System.out.print("Ingresa el valor que quieres convertir: ");
                double cantidad = scanner.nextDouble();
                Conversion(opcion, cantidad, consultaAPI);
            } else if (opcion != 7) {
                System.out.println("Opción inválida. Intenta de nuevo.");
            }
        } while (opcion != 7);

        System.out.println("¡Gracias por usar mi conversor de monedas! ¡Hasta luego!");
        scanner.close();
    }

    private static void Conversion(int opcion, double cantidad, ConsultaAPI API) {
        String moneda1 = "";
        String moneda2 = "";

        switch (opcion) {
            case 1:
                moneda1 = "USD";
                moneda2 = "ARS";
                break;
            case 2:
                moneda1 = "USD";
                moneda2 = "BRL";
                break;
            case 3:
                moneda1 = "ARS";
                moneda2 = "USD";
                break;
            case 4:
                moneda1 = "ARS";
                moneda2 = "BRL";
                break;
            case 5:
                moneda1 = "BRL";
                moneda2 = "USD";
                break;
            case 6:
                moneda1 = "BRL";
                moneda2 = "ARS";
                break;
        }

        try {
            double tasaDeCambio = API.obtenerTasaDeCambio(moneda1, moneda2);

            double valorConvertido = cantidad * tasaDeCambio;

            System.out.printf("\nEl valor de %.2f %s corresponde al valor final de %.2f %s.\n\n",
                    cantidad, moneda1, valorConvertido, moneda2);
        } catch (Exception e) {
            System.out.println("Error al realizar la conversión: " + e.getMessage());
        }
    }
}
