package ws2022.Client.ViewController;

import java.util.Arrays;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ws2022.Middleware.GameManager;

public class WhichColorController {

    @FXML
    private ChoiceBox<String> choiceBox = new ChoiceBox<>();
    @FXML
    private Pane mypane;
    @FXML
    private Button nextButton;
    private String[] color = { "red", "green", "blue", "yellow", "orange" };
    private List<String> colorList = Arrays.asList(color);

    public void display() {
        choiceBox.getItems().addAll(colorList);
    }

    public void closePopUp() {
        String myChoice = choiceBox.getValue();
        GameManager.COLOR = myChoice;
        Stage mystage = (Stage) mypane.getScene().getWindow();
        mystage.close();
        BoardGameController bgc = BoardGameController.getInstance();
        bgc.getNormalColor();
    }
}
