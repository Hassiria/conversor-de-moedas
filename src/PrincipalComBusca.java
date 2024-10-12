import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class PrincipalComBusca {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner leitura = new Scanner(System.in);
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://v6.exchangerate-api.com/v6/73c9b0ea3e69ccee09c025b3/latest/USD"))
                .build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();
        JsonObject taxasDeCambio = jsonResponse.getAsJsonObject("conversion_rates");

        System.out.println("\n");
        System.out.println("*************** Seja bem-vindo ao Conversor de Moedas ***************");

        int opcao;
        do {

            System.out.println("Escolha uma opção válida:");
            System.out.println("1) Dólar -> Peso argentino (ARS)");
            System.out.println("2) Peso argentino -> Dólar (USD)");
            System.out.println("3) Dólar -> Real brasileiro (BRL)");
            System.out.println("4) Real brasileiro -> Dólar (USD)");
            System.out.println("5) Dólar -> Peso colombiano (COP)");
            System.out.println("6) Peso colombiano -> Dólar (USD)");
            System.out.println("7) Sair");
            opcao = leitura.nextInt();

            if (opcao >= 1 && opcao <= 6) {
                System.out.println("Digite o valor que deseja converter: ");
                double valor = leitura.nextDouble();
                double resultado = 0.0;

                switch (opcao) {
                    case 1:
                        resultado = valor * taxasDeCambio.get("ARS").getAsDouble();
                        System.out.printf("Valor convertido: %.2f ARS%n", resultado);
                        break;
                    case 2:
                        resultado = valor / taxasDeCambio.get("ARS").getAsDouble();
                        System.out.printf("Valor convertido: %.2f USD%n", resultado);
                        break;
                    case 3:
                        resultado = valor * taxasDeCambio.get("BRL").getAsDouble();
                        System.out.printf("Valor convertido: %.2f BRL%n", resultado);
                        break;
                    case 4:
                        resultado = valor / taxasDeCambio.get("BRL").getAsDouble();
                        System.out.printf("Valor convertido: %.2f USD%n", resultado);
                        break;
                    case 5:
                        resultado = valor * taxasDeCambio.get("COP").getAsDouble();
                        System.out.printf("Valor convertido: %.2f COP%n", resultado);
                        break;
                    case 6:
                        resultado = valor / taxasDeCambio.get("COP").getAsDouble();
                        System.out.printf("Valor convertido: %.2f USD%n", resultado);
                        break;
                }
            } else if (opcao != 7) {
                System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 7);

        System.out.println("Obrigado por usar o Conversor de Moedas!");
        leitura.close();
    }
}
