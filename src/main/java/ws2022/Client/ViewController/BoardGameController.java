package ws2022.Client.ViewController;

import java.io.File;
import java.io.FileInputStream;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class BoardGameController {
    @FXML
    public GridPane boardgame;

    @FXML
    public void initialize() throws FileNotFoundException {
        // FileInputStream input = new
        // FileInputStream("/ws2022/assets/FootballTheme/Australia.png");
        String[] arrFlag = { "Argentina", "Australia", "Belgium", "Brazil", "Cameroon", "Canada" };

        ArrayList<String> myList = new ArrayList<>(Arrays.asList(arrFlag));

        Image image = new Image(
                this.getClass().getResourceAsStream("/ws2022/Client/assets/FootballTheme/Argentina.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(120);
        imageView.setFitHeight(120);
        boardgame.add(imageView, 1, 1);
    }

}
