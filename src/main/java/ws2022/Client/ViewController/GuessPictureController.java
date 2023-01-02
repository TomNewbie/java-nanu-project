
package ws2022.Client.ViewController;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ws2022.Client.Model.Disc;
import ws2022.Client.Model.GameManager;

public class GuessPictureController {

    @FXML
    private Text cover;

    @FXML
    private ChoiceBox<String> choicebox = new ChoiceBox<>();

    public void display(String color) {
        cover.setText(color + " cover ?");
        ArrayList<String> answer = new ArrayList();
        for (Disc i : GameManager.myList) {
            if (!i.checkGuessed()) {

                answer.add(i.getValue());
            }
        }

        choicebox.getItems().addAll(answer);
    }
}
