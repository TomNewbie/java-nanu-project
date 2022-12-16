package ws2022.Client.ViewController;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import ws2022.Client.Model.Disc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BoardGameController {
    @FXML
    public GridPane boardgame;
    public Button myButton;

    @FXML
    public void initialize() throws FileNotFoundException {
        String[] arrFlag = { "Argentina", "Australia", "Belgium", "Brazil",
                "Cameroon", "Canada", "CostaRica",
                "Croatia", "Denmark", "Ecuador", "England", "France", "Germany", "Ghana",
                "Iran", "Japan", "Korea",
                "Mexico", "Netherland", "Poland", "Portugal", "Qatar", "SaudiArabia",
                "Senegal", "Serbia", "Spain",
                "Switzerland", "Tunisia", "Uruguay", "USA", "Wales" };
        Disc disc1 = new Disc("Argentina.png", "Argentina");
        Disc disc2 = new Disc("Australia.png", "Australia");

        ArrayList<String> myList = new ArrayList<>(Arrays.asList(arrFlag));
        Collections.shuffle(myList);
        int index = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                String selectedImage = "/ws2022/Client/assets/FootballTheme/" + myList.get(index) + ".png";
                // Image image = new Image(
                // this.getClass()
                // .getResourceAsStream(
                // selectedImage));
                Image image = new Image(this.getClass()
                        .getResource(selectedImage)
                        .toExternalForm());
                Circle clip = new Circle(60, 60, 50);
                ImageView imageView = new ImageView(image);

                imageView.setFitWidth(120);
                imageView.setFitHeight(120);
                imageView.setClip(clip);
                imageView.setUserData(disc1); // change
                imageView.setOnMouseClicked(event -> showDisc(event));
                boardgame.add(imageView, i, j);
                index++;
            }
        }

    }
    // random cover

    // change first image to USA when click remeber all
    @FXML
    public void changeImage() {
        Image image = new Image(
                this.getClass()
                        .getResourceAsStream("/ws2022/Client/assets/FootballTheme/USA.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(120);
        imageView.setFitHeight(120);
        boardgame.add(imageView, 0, 0);
    }

    // show disc value when click on disc
    @FXML
    public void showDisc(MouseEvent event) {
        Node sourceComponent = (Node) event.getSource();
        Disc disc = (Disc) sourceComponent.getUserData();
        System.out.println(disc.getValue() + " and " + disc.getImage());
    }

}
