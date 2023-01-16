package ws2022.Client.ViewController;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ws2022.Client.Model.GameManager;

public class HomeScreenController {
    @FXML
    private Pane pane;
    SceneController sc = SceneController.getInstance();

    public void playOffline(ActionEvent event) throws IOException {
        sc.enterProfile1(event);
    }

    public void playOnline(ActionEvent event) throws IOException {
        // sc.enterProfile1(event);
        GameManager.isOnline = true;
        sc.enterOnline(event);
    }

    public void quit(ActionEvent event) throws IOException {
        Stage mystage = (Stage) pane.getScene().getWindow();
        mystage.close();
    }

    public void manualPage(ActionEvent event) throws IOException {
        sc.gotomanualpage(event);
    }
}
