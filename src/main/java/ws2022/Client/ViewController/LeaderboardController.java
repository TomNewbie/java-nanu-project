package ws2022.Client.ViewController;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import ws2022.Client.Model.GameManager;

/**
 * This class manages what happens at the end of the game
 * 
 * It manages the Leaderboard.
 * 
 * The methods contain the function which what text will shown
 * to the user when the game is over. Also the return and again buttun will be
 * managed
 */
public class LeaderboardController {
    @FXML
    public GridPane leaderboard;
    @FXML
    public Button againBtn;
    @FXML
    public Button returnBtn;
    @FXML
    public Text name_1;
    @FXML
    public Text point_1;
    @FXML
    public Text name_2;
    @FXML
    public Text point_2;
    @FXML
    public Label status;
    SoundController soundc = new SoundController();

    public void initialize() throws FileNotFoundException {
        boolean isPlayer1Win = GameManager.playerManager.PLAYER1.getScore() >= GameManager.playerManager.PLAYER2
                .getScore();
        if (GameManager.isOnline) {
            returnBtn.setVisible(false);
            againBtn.setText("Main Menu");
            if (isPlayer1Win) {
                soundc.victory();
                status.setText("You are the winner!");
            } else {
                soundc.lose();
                status.setText("You lose :( ");
            }
        } else
            soundc.victory();
        String winnerName = "";
        String loserName = "";
        int winnerScore = 0;
        int loserScore = 0;
        if (isPlayer1Win) {
            winnerName = GameManager.playerManager.PLAYER1.getName();
            winnerScore = GameManager.playerManager.PLAYER1.getScore();
            loserName = GameManager.playerManager.PLAYER2.getName();
            loserScore = GameManager.playerManager.PLAYER2.getScore();
        } else {
            winnerName = GameManager.playerManager.PLAYER2.getName();
            winnerScore = GameManager.playerManager.PLAYER2.getScore();
            loserName = GameManager.playerManager.PLAYER1.getName();
            loserScore = GameManager.playerManager.PLAYER1.getScore();
        }
        name_1.setText(winnerName);
        point_1.setText("" + winnerScore);
        name_2.setText(loserName);
        point_2.setText("" + loserScore);
    }

    public void clickReturnButton(ActionEvent event) throws IOException {
        soundc.click();
        SceneController sc = SceneController.getInstance();
        sc.createScene(event, "HomeScreen");
    }

    public void clickAgainButton(ActionEvent event) throws IOException {
        soundc.click();
        SceneController sc = SceneController.getInstance();
        if (GameManager.isOnline) {
            GameManager.client.close();
            sc.createScene(event, "HomeScreen");
            return;
        }
        sc.createScene(event, "EnterProfile");
    }
}
