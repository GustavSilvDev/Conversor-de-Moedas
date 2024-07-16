import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExchangerateApi {
    private static final String API_KEY = "dc3d27eab57877ef2d07d16c";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";
    private final HttpClient client;
    private final Gson gson;

    public ExchangerateApi() {
        this.client = HttpClient.newHttpClient();
        this.gson = new Gson();
    }

    public Conversor ObtemMoeda(String baseCode, String targetCode) throws IOException, InterruptedException {
        String endpoint = String.format("%s%s/pair/%s/%s", BASE_URL, API_KEY, baseCode, targetCode);
        URI uri = URI.create(endpoint);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return gson.fromJson(response.body(), Conversor.class);
        } catch (IOException | InterruptedException e) {
            throw e; // Propaga a exceção para o chamador lidar
        } catch (JsonSyntaxException e) {
            System.out.println("Erro ao processar a resposta JSON: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }
        return null;
    }
}
