package ws2022.Client.ViewController;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class SoundController implements Initializable {
    public static double volume = 100;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    public void playSound(String path) {
        Media media = new Media(this.getClass()
                .getResource(path)
                .toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(SoundController.volume);
        mediaPlayer.play();
    }

    public void click() {
        String path = "/ws2022/sound/click.mp3";
        playSound(path);
    }

    public void correctAnswer() {
        String path = "/ws2022/sound/correctanswer.mp3";
        playSound(path);
    }

    public void dice() {
        String path = "/ws2022/sound/dice.mp3";
        playSound(path);
    }

    public void joker() {
        String path = "/ws2022/sound/joker.mp3";
        playSound(path);
    }

    public void lose() {
        String path = "/ws2022/sound/losegame.mp3";
        playSound(path);
    }

    public void wrongAnswer() {
        String path = "/ws2022/sound/wronganswer.mp3";
        playSound(path);
    }

    public void presskeyboard() {
        String path = "/ws2022/sound/presskeyboard.mp3";
        playSound(path);
    }

    public void victory() {
        String path = "/ws2022/sound/victory.mp3";
        playSound(path);
    }

}