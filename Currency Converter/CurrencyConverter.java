import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Scanner;

public class CurrencyConverter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input currencies and amount
        System.out.print("Enter base currency (e.g., USD): ");
        String baseCurrency = scanner.next().toUpperCase();

        System.out.print("Enter target currency (e.g., INR): ");
        String targetCurrency = scanner.next().toUpperCase();

        System.out.print("Enter amount to convert: ");
        double amount = scanner.nextDouble();
        scanner.close();
        try {
            double exchangeRate = getExchangeRate(baseCurrency, targetCurrency);
            if (exchangeRate == -1) {
                System.out.println("Could not fetch exchange rate.");
                return;
            }

            double converted = amount * exchangeRate;
            System.out.printf("Converted amount: %.2f %s%n", converted, targetCurrency);

        } catch (IOException e) {
            System.out.println("Error fetching data: " + e.getMessage());
        }
    }

    private static double getExchangeRate(String base, String target) throws IOException {
        String apiKey = "6bfe811b4d4a5c80313033e5"; // 👈 Your actual API key
        String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + base;

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();

        if (!response.isSuccessful()) {
            System.out.println("HTTP error: " + response.code());
            return -1;
        }

        String jsonData = response.body().string();
        JSONObject json = new JSONObject(jsonData);

        if (!json.getString("result").equals("success")) {
            System.out.println("API error: " + json.optString("error-type", "unknown"));
            return -1;
        }

        JSONObject rates = json.getJSONObject("conversion_rates");

        if (!rates.has(target)) {
            System.out.println("Invalid target currency.");
            return -1;
        }

        return rates.getDouble(target);
    }
}
