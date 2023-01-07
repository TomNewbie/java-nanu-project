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

    SceneController sceneController = new SceneController();

    public void returnHome(ActionEvent event) throws IOException {
        sceneController.homeScreen(event);
    }

    public void enterProfile1(ActionEvent event) throws IOException {
        sceneController.enterProfile1(event);
    }

    public void goToProfile2(ActionEvent event) throws IOException {
        // In first enterprofile
        String name = nameTF.getText();
        String age = ageTF.getText();
        if (name.isEmpty()) {
            showAlertMessage(Alert.AlertType.ERROR, "Name Required!",
                    "Please enter your name");
            return;
        }
        if (age == null) {
            showAlertMessage(Alert.AlertType.ERROR, "Age Required!",
                    "Please enter your age");
            return;
        }
        if (!age.matches("\\d+")) {
            showAlertMessage(Alert.AlertType.ERROR, "Wrong format!",
                    "Please enter your age again!");
            return;
        }
        GameManager.PLAYER1 = new Player(name, Integer.parseInt(age));
        sceneController.enterProfile2(event, name);
    }

    public void enterGame(ActionEvent event) throws IOException {
        // in second enter profile (before go to game)
        String name = nameTF.getText();
        String age = ageTF.getText();
        if (name.isEmpty()) {
            showAlertMessage(Alert.AlertType.ERROR, "Name Required!",
                    "You do not enter your name. Please enter your name!");
            return;
        }
        if (age == null) {
            showAlertMessage(Alert.AlertType.ERROR, "Age Required!",
                    "You do not enter your age. Please enter your age!");
            return;
        }
        if (!age.matches("\\d+")) {
            showAlertMessage(Alert.AlertType.ERROR, "Wrong format!",
                    "Your age is not a number. Please enter a number!");
            return;
        }
        if (GameManager.PLAYER1.getName().equals(name)) {
            showAlertMessage(Alert.AlertType.ERROR, "Same name!",
                    "You have the same name as the player 1. Please enter another name!");
            return;
        }
        GameManager.PLAYER2 = new Player(name, Integer.parseInt(age));
        sceneController.enterGame(event);
    }

    public void displayProfile(String name, int age) {
        nameTF.setText(name);
        ageTF.setText("" + age);
    }

    @FXML
    public static void showAlertMessage(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }
}
