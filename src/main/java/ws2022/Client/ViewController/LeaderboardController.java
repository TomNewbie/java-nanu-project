package ws2022.Client.ViewController;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import ws2022.Client.Model.GameManager;

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
    SoundController soundc = new SoundController();

    public void initialize() throws FileNotFoundException {
        soundc.victory();
        String winnerName = "";
        String loserName = "";
        int winnerScore = 0;
        int loserScore = 0;
        if (GameManager.PLAYER1.getScore() >= GameManager.PLAYER2.getScore()) {
            winnerName = GameManager.PLAYER1.getName();
            winnerScore = GameManager.PLAYER1.getScore();
            loserName = GameManager.PLAYER2.getName();
            loserScore = GameManager.PLAYER2.getScore();
        } else {
            winnerName = GameManager.PLAYER2.getName();
            winnerScore = GameManager.PLAYER2.getScore();
            loserName = GameManager.PLAYER1.getName();
            loserScore = GameManager.PLAYER1.getScore();
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
        sc.createScene(event, "EnterProfile");
    }
}
