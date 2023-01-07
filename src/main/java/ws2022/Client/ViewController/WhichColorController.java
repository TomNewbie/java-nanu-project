package ws2022.Client.ViewController;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import ws2022.Client.Model.GameManager;

public class WhichColorController {

    @FXML
    private ChoiceBox<String> choiceBox = new ChoiceBox<>();

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
        GameManager.currentPopUp.close();
        BoardGameController bgc = GameManager.gameLoader.getController();
        bgc.getNormalColor();
    }
}
