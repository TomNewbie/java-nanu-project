package ws2022.Client.ViewController;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ws2022.Client.Model.GameManager;

/**
 * 
 * The class RightAnswerController is a JavaFX controller class that is used to
 * control the right answer pop-up window.
 * 
 * It provides methods to close the right answer pop-up window and perform
 * actions when the next button is clicked.
 * 
 */
public class RightAnswerController {
    @FXML
    private Button nextBtn;
    @FXML
    private Pane pane;
    SoundController soundc = new SoundController();

    /**
     * 
     * The method closePopUp() is used to close the right answer pop-up window and
     * perform actions based on whether the game is online or offline.
     * 
     * @param event the ActionEvent object that is generated when the next button is
     *              clicked
     * @throws IOException if an input/output exception occurs while loading the
     *                     FXML file
     */
    public void closePopUp(ActionEvent event) throws IOException {
        soundc.click();
        Stage mystage = (Stage) pane.getScene().getWindow();
        mystage.close();
        if (GameManager.isOnline) {
            GameManager.client.closePopUp("right");
            return;
        }
        GameManager.playerManager.addScore();
        GameManager.updateGame(GameManager.stage);

    }
}
