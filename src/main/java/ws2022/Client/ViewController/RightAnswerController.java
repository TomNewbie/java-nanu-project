package ws2022.Client.ViewController;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ws2022.Middleware.GameManager;

public class RightAnswerController {
    @FXML
    private Button nextBtn;
    @FXML
    private Pane pane;
    SoundController soundc = new SoundController();

    public void closePopUp(ActionEvent event) throws IOException {
        soundc.click();
        Stage mystage = (Stage) pane.getScene().getWindow();
        mystage.close();
        GameManager.addScore();
        GameManager.updateGame(GameManager.stage);

    }
}
