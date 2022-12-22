package ws2022.Client.ViewController;

import java.io.IOException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import ws2022.Client.Logic.BoardGame;

public class EnterProfileController {
    @FXML
    TextField nameTF;
    @FXML
    DatePicker birthdayDP;

    SceneController sceneController = new SceneController();

    public void returnHome(ActionEvent event) throws IOException {
        sceneController.homeScreen(event);
    }

    public void enterProfile1(ActionEvent event) throws IOException {
        sceneController.enterProfile(event);
    }

    public void sendData(ActionEvent event) throws IOException {
        String name = nameTF.getText();
        LocalDate birthday = birthdayDP.getValue();
        if (name.isEmpty()) {
            showAlertMessage(Alert.AlertType.ERROR, "Name Required!",
                    "Please enter your name");
            return;
        }
        if (birthday == null) {
            showAlertMessage(Alert.AlertType.ERROR, "Birthday Required!",
                    "Please enter your Birthday");
            return;
        }
        sceneController.enterProfile2(event);
    }

    public void enterGame(ActionEvent event) throws IOException {
        String name = nameTF.getText();
        LocalDate birthday = birthdayDP.getValue();
        if (name.isEmpty()) {
            showAlertMessage(Alert.AlertType.ERROR, "Name Required!",
                    "Please enter your name");
            return;
        }
        if (birthday == null) {
            showAlertMessage(Alert.AlertType.ERROR, "Birthday Required!",
                    "Please enter your Birthday");
            return;
        }
        sceneController.enterGame(event);
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
