package ws2022.Client.ViewController;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class SoundController implements Initializable {
    String path = "/ws2022/sound/click.mp3";

    Media media = new Media(this.getClass()
            .getResource(path)
            .toExternalForm());

    MediaPlayer mediaPlayer = new MediaPlayer(media);

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    public void click() {
        mediaPlayer.play();
    }

}