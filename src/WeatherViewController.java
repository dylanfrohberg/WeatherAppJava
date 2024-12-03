import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class WeatherViewController {

    @FXML
    private Label lblTemperature;

    @FXML
    private Label lblWindSpeed;

    @FXML
    private Label lblWindDirection;

    @FXML
    private Label lblWeatherCondition;

    @FXML
    private TextField txtZipCode;

    @FXML
    private Button btnSubmit;

    @FXML Label lblLocation;

    @FXML
    private ImageView imgBackground;

    @FXML
    private ImageView imgDayOrNight;

    @FXML
    private Label lblError;

    @FXML
    private AnchorPane Overlay;

    private PauseTransition pauseTransition;

    private static final Image clearSky = new Image("file:/C:/Users/dfrohbe1/IdeaProjects/WeatherAppJava_Fall2024/src/img/clearsky.jpg");
    private static final Image snow = new Image("file:/C:/Users/dfrohbe1/IdeaProjects/WeatherAppJava_Fall2024/src/img/snow.jpg");
    private static final Image thunderstorm = new Image("file:/C:/Users/dfrohbe1/IdeaProjects/WeatherAppJava_Fall2024/src/img/thunderstorm.jpg");
    private static final Image cloudy = new Image("file:/C:/Users/dfrohbe1/IdeaProjects/WeatherAppJava_Fall2024/src/img/cloudy.jpg");
    private static final Image rainy = new Image("file:/C:/Users/dfrohbe1/IdeaProjects/WeatherAppJava_Fall2024/src/img/rainy.jpg");
    private static final Image partlyCloudy = new Image("file:/C:/Users/dfrohbe1/IdeaProjects/WeatherAppJava_Fall2024/src/img/partlycloudy.png");
    private static final Image foggy = new Image("file:/C:/Users/dfrohbe1/IdeaProjects/WeatherAppJava_Fall2024/src/img/foggy.jpg");
    private static final Image sun = new Image("file:/C:/Users/dfrohbe1/IdeaProjects/WeatherAppJava_Fall2024/src/img/sun.png");
    private static final Image moon = new Image("file:/C:/Users/dfrohbe1/IdeaProjects/WeatherAppJava_Fall2024/src/img/moon.png");
    @FXML
    public void initialize() {
        // Set fixed dimensions for images
        imgBackground.setFitWidth(600);
        imgBackground.setFitHeight(450);
        imgBackground.setPreserveRatio(false);
    }

    public void showErrorMessage() { //Show error label
        lblError.setOpacity(1);
    }
    public void clearErrorMessage() {
        lblError.setOpacity(0);     // Hide the error label
    }
    public void getWeather(ActionEvent actionEvent) throws IOException {
        clearErrorMessage();
        String zipCode = txtZipCode.getText();

        double[] latLong = ZipCodeToLatLong.getLatLong(zipCode); //Get the lat long from ZipCodeToLatLong
        if (latLong[0] == 0 && latLong[1] == 0) {
            showErrorMessage(); //If no lat long, show error message
        }
        if (latLong[0] != 0 && latLong[1] != 0) {
            double latitude = latLong[0];
            double longitude = latLong[1];
            WeatherData weather = WeatherService.getWeather(latitude, longitude); //Pass lat long into the weather service method and make a Weather Data object
            //Set all the labels
            lblTemperature.setText("Temperature: " + weather.getTemperature() + "°F");
            lblWindSpeed.setText("Wind speed: " + weather.getWindSpeed() + " mph");
            lblWindDirection.setText("Wind Direction: " + weather.getCompassDirection());
            lblWeatherCondition.setText("Weather Condition: " + weather.getWeatherCode());
            lblLocation.setText("Location: " + ZipCodeToLatLong.getCity() +", " + ZipCodeToLatLong.getState());
            int weatherCode = weather.getWeatherNumericalCode();
            //If it's night, turn brightness of the anchor pane down and moon image is displayed
            ColorAdjust colorAdjust = new ColorAdjust();
            if (weather.getIsDayOrNight() == 0) {
                imgDayOrNight.setImage(moon);
                colorAdjust.setBrightness(-.2);
                imgBackground.setEffect(colorAdjust);
                Overlay.setEffect(colorAdjust);
            }
            else {
                //Ensure that if it's day, the brightness is default and sun image is displayed
                colorAdjust.setBrightness(0);
                imgDayOrNight.setImage(sun);
                imgDayOrNight.setEffect(colorAdjust);
                Overlay.setEffect(colorAdjust);
            }
            //Switch case to set the image of the background based on the weather code
            switch (weatherCode) {
                case 0:
                    imgBackground.setImage(clearSky);
                    break;
                case 1:
                case 2:
                    imgBackground.setImage(partlyCloudy);
                    break;
                case 3:
                    imgBackground.setImage(cloudy);
                    break;
                case 45:
                case 48:
                    imgBackground.setImage(foggy);
                    break;
                case 51:
                case 53:
                case 55:
                    imgBackground.setImage(rainy);
                    break;
                case 61:
                case 63:
                case 65:
                    imgBackground.setImage(rainy);
                    break;
                case 71:
                case 73:
                case 75:
                    imgBackground.setImage(snow);
                    break;
                case 95:
                case 96:
                case 99:
                    imgBackground.setImage(thunderstorm);
                    break;
                default:
                    imgBackground.setImage(partlyCloudy); // Default image if no match
                    break;
            }
        }
    }
}
