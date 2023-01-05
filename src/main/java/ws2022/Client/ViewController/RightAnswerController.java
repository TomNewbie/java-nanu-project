package ws2022.Client.ViewController;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import ws2022.Client.Model.GameManager;

public class RightAnswerController {
    @FXML
    private Button nextBtn;

    public void closePopUp(ActionEvent event) throws IOException {
        GameManager.currentPopUp.close();
        GameManager.addScore();
        GameManager.updateGame();

    }
}
