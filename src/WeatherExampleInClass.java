import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class WeatherExampleInClass {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter Zip Code: ");
        String zipCode = scan.next();
        double[] latLong = ZipCodeToLatLong.getLatLong(zipCode);
        if (latLong[0] != 0 && latLong[1] != 0) {
            getWeather(latLong[0], latLong[1]);
        } else {
            System.out.println("Invalid latitude and longitude received.");
        }
    }

    public static void getWeather(double latitude, double longitude) {
        String urlString = String.format(
                "https://api.open-meteo.com/v1/forecast?latitude=%f&longitude=%f&current_weather=true&temperature_unit=fahrenheit&wind_speed_unit=mph&precipitation_unit=inch",
                latitude, longitude);

        try {
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

            double temperature = currentWeather.getDouble("temperature");
            int isDayOrNight = currentWeather.getInt("is_day");
            double windDirection = currentWeather.getDouble("winddirection");
            double windSpeed = currentWeather.getDouble("windspeed");

            System.out.println("Temperature: " + temperature + "°F");
            System.out.println("Day/Night: " + (isDayOrNight == 1 ? "Day" : "Night"));
            System.out.println("Wind Direction: " + getCompassDirection(windDirection));
            System.out.println("Wind Speed: " + windSpeed + " mph");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getCompassDirection(double angle) {
        String[] directions = {"North", "Northeast", "East", "Southeast", "South", "Southwest", "West", "Northwest", "North"};
        return directions[(int) Math.round(((angle % 360) / 45))];
    }
}
