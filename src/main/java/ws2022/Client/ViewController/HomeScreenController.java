package ws2022.Client.ViewController;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ws2022.Client.Model.GameManager;

public class HomeScreenController {
    @FXML
    private Pane pane;
    SceneController sc = SceneController.getInstance();
    SoundController soundc = new SoundController();

    public void playOffline(ActionEvent event) throws IOException {
        soundc.click();
        sc.enterProfile1(event);
    }

    public void playOnline(ActionEvent event) throws IOException {
        // sc.enterProfile1(event);
        soundc.click();
        GameManager.isOnline = true;
        sc.enterProfileOnline(event);
    }

    public void quit(ActionEvent event) throws IOException {
        soundc.click();
        Stage mystage = (Stage) pane.getScene().getWindow();
        mystage.close();
    }

    Stage stage;
    Scene scene;
    Parent root;

    public void manualPage(ActionEvent event) throws IOException {

        /*
         * Parent root =
         * FXMLLoader.load(getClass().getResource("ws2022/fxml/ManualPage.fxml"));
         * stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
         * scene = new Scene(root);
         * scene.getStylesheets()
         * .add(getClass().getResource("/ws2022/Client/assets/styles/style.css").
         * toExternalForm());
         * stage.setScene(scene);
         * stage.setScene(scene);
         * stage.show();
         */
        // sc.gotomanualpage(event);
        soundc.click();
        Parent root = FXMLLoader.load(getClass().getResource("/ws2022/fxml/ManualPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setScene(scene);
        stage.show();
    }

}
