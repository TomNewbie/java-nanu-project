package ws2022.Client.ViewController;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;
import ws2022.Client.Model.GameManager;

public class GuessPictureController {

    @FXML
    private Text cover;

    @FXML
    private ComboBox<String> comboBox = new ComboBox<>();

    @FXML
    private Button submit;
    SoundController soundc = new SoundController();

    public void display() throws IOException {
        cover.setText("What is the image under " + GameManager.COLOR + " cover ?");

        comboBox.getItems().addAll(GameManager.pictureName);
        comboBox.setVisibleRowCount(7);
    }

    public void clickSubmit(ActionEvent event) throws IOException {
        soundc.click();
        String myChoice = comboBox.getValue();
        String answer = GameManager.getAnswer();
        SceneController sc = SceneController.getInstance();
        if (myChoice.equals(answer)) {
            soundc.correctAnswer();
            sc.createScene(event, "rightAnswer");
        } else {
            soundc.wrongAnswer();
            sc.createScene(event, "wrongAnswer");
        }
    }
}