package ws2022.Client.ViewController;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * ChooseRoleController class is responsible for handling the role selection
 * screen of the application. It has three buttons to represent the user's
 * choices: home, server, and client.
 * 
 * The class has three methods, returnHome(), goToServer(), and goToClient(),
 * to handle the button events and navigate to the corresponding screens.
 * The class also has two instance variables, sc and soundc, for accessing the
 * SceneController and SoundController instances, respectively.
 */
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
