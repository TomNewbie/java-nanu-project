package ws2022.Client.ViewController;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ManualOptionsController {
    @FXML
    public Button onlineBtn;
    @FXML
    public Button offlineBtn;
    SceneController sc = SceneController.getInstance();
    SoundController soundc = new SoundController();
    Stage stage;
    Scene scene;

    public void returnHome(ActionEvent event) throws IOException {
        soundc.click();
        sc.homeScreen(event);
    }

    @FXML
    public void goToOffline(ActionEvent event) throws IOException {
        soundc.click();
        Parent root = FXMLLoader.load(getClass().getResource("/ws2022/fxml/ManualPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void goToOnline(ActionEvent event) throws IOException {
        soundc.click();
        Parent root = FXMLLoader.load(getClass().getResource("/ws2022/fxml/ManualOnline.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setScene(scene);
        stage.show();
    }
}
