
package ws2022.Client.ViewController;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
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
        choicebox.getItems().addAll(GameManager.getArrayValue());
    }

    public void clickSubmit(ActionEvent event) throws IOException {
        String myChoice = choicebox.getValue();
        String answer = GameManager.getAnswer();
        SceneController sc = SceneController.getInstance();
        if (myChoice.equals(answer)) {
            sc.createScene(event, "rightAnswer");
        } else {
            sc.createScene(event, "wrongAnswer");
        }

    }
}