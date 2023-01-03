
package ws2022.Client.ViewController;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
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
        cover.setText(GameManager.color + " cover ?");
        ArrayList<String> answer = new ArrayList();
        for (Disc i : GameManager.myList) {
            if (!i.checkGuessed()) {
                answer.add(i.getValue());
            }
        }

        choicebox.getItems().addAll(answer);
    }

    public void clickSubmit(ActionEvent event) throws IOException {
        String myChoice = choicebox.getValue();
        String answer = GameManager.myList.get(GameManager.myHashMap.get(GameManager.color)).getValue();
        if (myChoice.equals(answer)) {

            System.out.println("right");
            SceneController sc = new SceneController();
            sc.createScene(event, "rightAnswer");
        } else {
            System.out.println("wrong");
            SceneController sc = new SceneController();
            sc.createScene(event, "wrongAnswer");
        }
    }

}

// Need to do two more things: who is the next turn, how to close stage
// @TomNewbie