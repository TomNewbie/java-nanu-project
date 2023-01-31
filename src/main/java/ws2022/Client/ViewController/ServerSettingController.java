package ws2022.Client.ViewController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Inet4Address;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import ws2022.Client.Model.Dice;
import ws2022.Server.Server;

public class ServerSettingController {
    @FXML
    Button HomeButton;
    @FXML
    ComboBox<Integer> difficulty = new ComboBox<>();
    @FXML
    Label ipLabel;
    SceneController sc = SceneController.getInstance();

    @FXML
    public void initialize() throws IOException {
        Integer number[] = { 1, 2, 3, 4, 5 };
        difficulty.getItems().addAll(number);
        // Server.execute();
        ipLabel.setText("Your IP number is: " +
                Inet4Address.getLocalHost().getHostAddress());
    }

    public void returnHome(ActionEvent event) throws IOException {
        sc.homeScreen(event);
    }

    @FXML
    public void setDifficulty() {
        Integer myChoice = difficulty.getValue();
        Dice.numDice = myChoice;
    }

}
