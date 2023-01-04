
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
        ArrayList<String> answer = new ArrayList<String>();
        for (Disc i : GameManager.myList) {
            if (!i.checkGuessed()) {
                answer.add(i.getValue());
            }
        }

        choicebox.getItems().addAll(answer);
    }

    public void clickSubmit(ActionEvent event) throws IOException {
        String myChoice = choicebox.getValue();
        String answer = GameManager.getAnswer();
        if (myChoice.equals(answer)) {
            GameManager.isCorrect = true;
        } else {
            GameManager.isCorrect = false;
        }
        SceneController sc = new SceneController();
        GameManager.currentPopUp = sc.createScene(event, "answer");
    }

}

// Need to do two more things:
// @TomNewbie