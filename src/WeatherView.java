import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WeatherView extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Current Weather App");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("WeatherViewFXML.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 600, 450);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
