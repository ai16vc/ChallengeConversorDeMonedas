import com.google.gson.Gson;
import io.github.cdimascio.dotenv.Dotenv;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
import java.net.URI;



public class Principal {
    public static void main(String[] args)  throws Exception {

        Dotenv dotenv = Dotenv.load();

        // Variables
        String monedaOrigen = "";
        String monedaDestino = "";
        double valorInicial = 0;
        int opcionElegidaParaLaConversion = 0;

        while (true) {
            String mensajeTotalConversion = "";

            // Mensajes
            String mensajeDeBienvenida = "Bienvenido/a al conversor de monedas.";
            String mensajeOpcionesDeCambio =
                    """
                            1- Convertir ARS a USD
                            2- Convertir USD a ARS
                            3- Convertir € a USD
                            4- Convertir USD a €
                            5- Convertir MXN a USD
                            6- Convertir USD a MXN
                            7- Finalizar Operación.""";

            String mensajeElegirOpcionDeCambio = "Ingrese el número de al operación que desea realizar";
            String mensajeOperacionFinalizada = "Ha finalizado la operación.";
            String mensajeError = "Opción no válida.";
            String apiKey = dotenv.get("API_KEY");

            // Impresiones
            System.out.println(mensajeDeBienvenida);
            System.out.println(mensajeOpcionesDeCambio);
            System.out.println(mensajeElegirOpcionDeCambio);


            //Scanners
            Scanner eligeOpcion = new Scanner(System.in);
            boolean opcionValida = false;

            while (!opcionValida) {
                try {
                    opcionElegidaParaLaConversion = eligeOpcion.nextInt();

                    if (opcionElegidaParaLaConversion >= 1 && opcionElegidaParaLaConversion <= 7) {
                        opcionValida = true;
                    } else {
                        System.out.println(mensajeError);
                        System.out.println(mensajeElegirOpcionDeCambio);
                    }

                } catch (Exception e) {
                    System.out.println(mensajeError);
                    System.out.println(mensajeElegirOpcionDeCambio);
                    eligeOpcion.next();
                }
            }

            if (opcionElegidaParaLaConversion == 1) {
                monedaOrigen = "ARS";
                monedaDestino = "USD";
            } else if (opcionElegidaParaLaConversion == 2) {
                monedaOrigen = "USD";
                monedaDestino = "ARS";
            } else if (opcionElegidaParaLaConversion == 3) {
                monedaOrigen = "EUR";
                monedaDestino = "USD";
            } else if (opcionElegidaParaLaConversion == 4) {
                monedaOrigen = "USD";
                monedaDestino = "EUR";
            } else if (opcionElegidaParaLaConversion == 5) {
                monedaOrigen = "MXN";
                monedaDestino = "USD";
            } else if (opcionElegidaParaLaConversion == 6) {
                monedaOrigen = "USD";
                monedaDestino = "MXN";
            } else {
                System.out.println(mensajeOperacionFinalizada);
                break;
            }

            System.out.println("Ingrese el valor a convertir:");
            valorInicial = eligeOpcion.nextDouble();

            String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/"
                    + monedaOrigen + "/" + monedaDestino;

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


            Gson gson = new Gson();
            RespuestaAPI datos = gson.fromJson(response.body(), RespuestaAPI.class);

            double conversion = valorInicial * datos.conversion_rate;

            mensajeTotalConversion = "El valor " + valorInicial + " " + monedaOrigen +
                    " corresponde a " + conversion + " " + monedaDestino;
            System.out.println(mensajeTotalConversion);
        }
    }
}