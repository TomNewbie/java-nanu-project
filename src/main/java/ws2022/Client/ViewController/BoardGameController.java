package ws2022.Client.ViewController;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ws2022.Client.Logic.GenerateData;
import ws2022.Client.Model.Coordinate;
import ws2022.Client.Model.Dice;
import ws2022.Client.Model.Disc;
import ws2022.Client.Model.GameManager;
import ws2022.Client.utils.ConvertCoordinate;

import java.io.Console;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class BoardGameController {
    private static BoardGameController bgc;

    private BoardGameController() {
    }

    public static BoardGameController getInstance() {
        if (BoardGameController.bgc == null) {
            bgc = new BoardGameController();
        }
        return bgc;
    }

    private SceneController sc = SceneController.getInstance();
    @FXML
    public GridPane boardgame;
    public Button myButton;
    public ImageView dice;
    public Pane pane;
    Button rollDice = new Button("Roll Dice");
    Button guessPicture = new Button("Guess Picture");
    Button chooseColor = new Button("Choose color");
    @FXML
    public Text player1;
    @FXML
    public Text player2;
    @FXML
    public Text player1Score;
    @FXML
    public Text player2Score;
    @FXML
    public Text status;
    @FXML
    public ImageView imageView;

    private HashMap<String, ImageView> HashMapImageView = new HashMap<>();;

    @FXML
    public void initialize() throws FileNotFoundException {
        GenerateData.getSortValue(GameManager.value);
        GenerateData.generateDisc(GameManager.myList);
        Collections.shuffle(GameManager.myList);
        int index = 0;
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 6; x++) {
                String selectedImage = "/ws2022/Client/assets/FootballTheme/"
                        + GameManager.myList.get(index).getImage();
                Image image = new Image(this.getClass()
                        .getResource(selectedImage)
                        .toExternalForm());
                Circle clip = new Circle(50, 50, 45);
                imageView = new ImageView(image);

                // populate matrix
                imageView.setFitWidth(100);
                imageView.setFitHeight(100);
                imageView.setClip(clip);
                imageView.setOnMouseClicked(event -> changeDisc(event));
                imageView.setUserData(new Coordinate(x, y)); // index data
                boardgame.add(imageView, x, y);
                index++;
            }
        }
        // image of dice
        Image diceImage = new Image(this.getClass()
                .getResource("/ws2022/Client/assets/Dice/dice.png")
                .toExternalForm());
        dice.setImage(diceImage);
        dice.setFitWidth(100);
        dice.setFitHeight(100);
        player1.setText(GameManager.PLAYER1.getName());
        player1Score.setText("" + GameManager.PLAYER1.getScore());
        player2.setText(GameManager.PLAYER2.getName());
        player2Score.setText("" + GameManager.PLAYER1.getScore());
    }

    public void setTurn(boolean isPlayer1Turn) {
        status.setVisible(true);
        if (isPlayer1Turn) {
            status.setText("Player " + GameManager.PLAYER1.getName() + " turn: ");
            return;
        }
        status.setText("Player " + GameManager.PLAYER2.getName() + " turn: ");
    }

    // random cover
    public void setUpCover() {
        String[] colorImage = { "blue", "green", "orange", "red", "yellow" };
        int count = 0;
        while (count < 5) {
            Random random = new Random();
            int x = random.nextInt(6);
            int y = random.nextInt(4);
            int index = y * 6 + x;
            String selectedImage = "/ws2022/Client/assets/Covers/" + colorImage[count] + ".png";

            if (GameManager.myList.get(index).checkCover())
                continue;
            putCover(selectedImage, new Coordinate(x, y), colorImage[count]);
            count++;
        }
    }

    public void putCover(String selectedImage, Coordinate coord, String color) {
        Image image = new Image(this.getClass().getResource(selectedImage).toExternalForm());
        Circle clip = new Circle(50, 50, 45);
        ImageView imageView = new ImageView(image);

        int x = coord.getColumn();
        int y = coord.getRow();
        int index = y * 6 + x;
        GameManager.myList.get(index).setCover();
        if (GameManager.myHashMap.get(color) == null) {
            GameManager.myHashMap.put(color, index);
        } else {
            GameManager.myHashMap.replace(color, index);
        }
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        imageView.setClip(clip);
        imageView.setOnMouseClicked(event -> alertCover(event));
        HashMapImageView.put(color, imageView);
        boardgame.add(imageView, x, y);
    }

    public void deleteCover(Coordinate coord) {
        // deleteCover by coordinate
        boardgame.getChildren().remove(HashMapImageView.get(GameManager.COLOR));
        ObservableList<Node> childrens = boardgame.getChildren();
        for (Node node : childrens) {
            if (node instanceof ImageView && GridPane.getRowIndex(node) == coord.getRow()
                    && GridPane.getColumnIndex(node) == coord.getColumn()) {
                ImageView imageView = (ImageView) node; // use what you want to remove
                boardgame.getChildren().remove(imageView);
                break;
            }
        }
    }

    // click remeber all
    @FXML
    public void clickRememberAll() throws IOException {
        setUpCover();
        pane.getChildren().remove(myButton);
        GameManager.getFirstTurn();
        setTurn(GameManager.isPlayer1Turn);
        createRollDiceBtn();
    }

    public void createRollDiceBtn() {
        rollDice.setPrefWidth(100);
        rollDice.setPrefHeight(50);
        rollDice.setLayoutX(800);
        rollDice.setLayoutY(342);
        rollDice.setOnAction(event -> {
            try {
                clickRollDice();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        pane.getChildren().add(rollDice);
    }

    public void clickRollDice() throws IOException {
        GameManager.COLOR = Dice.rollDice();

        if (GameManager.COLOR.equals("joker")) {
            System.out.println("you get joker mathar facker");
            getJoker();
        } else {
            getNormalColor();
        }

    }

    public void getNormalColor() {
        // display cover has been generated by roll dice
        String coverImage = "/ws2022/Client/assets/Covers/" + GameManager.COLOR + ".png";
        Image cover = new Image(this.getClass()
                .getResource(coverImage)
                .toExternalForm());
        dice.setImage(cover);
        dice.setFitWidth(100);
        dice.setFitHeight(100);

        // remove roll Dice button
        pane.getChildren().remove(rollDice);

        // add guess Picture button
        guessPicture.setPrefWidth(100);
        guessPicture.setPrefHeight(50);
        guessPicture.setLayoutX(800);
        guessPicture.setLayoutY(342);
        guessPicture.setOnAction(event -> {
            try {
                Stage popupwindow = new Stage();
                popupwindow.initModality(Modality.APPLICATION_MODAL);
                popupwindow.setTitle("This is a pop up window");

                FXMLLoader loader = new FXMLLoader(
                        this.getClass().getResource("/ws2022/Client/ViewFx/guessPicture.fxml"));
                Parent popUp = loader.load();
                GuessPictureController gpc = loader.getController();
                gpc.display();
                Scene scene = new Scene(popUp);
                popupwindow.setScene(scene);

                popupwindow.showAndWait();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        pane.getChildren().add(guessPicture);

    }

    public void getJoker() {
        // display dice image
        String diceImage = "/ws2022/Client/assets/Dice/Joker.png";
        Image cover = new Image(this.getClass()
                .getResource(diceImage)
                .toExternalForm());
        dice.setImage(cover);
        dice.setFitWidth(100);
        dice.setFitHeight(100);

        // remove roll Dice button
        pane.getChildren().remove(rollDice);

        // add which color button
        chooseColor.setPrefWidth(100);
        chooseColor.setPrefHeight(50);
        chooseColor.setLayoutX(800);
        chooseColor.setLayoutY(342);
        chooseColor.setOnAction(event -> {
            try {
                Stage popupwindow = new Stage();
                GameManager.currentPopUp = popupwindow;
                popupwindow.initModality(Modality.APPLICATION_MODAL);
                popupwindow.setTitle("This is a pop up window");
                FXMLLoader loader = new FXMLLoader(
                        this.getClass().getResource("/ws2022/Client/ViewFx/WhichColor.fxml"));
                Parent popUp = loader.load();
                WhichColorController gpc = loader.getController();
                gpc.display();
                Scene scene = new Scene(popUp);
                popupwindow.setScene(scene);
                popupwindow.showAndWait();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        pane.getChildren().add(chooseColor);
    }

    public void update() {
        if (GameManager.isPlayer1Turn) {
            player1Score.setText("" + GameManager.PLAYER1.getScore());
        } else {
            player2Score.setText("" + GameManager.PLAYER2.getScore());
        }
        status.setText("Please choose picture to place " + GameManager.COLOR + " cover");
        GameManager.isChangeDisc = true;
    }

    public void removeGuessPictureBtn() {
        pane.getChildren().remove(guessPicture);
    }

    // show disc value when click on disc

    public void changeDisc(MouseEvent event) {
        if (!GameManager.isChangeDisc) {
            return;
        }
        GameManager.isChangeDisc = false;
        deleteCover(GameManager.getCurrentColorCoord());
        Node sourceComponent = (Node) event.getSource();
        Coordinate coord = (Coordinate) sourceComponent.getUserData();
        String coverImage = "/ws2022/Client/assets/Covers/" + GameManager.COLOR + ".png";
        putCover(coverImage, coord, GameManager.COLOR);
        setTurn(GameManager.isPlayer1Turn);
    }

    public void alertCover(MouseEvent event) {
        if (!GameManager.isChangeDisc) {
            return;
        }
        sc.showAlertMessage(AlertType.ERROR, "Error",
                "You can not choose this picture. Choose another one without cover");
    }
}
