import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Http {
    Moneda api(String moneda1, String moneda2, double cantidad) {
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/b858cdbb94b4b10cc084e285/pair/" + moneda1 + "/" + moneda2 + "/" + cantidad);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(direccion).build();
        HttpResponse<String> response;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new Gson().fromJson(response.body(), Moneda.class);
    }
}