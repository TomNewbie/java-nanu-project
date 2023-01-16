package ws2022.Client.ViewController;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import ws2022.Middleware.GameManager;

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

    public void initialize() throws FileNotFoundException {
        String winnerName = "";
        String loserName = "";
        int winnerScore = 0;
        int loserScore = 0;
        if (GameManager.players.get(0).getScore() >= GameManager.players.get(1).getScore()) {
            winnerName = GameManager.players.get(0).getName();
            winnerScore = GameManager.players.get(0).getScore();
            loserName = GameManager.players.get(1).getName();
            loserScore = GameManager.players.get(1).getScore();
        } else {
            winnerName = GameManager.players.get(1).getName();
            winnerScore = GameManager.players.get(1).getScore();
            loserName = GameManager.players.get(0).getName();
            loserScore = GameManager.players.get(0).getScore();
        }
        name_1.setText(winnerName);
        point_1.setText("" + winnerScore);
        name_2.setText(loserName);
        point_2.setText("" + loserScore);
    }

    public void clickReturnButton(ActionEvent event) throws IOException {
        SceneController sc = SceneController.getInstance();
        sc.createScene(event, "HomeScreen");
    }

    public void clickAgainButton(ActionEvent event) throws IOException {
        SceneController sc = SceneController.getInstance();
        sc.createScene(event, "EnterProfile");
    }
}
