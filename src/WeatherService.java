import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class WeatherService {

    public static WeatherData getWeather(double latitude, double longitude) throws IOException {
        String urlString = String.format(
                "https://api.open-meteo.com/v1/forecast?latitude=%f&longitude=%f&current_weather=true&temperature_unit=fahrenheit&wind_speed_unit=mph&precipitation_unit=inch",
                latitude, longitude);

            JSONObject currentWeather = getJsonObject(urlString);
                double temperature = currentWeather.getDouble("temperature");
                int isDayOrNight = currentWeather.getInt("is_day");
                double windDirection = currentWeather.getDouble("winddirection");
                double windSpeed = currentWeather.getDouble("windspeed");
                int weatherCode = currentWeather.getInt("weathercode");
        return new WeatherData(temperature, windSpeed, windDirection, isDayOrNight, weatherCode);
    }

    private static JSONObject getJsonObject(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        JSONObject jsonResponse = new JSONObject(response.toString());
        JSONObject currentWeather = jsonResponse.getJSONObject("current_weather");
        return currentWeather;
    }


}
