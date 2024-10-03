import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class Weather {

    // Your OpenWeatherMap API key
    private static final String API_KEY = "125e438227dd5fb05aeb8303ecef5e1d"; // Replace with your actual API key

    public static void main(String[] args) {
        String city = "Moscow";  // City name is set to Beirut
        String urlString = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&units=metric&appid=" + API_KEY;

        try {
            // Create URL object
            URL url = new URL(urlString);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Read the response
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();

            // Parse the JSON response
            JSONObject jsonResponse = new JSONObject(result.toString());
            JSONObject main = jsonResponse.getJSONObject("main");
            double temperature = main.getDouble("temp");  // Temperature in Celsius
            int humidity = main.getInt("humidity");       // Humidity

            String weatherDescription = jsonResponse.getJSONArray("weather").getJSONObject(0).getString("description");

            // Print the weather information
            System.out.println("City: " + city);
            System.out.println("Temperature: " + String.format("%.2f", temperature) + " °C");
            System.out.println("Humidity: " + humidity + "%");
            System.out.println("Weather: " + weatherDescription);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
