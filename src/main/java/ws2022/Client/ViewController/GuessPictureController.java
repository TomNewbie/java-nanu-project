package ws2022.Client.ViewController;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import ws2022.Client.Model.GameManager;

/**
 * This class manages the putting answer when playing the game nanu.
 * 
 * It manages what happens when the user misses to select a value,
 * asks for a value and says if it is wrong or true.
 */
public class GuessPictureController {

    @FXML
    private Text cover;

    @FXML
    private ComboBox<String> comboBox = new ComboBox<>();

    @FXML
    private Button submit;
    SoundController soundc = new SoundController();

    // shows the list of the pictures at the game, ans to select the valie which is
    // asked for
    public void display() throws IOException {
        cover.setText("What is the image under " + GameManager.gameLogic.COLOR + " cover ?");

        comboBox.getItems().addAll(GameManager.gameLogic.pictureName);
        comboBox.setVisibleRowCount(7);
    }

    // some sound and shows an alert if value was not chosen
    public void clickSubmit(ActionEvent event) throws IOException {
        soundc.click();
        String myChoice = comboBox.getValue();
        SceneController sc = SceneController.getInstance();
        if (myChoice == null) {
            sc.showAlertMessage(Alert.AlertType.ERROR, "Missing input", "Please choose a value!!");
            return;
        }
        if (GameManager.isOnline) {
            GameManager.client.sendAnswer(myChoice);
            return;
        }
        String answer = GameManager.getAnswer();
        if (myChoice.equals(answer)) {
            soundc.correctAnswer();
            sc.createScene(event, "RightAnswer");
        } else {
            soundc.wrongAnswer();
            sc.createScene(event, "WrongAnswer");
        }
    }
}