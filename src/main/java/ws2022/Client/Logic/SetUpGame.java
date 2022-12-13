package ws2022.Client.Logic;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SetUpGame extends Application {
    public void start(Stage stage) throws IOException {
        // URL url = new File("/ws2022/Client/ViewFx/boardgame.fxml")
        // .toURI().toURL();
        Parent root = FXMLLoader.load(getClass().getResource("/ws2022/Client/ViewFx/boardgame.fxml"));
        stage.setTitle("Nanu");
        stage.setScene(new Scene(root));

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
