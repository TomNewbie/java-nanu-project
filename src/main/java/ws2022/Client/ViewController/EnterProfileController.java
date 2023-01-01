package ws2022.Client.ViewController;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import ws2022.Client.Logic.BoardGame;
import ws2022.Client.Model.GameManager;
import ws2022.Client.Model.Player;
import ws2022.Client.utils.CalculateAge;

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
