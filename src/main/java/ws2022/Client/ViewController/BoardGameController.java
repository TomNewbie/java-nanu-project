package ws2022.Client.ViewController;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import ws2022.Client.Model.Disc;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BoardGameController {
    @FXML
    public GridPane boardgame;
    public Button myButton;
    public ImageView dice;
    public Pane pane;

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
                // Image image = new Image(
                // this.getClass()
                // .getResourceAsStream(
                // selectedImage));
                Image image = new Image(this.getClass()
                        .getResource(selectedImage)
                        .toExternalForm());
                Circle clip = new Circle(50, 50, 45);
                ImageView imageView = new ImageView(image);

                // populate matrix
                imageView.setFitWidth(100);
                imageView.setFitHeight(100);
                imageView.setClip(clip);
                imageView.setUserData("sample"); // change
                imageView.setOnMouseClicked(event -> showDisc(event));
                boardgame.add(imageView, i, j);
                index++;

                // image of dice
                Image diceImage = new Image(this.getClass()
                        .getResource("/ws2022/Client/assets/Dice/dice.png")
                        .toExternalForm());
                dice.setImage(diceImage);
                dice.setFitWidth(100);
                dice.setFitHeight(100);

            }
        }

    }
    // random cover

    // change first image to USA when click remeber all
    @FXML
    public void clickRememberAll() {
        pane.getChildren().remove(myButton);
        Button rollDice = new Button("Roll Dice");
        rollDice.setPrefWidth(100);
        rollDice.setPrefHeight(50);
        rollDice.setLayoutX(800);
        rollDice.setLayoutY(342);
        rollDice.setOnAction(event -> clickRollDice());
        pane.getChildren().add(rollDice);
    }

    public void clickRollDice() {
        Button e = new Button("test");
        pane.getChildren().addAll(e);
    }

    // show disc value when click on disc
    @FXML
    public void showDisc(MouseEvent event) {
        Node sourceComponent = (Node) event.getSource();
        Disc disc = (Disc) sourceComponent.getUserData();
        System.out.println(disc.getValue() + " and " + disc.getImage());
    }

}
