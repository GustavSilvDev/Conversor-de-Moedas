import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        ExchangerateApi exchangerateApi = new ExchangerateApi();
        HistoricoConversoes historicoConversoes = new HistoricoConversoes();
        int option;

        do {
            printMenu();
            option = getOption(scanner);

            switch (option) {
                case 1, 2, 3, 4, 5, 6 -> handleConversion(option, scanner, exchangerateApi, historicoConversoes);
                case 7 -> historicoConversoes.mostrarHistorico();
                case 8 -> System.out.println("Programa finalizado!");
                default -> System.out.println("Opção inválida. Por favor, selecione uma opção válida.");
            }
        } while (option != 8);
    }

    private static void printMenu() {
        System.out.println("""
                ****************************************************
                | Bem-vindo ao conversor de moedas:                |
                |                                                  |
                | 1) Dolar => Real                                 |
                | 2) Real => Dolar                                 |
                | 3) Dolar => Euro                                 |
                | 4) Euro => Dolar                                 |
                | 5) Dolar => Libra esterlina                      |
                | 6) Libra esterlina => Dolar                      |
                | 7) Mostrar histórico                             |
                | 8) Sair                                          |
                |                                                  |
                ****************************************************
                """);
    }

    private static int getOption(Scanner scanner) {
        System.out.print("Selecione uma opção: ");
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Opção inválida. Por favor, digite um número entre 1 e 8.");
            scanner.next();  // Limpar a entrada inválida
            return 0;
        }
    }

    private static double getValue(Scanner scanner, String message) {
        System.out.print(message);
        try {
            return scanner.nextDouble();
        } catch (InputMismatchException e) {
            System.out.println("Digite somente números!");
            scanner.next();  // Limpar a entrada inválida
            return -1;
        }
    }

    private static void handleConversion(int option, Scanner scanner, ExchangerateApi exchangerateApi, HistoricoConversoes historicoConversoes) throws IOException, InterruptedException {
        double value = getValue(scanner, "Digite o valor que irá converter: ");
        if (value == -1) return;

        String resultado = processOption(option, value, exchangerateApi);
        if (resultado != null) {
            historicoConversoes.adicionarConversao(resultado);
        }
    }

    private static String processOption(int option, double value, ExchangerateApi exchangerateApi) throws IOException, InterruptedException {
        String fromCurrency = null;
        String toCurrency = null;

        switch (option) {
            case 1 -> { fromCurrency = "USD"; toCurrency = "BRL"; }
            case 2 -> { fromCurrency = "BRL"; toCurrency = "USD"; }
            case 3 -> { fromCurrency = "USD"; toCurrency = "EUR"; }
            case 4 -> { fromCurrency = "EUR"; toCurrency = "USD"; }
            case 5 -> { fromCurrency = "USD"; toCurrency = "GBP"; }
            case 6 -> { fromCurrency = "GBP"; toCurrency = "USD"; }
        }

        if (fromCurrency != null && toCurrency != null) {
            Conversor conversor = exchangerateApi.ObtemMoeda(fromCurrency, toCurrency);
            double convertedValue = conversor.conversion_rate() * value;
            String resultado = String.format("Valor %.2f [%s] Corresponde a ==> %.2f [%s]", value, fromCurrency, convertedValue, toCurrency);
            System.out.println(resultado);
            return resultado;
        }
        return null;
    }
}
