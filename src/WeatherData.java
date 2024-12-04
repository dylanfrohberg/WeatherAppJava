public class WeatherData {
    private double temperature;
    private double windSpeed;
    private double windDirection;
    private int isDayOrNight;
    private int weatherCode;

    public WeatherData(double temperature, double windSpeed, double windDirection, int isDayOrNight, int weatherCode) {
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.isDayOrNight = isDayOrNight;
        this.weatherCode = weatherCode;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setWindDirection(double windDirection) {
        this.windDirection = windDirection;
    }

    public void setIsDayOrNight(int isDayOrNight) {
        this.isDayOrNight = isDayOrNight;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public double getWindDirection() {
        return windDirection;
    }

    public int getIsDayOrNight() {
        return isDayOrNight;
    }
    public String getWeatherCode() {
        return getWeatherDescription(weatherCode);
    }
    public int getWeatherNumericalCode() {
        return weatherCode;
    }

    public void setWeatherCode(int weatherCode) {
        this.weatherCode = weatherCode;
    }
    public String getCompassDirection() {
        return setCompassDirection(windDirection);
    }

    private static String setCompassDirection(double angle) {
        String[] directions = {"North", "Northeast", "East", "Southeast", "South", "Southwest", "West", "Northwest", "North"};
        return directions[(int) Math.round(((angle % 360) / 45))];
    }
/*

WMO Weather interpretation codes (WW)
Code	Description
0	Clear sky
1, 2, 3	Mainly clear, partly cloudy, and overcast
45, 48	Fog and depositing rime fog
51, 53, 55	Drizzle: Light, moderate, and dense intensity
56, 57	Freezing Drizzle: Light and dense intensity
61, 63, 65	Rain: Slight, moderate and heavy intensity
66, 67	Freezing Rain: Light and heavy intensity
71, 73, 75	Snow fall: Slight, moderate, and heavy intensity
77	Snow grains
80, 81, 82	Rain showers: Slight, moderate, and violent
85, 86	Snow showers slight and heavy
95 *	Thunderstorm: Slight or moderate
96, 99 *	Thunderstorm with slight and heavy hail

*/

    public String getWeatherDescription(int weatherCode) {
        // Map weather codes to descriptions (based on Open-Meteo API documentation)
        switch (weatherCode) {
            case 0:
                return "Clear sky";
            case 1:
                return "Mainly clear";
            case 2:
                return "Partly cloudy";
            case 3:
                return "Overcast";
            case 45:
                return "Foggy";
            case 48:
                return "Depositing Rime Fog"; // Fog and object are cold leaving ice on the object from the fog.
            case 51:
                return "Light Drizzle";
            case 53:
                return "Moderate Drizzle";
            case 55:
                return "Dense Drizzle";
            case 56:
                return "Light Freezing Drizzle";
            case 57:
                return "Dense Freezing Drizzle";
            case 61:
                return "Light Rain";
            case 63:
                return "Moderate Rain";
            case 65:
                return "Heavy Rain";
            case 66:
                return "Light Freezing Rain";
            case 67:
                return "Heavy Freezing Rain";
            case 71:
                return "Slight Snowfall";
            case 73:
                return "Moderate Snowfall";
            case 75:
                return "Heavy Snowfall";
            case 77:
                return "Snow Grains";
            case 80:
                return "Slight Rain Showers";
            case 81:
                return "Moderate Rain Showers";
            case 82:
                return "Violent Rain Showers";
            case 85:
                return "Slight Snow Showers";
            case 86:
                return "Heavy Snow Showers";
            case 95:
                return "Thunderstorms: Slight or Moderate";
            case 96:
                return "Thunderstorms with Light Hail"; // Only available in Central Europe
            case 99:
                return "Thunderstorms with Heavy Hail"; // Only available in Central Europe
            default:
                return "Unknown Weather Code: " + weatherCode;
        }
    }

}
