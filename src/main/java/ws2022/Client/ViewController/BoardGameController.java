package ws2022.Client.ViewController;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ws2022.Client.Model.Coordinate;
import ws2022.Client.Model.Dice;
import ws2022.Middleware.GameManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

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
    private HashMap<String, ImageView> hashMapImageView = new HashMap<>();
    private HashMap<String, ImageView> hashMapPicture = new HashMap<>();
    SoundController soundc = new SoundController();

    @FXML
    public void initialize() throws FileNotFoundException {
        if (GameManager.isOnline) {
            return;
            // GenerateData.generateDisc(GameManager.myList);
            // Collections.shuffle(GameManager.myList);
        }
        GameManager.startGame();
        int index = 0;
        for (int y = 0; y < 7; y++) {
            for (int x = 0; x < 7; x++) {
                if (x != 0 && y != 0 && x != 6 && y != 6)
                    continue;
                String selectedImage = "/ws2022/assets/FootballTheme/"
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
                hashMapPicture.put(GameManager.myList.get(index).getValue(), imageView);
                boardgame.add(imageView, x, y);
                index++;
            }
        }
        // image of dice
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

    public void putCover(String selectedImage, Coordinate coord, String color) {
        Image image = new Image(this.getClass().getResource(selectedImage).toExternalForm());
        Circle clip = new Circle(50, 50, 45);
        ImageView imageView = new ImageView(image);

        int x = coord.getColumn();
        int y = coord.getRow();
        int index = Coordinate.convertToIndex(coord);
        if (index == -1) {
            System.out.println("there is something wrong with putCover");
            return;
        }
        if (GameManager.coverHashMap.get(color) == null) {
            GameManager.coverHashMap.put(color, index);
        } else {
            GameManager.coverHashMap.replace(color, index);
        }
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        imageView.setClip(clip);
        imageView.setOnMouseClicked(event -> alertCover(event));
        hashMapImageView.put(color, imageView);
        System.out.println("put cover");
        System.out.println("x, y" + x + " ," + y);
        System.out.println("index: " + index);
        boardgame.add(imageView, x, y);
    }

    public void deleteCover() {
        // deleteCover by coordinate
        boardgame.getChildren().remove(hashMapImageView.get(GameManager.COLOR));
        boardgame.getChildren().remove(hashMapPicture.get(GameManager.getAnswer()));
    }

    // click remeber all will set up cover
    @FXML
    public void clickRememberAll() throws IOException {
        soundc.click();
        Coordinate[] coverCoords = GameManager.setUpCover();
        String[] colorImage = { "blue", "green", "orange", "red", "yellow" };
        for (int count = 0; count < 5; count++) {
            String selectedImage = "/ws2022/assets/Covers/" + colorImage[count] + ".png";
            putCover(selectedImage, coverCoords[count], colorImage[count]);
        }
        boardgame.getChildren().remove(myButton);
        GameManager.getFirstTurn();
        setTurn(GameManager.isPlayer1Turn);
        createRollDiceBtn();
    }

    public void createRollDiceBtn() {
        Image diceImage = new Image(this.getClass()
                .getResource("/ws2022/assets/Dice/dice.png")
                .toExternalForm());
        dice.setImage(diceImage);
        dice.setFitWidth(100);
        dice.setFitHeight(100);
        if (guessPicture != null) {
            boardgame.getChildren().remove(guessPicture);
        }
        rollDice.setPrefHeight(50);
        rollDice.setPrefWidth(297);
        rollDice.setOnAction(event -> {
            try {
                clickRollDice();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        boardgame.setColumnIndex(rollDice, 2);
        boardgame.setRowIndex(rollDice, 5);
        boardgame.setColumnSpan(rollDice, 3);
        boardgame.getChildren().add(rollDice);

    }

    public void clickRollDice() throws IOException {
        soundc.click();
        GameManager.COLOR = Dice.rollDice();

        if (GameManager.COLOR.equals("joker")) {
            soundc.joker();
            System.out.println("you get joker mathar facker");
            getJoker();
        } else {
            getNormalColor();
        }

    }

    public void getNormalColor() {
        // display cover has been generated by roll dice
        String coverImage = "/ws2022/assets/Covers/" + GameManager.COLOR + ".png";
        Image cover = new Image(this.getClass()
                .getResource(coverImage)
                .toExternalForm());
        dice.setImage(cover);
        dice.setFitWidth(100);
        dice.setFitHeight(100);
        if (chooseColor != null) {
            boardgame.getChildren().remove(chooseColor);
        }
        // remove roll Dice button
        boardgame.getChildren().remove(rollDice);

        // add guess Picture button
        guessPicture.setPrefWidth(297);
        guessPicture.setPrefHeight(50);
        guessPicture.setOnAction(event -> {
            try {
                soundc.click();
                Stage popupwindow = new Stage();
                popupwindow.initModality(Modality.APPLICATION_MODAL);
                popupwindow.setTitle("This is a pop up window");

                popupwindow.setY(GameManager.stage.getY() + GameManager.stage.getHeight() / 3.5);
                popupwindow.setX(GameManager.stage.getX() + GameManager.stage.getWidth() / 7.75);
                FXMLLoader loader = new FXMLLoader(
                        this.getClass().getResource("/ws2022/fxml/guessPicture.fxml"));
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
        boardgame.setColumnIndex(guessPicture, 2);
        boardgame.setRowIndex(guessPicture, 5);
        boardgame.setColumnSpan(guessPicture, 3);
        boardgame.getChildren().add(guessPicture);
    }

    public void getJoker() {
        // display dice image
        String diceImage = "/ws2022/assets/Dice/Joker.png";
        Image cover = new Image(this.getClass()
                .getResource(diceImage)
                .toExternalForm());
        dice.setImage(cover);
        dice.setFitWidth(100);
        dice.setFitHeight(100);

        // remove roll Dice button
        boardgame.getChildren().remove(rollDice);

        // add which color button
        chooseColor.setPrefWidth(297);
        chooseColor.setPrefHeight(50);
        chooseColor.setOnAction(event -> {
            try {
                Stage popupwindow = new Stage();
                popupwindow.initModality(Modality.APPLICATION_MODAL);
                popupwindow.setTitle("This is a pop up window");
                FXMLLoader loader = new FXMLLoader(
                        this.getClass().getResource("/ws2022/fxml/WhichColor.fxml"));
                Parent popUp = loader.load();
                popupwindow.setY(GameManager.stage.getY() + GameManager.stage.getHeight() / 3.5);
                popupwindow.setX(GameManager.stage.getX() + GameManager.stage.getWidth() / 7.75);
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
        boardgame.setColumnIndex(chooseColor, 2);
        boardgame.setRowIndex(chooseColor, 5);
        boardgame.setColumnSpan(chooseColor, 3);
        boardgame.getChildren().add(chooseColor);
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
        boardgame.getChildren().remove(guessPicture);
    }

    // show disc value when click on disc

    public void changeDisc(MouseEvent event) {
        if (!GameManager.isChangeDisc) {
            return;
        }
        soundc.click();
        GameManager.isChangeDisc = false;
        deleteCover();
        Node sourceComponent = (Node) event.getSource();
        Coordinate coord = (Coordinate) sourceComponent.getUserData();
        String coverImage = "/ws2022/assets/Covers/" + GameManager.COLOR + ".png";
        putCover(coverImage, coord, GameManager.COLOR);
        setTurn(GameManager.isPlayer1Turn);
        bgc.createRollDiceBtn();
    }

    public void alertCover(MouseEvent event) {
        if (!GameManager.isChangeDisc) {
            return;
        }
        sc.showAlertMessage(AlertType.ERROR, "Error",
                "You can not choose this picture. Choose another one without cover");
    }
}
