import java.io.IOException;
import java.util.Scanner;
//Console
public class ConsoleDriver {
    public static void main(String[] args) throws IOException {
            Scanner scan = new Scanner(System.in);
            System.out.print("Enter Zip Code: ");
            String zipCode = scan.next();
            double[] latLong = ZipCodeToLatLong.getLatLong(zipCode);
            if (latLong[0] != 0 && latLong[1] != 0) {
                double latitude = latLong[0];
                double longitude = latLong[1];
                WeatherData weather = WeatherService.getWeather(latitude, longitude);
                System.out.println("Current temperature: " + weather.getTemperature() + "°F");
                System.out.println("Wind speed: " + weather.getWindSpeed() + "mph");
                System.out.println("Wind Direction: " + weather.getCompassDirection());
                System.out.println("Weather Condition: " + weather.getWeatherCode());
                if (weather.getIsDayOrNight() == 0) {
                    System.out.println("It is currently night time.");
                }
                else if (weather.getIsDayOrNight() == 1) {
                    System.out.println("It is currently day time.");
                }
                else
                    System.out.println("There has been an error fetching the time of day.");
            } else {
                System.out.println("Invalid latitude and longitude received.");
            }

    }
}
