
package ws2022.Client.ViewController;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.cell.ChoiceBoxListCell;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import ws2022.Middleware.GameManager;

public class GuessPictureController {

    @FXML
    private Text cover;

    @FXML
    private ComboBox<String> comboBox = new ComboBox<>();

    @FXML
    private Button submit;

    public void display() throws IOException {
        cover.setText(GameManager.COLOR + " cover ?");

        comboBox.getItems().addAll(GameManager.getArrayValue());
        comboBox.setVisibleRowCount(5);
    }

    public void clickSubmit(ActionEvent event) throws IOException {
        String myChoice = comboBox.getValue();
        String answer = GameManager.getAnswer();
        SceneController sc = SceneController.getInstance();
        if (myChoice.equals(answer)) {
            sc.createScene(event, "rightAnswer");
        } else {
            sc.createScene(event, "wrongAnswer");
        }

    }
}