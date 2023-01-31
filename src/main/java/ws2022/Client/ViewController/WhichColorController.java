package ws2022.Client.ViewController;

import java.util.Arrays;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ws2022.Client.Model.GameManager;

public class WhichColorController {

    @FXML
    private ChoiceBox<String> choiceBox = new ChoiceBox<>();
    @FXML
    private Pane mypane;
    @FXML
    private Button nextButton;
    private List<String> colorList = Arrays.asList(GameManager.imageArray());
    SoundController soundc = new SoundController();

    public void display() {
        choiceBox.getItems().addAll(colorList);
    }

    public void closePopUp() {
        soundc.click();
        SceneController sc = SceneController.getInstance();
        String myChoice = choiceBox.getValue();
        if (myChoice == null) {
            sc.showAlertMessage(Alert.AlertType.ERROR, "Missing input", "Please choose a value!!");
            return;
        }
        GameManager.COLOR = myChoice;
        Stage popupwindow = (Stage) mypane.getScene().getWindow();
        popupwindow.close();
        BoardGameController bgc = BoardGameController.getInstance();
        bgc.getNormalColor();
    }
}
