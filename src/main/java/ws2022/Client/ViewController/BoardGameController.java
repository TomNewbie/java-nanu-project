package ws2022.Client.ViewController;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BoardGameController {
    @FXML
    public GridPane boardgame;

    @FXML
    public void initialize() throws FileNotFoundException {
        String[] arrFlag = { "Argentina", "Australia", "Belgium", "Brazil",
                "Cameroon", "Canada", "CostaRica",
                "Croatia", "Denmark", "Ecuador", "England", "France", "Germany", "Ghana",
                "Iran", "Japan", "Korea",
                "Mexico", "Netherland", "Poland", "Portugal", "Qatar", "SaudiArabia",
                "Senegal", "Serbia", "Spain",
                "Switzerland", "Tunisia", "Uruguay", "USA", "Wales" };

        ArrayList<String> myList = new ArrayList<>(Arrays.asList(arrFlag));
        Collections.shuffle(myList);
        int index = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                String selectedImage = "/ws2022/Client/assets/FootballTheme/" + myList.get(index) + ".png";
                Image image = new Image(
                        this.getClass()
                                .getResourceAsStream(
                                        selectedImage));
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(120);
                imageView.setFitHeight(120);
                boardgame.add(imageView, i, j);
                index++;
            }
        }

    }

}
