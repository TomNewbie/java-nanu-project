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
    SoundController soundc = new SoundController();

    public void returnHome(ActionEvent event) throws IOException {
        soundc.click();
        sc.homeScreen(event);
    }

    public void goToServer(ActionEvent event) throws IOException {
        soundc.click();
        sc.serverSetting(event);
    }

    public void goToClient(ActionEvent event) throws IOException {
        soundc.click();
        sc.enterProfileOnline(event);
    }

}
