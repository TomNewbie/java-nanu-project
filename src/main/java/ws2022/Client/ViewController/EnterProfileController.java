package ws2022.Client.ViewController;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import ws2022.Client.Model.GameManager;
import ws2022.Client.Model.Player;

/**
 * This class specifies and gives to several scenes the posibility to play a
 * short tune
 * when pressing a button.
 *
 * These methods has this class: enterProfile1, goToProfile2, enterGame and
 * displayProfile.
 * 
 * These methods are used for adding some sound and specifying name and age of
 * the user.
 * 
 * The method "displayProfile" shows the name and age of the user when playing
 * the game!
 */

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

    // Specify sound when going to screen for first player
    public void enterProfile1(ActionEvent event) throws IOException {
        soundc.click();
        sceneController.enterProfile1(event);
    }

    // Specify sound when going to screen for second player
    public void goToProfile2(ActionEvent event) throws IOException {
        // In first enterprofile
        soundc.click();
        String name = nameTF.getText();
        String age = ageTF.getText();
        GameManager.playerManager.validateValue(name, age);
        GameManager.playerManager.PLAYER1 = new Player(name, Integer.parseInt(age));
        sceneController.enterProfile2(event, name);
    }

    public void enterGame(ActionEvent event) throws IOException {
        // in second enter profile (before go to game)
        soundc.click();
        String name = nameTF.getText();
        String age = ageTF.getText();
        if (!GameManager.playerManager.validateValue(name, age))
            return;
        if (GameManager.playerManager.PLAYER1.getName().equals(name)) {
            sceneController.showAlertMessage(Alert.AlertType.ERROR, "Same name!",
                    "You have the same name as the player 1. Please enter another name!");
            return;
        }
        GameManager.playerManager.PLAYER2 = new Player(name, Integer.parseInt(age));
        GameManager.startGame();
        sceneController.enterGame(event);
    }

    public void displayProfile(String name, int age) {
        nameTF.setText(name);
        ageTF.setText("" + age);
    }

}
