package ws2022.Client.ViewController;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import ws2022.Client.Model.GameManager;
import ws2022.Client.Model.Player;

public class EnterProfileController {
    @FXML
    TextField nameTF;
    @FXML
    TextField ageTF;
    @FXML
    TextField IPserver;
    SceneController sceneController = SceneController.getInstance();
    SoundController soundc = new SoundController();

    public void returnHome(ActionEvent event) throws IOException {
        soundc.click();
        sceneController.homeScreen(event);
    }

    public void enterProfile1(ActionEvent event) throws IOException {
        soundc.click();
        sceneController.enterProfile1(event);
    }

    public void goToProfile2(ActionEvent event) throws IOException {
        // In first enterprofile
        soundc.click();
        String name = nameTF.getText();
        String age = ageTF.getText();
        GameManager.validateValue(name, age);
        GameManager.PLAYER1 = new Player(name, Integer.parseInt(age));
        sceneController.enterProfile2(event, name);
    }

    public void enterGame(ActionEvent event) throws IOException {
        // in second enter profile (before go to game)
        soundc.click();
        String name = nameTF.getText();
        String age = ageTF.getText();
        if (!GameManager.validateValue(name, age))
            return;
        if (GameManager.PLAYER1.getName().equals(name)) {
            sceneController.showAlertMessage(Alert.AlertType.ERROR, "Same name!",
                    "You have the same name as the player 1. Please enter another name!");
            return;
        }
        GameManager.PLAYER2 = new Player(name, Integer.parseInt(age));
        GameManager.startGame();
        sceneController.enterGame(event);
    }

    public void displayProfile(String name, int age) {
        nameTF.setText(name);
        ageTF.setText("" + age);
    }

}
