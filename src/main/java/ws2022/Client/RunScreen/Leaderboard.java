package ws2022.Client.RunScreen;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Leaderboard  extends Application {

    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/ws2022/Client/ViewFx/leaderboard.fxml"));
        stage.setTitle("Nanu");
        stage.setScene(new Scene(root));

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
