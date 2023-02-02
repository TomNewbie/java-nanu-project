package ws2022.Client.ViewController;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ChooseRoleController {
    @FXML
    Button HomeButton;
    @FXML
    Button ServerBtn;
    @FXML
    Button ClientBtn;
    SceneController sc = SceneController.getInstance();

    public void returnHome(ActionEvent event) throws IOException {
        sc.homeScreen(event);
    }

    public void goToServer(ActionEvent event) throws IOException {
        sc.serverSetting(event);
    }

    public void goToClient(ActionEvent event) throws IOException {
        sc.enterProfileOnline(event);
    }

}
