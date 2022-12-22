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
import ws2022.Client.Logic.GenerateData;
import ws2022.Client.Model.Dice;
import ws2022.Client.Model.Disc;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class LeaderboardController {
    @FXML
    public GridPane leaderboard;
    public Button playAgain;
    public Button Return;
    public Pane pane;
}
