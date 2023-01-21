package ws2022.Client.ViewController;

import java.io.FileNotFoundException;
import java.text.DecimalFormat;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.DataFormat;
import javafx.util.Duration;

public class BoardGameOnlController {
    // private static BoardGameOnlController bgoc;
    private final Integer startTime = 15;
    private Integer seconds = startTime;

    @FXML
    Label countdown;
    DecimalFormat dFormat = new DecimalFormat("00");

    public BoardGameOnlController() {
    }

    public void initialize() throws FileNotFoundException {
        countdown.setText("00:" + seconds);
        countdownTimer();
    }

    // public static BoardGameOnlController getInstance() {
    // if (BoardGameOnlController.bgoc == null) {
    // bgoc = new BoardGameOnlController();
    // }
    // return bgoc;
    // }

    public void countdownTimer() {
        Timeline time = new Timeline();
        time.setCycleCount(Timeline.INDEFINITE);
        if (time != null) {
            time.stop();
        }
        KeyFrame frame = new KeyFrame(Duration.seconds(1), new javafx.event.EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                seconds--;
                countdown.setText("00:" + dFormat.format(seconds));
                if (seconds <= 0) {
                    time.stop();
                }
            }

        }, null);
        time.getKeyFrames().add(frame);
        time.playFromStart();

    }

    // private SceneController sc = SceneController.getInstance();
}
