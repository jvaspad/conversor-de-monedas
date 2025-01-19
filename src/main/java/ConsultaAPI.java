import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ConsultaAPI {
    public double obtenerTasaDeCambio(String moneda1, String moneda2) throws Exception {
        String URL = "https://v6.exchangerate-api.com/v6/ae5f0479080724f07dda7fb3/latest/" + moneda1;

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();
            JsonObject tasas = jsonObject.getAsJsonObject("conversion_rates");

            return tasas.get(moneda2).getAsDouble();
        } else {
            throw new Exception("Error al obtener las tasas de cambio: " + response.statusCode());
        }
    }
}
