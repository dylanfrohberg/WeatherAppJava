import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class ZipCodeToLatLong {
    private static String city;
    private static String state;
    public static double[] getLatLong(String zipCode) {
        double[] latLong = new double[2]; // Store latitude and longitude in an array
        try {
            String apiUrl = "https://api.zippopotam.us/us/" + zipCode; //Zippopotam API
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder content = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                conn.disconnect();
                //Set latitude, longitude, city, and state.
                JSONObject jsonResponse = new JSONObject(content.toString());
                latLong[0] = Double.parseDouble(jsonResponse.getJSONArray("places").getJSONObject(0).getString("latitude"));
                latLong[1] = Double.parseDouble(jsonResponse.getJSONArray("places").getJSONObject(0).getString("longitude"));
                city = jsonResponse.getJSONArray("places").getJSONObject(0).getString("place name");
                state = jsonResponse.getJSONArray("places").getJSONObject(0).getString("state");
            } else {
                System.out.println("Error: Unable to fetch data for the provided ZIP code.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return latLong;
    }

    //Getters for state and city
    public static String getCity() {
    return city;
    }
    public static String getState() {
        return state;
    }
}