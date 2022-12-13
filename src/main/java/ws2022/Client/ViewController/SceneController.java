package ws2022.Client.ViewController;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ws2022.App;

public class SceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void switchToPrimary(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../ViewFx/primary.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToSecondary(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../ViewFx/secondary.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
