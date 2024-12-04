import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class WeatherView extends Application {
    /* Zipcodes for Presentation
        Norfolk: 68701
        Alaska: 99501
        Hawaii: 96815
        Florida: 33101
        Maine: 04101
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Image icon = new Image("file:/C:/Users/dfrohbe1/IdeaProjects/WeatherAppJava_Fall2024/src/img/icon.png");
        primaryStage.setTitle("Current Weather App");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("WeatherViewFXML.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(icon);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
