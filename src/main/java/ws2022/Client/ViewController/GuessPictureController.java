
package ws2022.Client.ViewController;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;
import ws2022.Client.Model.Disc;
import ws2022.Client.Model.GameManager;

public class GuessPictureController {

    @FXML
    private Text cover;

    @FXML
    private ChoiceBox<String> choicebox = new ChoiceBox<>();

    @FXML
    private Button submit;

    public void display() throws IOException {
        cover.setText(GameManager.COLOR + " cover ?");
        choicebox.getItems().addAll(GameManager.value);
    }

    public void clickSubmit(ActionEvent event) throws IOException {
        String myChoice = choicebox.getValue();
        String answer = GameManager.getAnswer();
        SceneController sc = new SceneController();
        if (myChoice.equals(answer)) {
            GameManager.currentPopUp = sc.createScene(event, "rightAnswer");
        } else {
            GameManager.currentPopUp = sc.createScene(event, "wrongAnswer");
        }

    }
}