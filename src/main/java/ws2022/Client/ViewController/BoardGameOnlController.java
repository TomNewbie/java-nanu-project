package ws2022.Client.ViewController;

import java.io.FileNotFoundException;
import java.text.DecimalFormat;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.DataFormat;
import javafx.scene.text.Text;
import javafx.util.Duration;
import ws2022.Client.Model.GameManager;

public class BoardGameOnlController {
    private static BoardGameOnlController bgoc;
    private final Integer startTime = 15;
    private Integer seconds = startTime;

    @FXML
    Label countdown;
    @FXML
    Text player1;
    @FXML
    Text player2;
    @FXML
    Text player1Score;
    @FXML
    Text player2Score;
    DecimalFormat dFormat = new DecimalFormat("00");

    private BoardGameOnlController() {
    }

    public void initialize() throws FileNotFoundException {
        setTextPlayer();
        countdown.setText("00:" + seconds);
        countdownTimer();

    }

    public void setTextPlayer() {
        player1.setText(GameManager.PLAYER1.getName());
        player1Score.setText("" + GameManager.PLAYER1.getScore());
        player2.setText(GameManager.PLAYER2.getName());
        player2Score.setText("" + GameManager.PLAYER1.getScore());
    }

    public static BoardGameOnlController getInstance() {
        if (BoardGameOnlController.bgoc == null) {
            bgoc = new BoardGameOnlController();
        }
        return bgoc;
    }

    public void countdownTimer() {
        Timeline time = new Timeline();
        time.setCycleCount(Timeline.INDEFINITE);
        if (time != null) {
            time.stop();
        }
        KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                seconds--;
                countdown.setText("00:" + dFormat.format(seconds));
                if (seconds <= 0) {
                    time.stop();
                    GameManager.startGame();
                }
            }
        });
        time.getKeyFrames().add(frame);
        time.playFromStart();
        System.out.println("hehe");
    }

    // private SceneController sc = SceneController.getInstance();
}
