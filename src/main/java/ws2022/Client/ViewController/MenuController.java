package ws2022.Client.ViewController;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;

public class MenuController {
    @FXML
    Slider soundValue;
    @FXML
    Button HomeButton;
    SoundController soundc = new SoundController();
    SceneController sc = SceneController.getInstance();

    @FXML
    public void initialize() throws FileNotFoundException {
        soundValue.setValue(SoundController.volume * 100);
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
}
