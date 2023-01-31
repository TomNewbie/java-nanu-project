package ws2022.Client.ViewController;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import ws2022.Client.Model.Dice;

public class MenuController {
    @FXML
    Slider soundValue;
    @FXML
    Button HomeButton;
    @FXML
    ComboBox<Integer> difficulty = new ComboBox<>();
    SoundController soundc = new SoundController();
    SceneController sc = SceneController.getInstance();

    @FXML
    public void initialize() throws FileNotFoundException {
        Integer number[] = { 1, 2, 3, 4, 5 };
        soundValue.setValue(SoundController.volume * 100);
        difficulty.getItems().addAll(number);
    }

    public void returnHome(ActionEvent event) throws IOException {
        soundc.click();
        sc.homeScreen(event);
    }

    @FXML
    public void updateVolume() {
        Double sound = soundValue.getValue();
        SoundController.volume = sound / 100;
    }

    @FXML
    public void setDifficulty() {
        Integer myChoice = difficulty.getValue();
        Dice.numDice = myChoice;
    }
}
