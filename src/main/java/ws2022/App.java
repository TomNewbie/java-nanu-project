package ws2022;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ws2022.Client.Model.GameManager;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/ws2022/fxml/HomeScreen.fxml"));
        System.out.println("Starting...");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Nanu");
        GameManager.stage = stage;
        stage.setScene(scene);
        stage.show();
        
    }

    public static void main(String[] args) {
        launch();
    }

}