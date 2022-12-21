package ws2022.Client.RunScreen;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ForgotPassword extends Application {
    // @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/ws2022/Client/ViewFX/ForgotPassword.fxml"));
        primaryStage.setTitle("Forgot Password");
        primaryStage.setScene(new Scene(root, 1000,600);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
