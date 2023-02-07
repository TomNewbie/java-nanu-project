package ws2022.Client.ViewController;

import javafx.util.Duration;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ws2022.Client.Model.Coordinate;
import ws2022.Client.Model.Dice;
import ws2022.Client.Model.GameManager;
import ws2022.Middleware.API;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;

public class BoardGameController {
    private static BoardGameController bgc = new BoardGameController();
    public Stage popUpStage;

    private BoardGameController() {
    }

    public static BoardGameController getInstance() {
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
    public Text message;
    @FXML
    public ImageView imageView;

    private HashMap<String, ImageView> hashMapImageView = new HashMap<>();
    private HashMap<String, ImageView> hashMapPicture = new HashMap<>();
    SoundController soundc = new SoundController();
    private Integer seconds = 2;
    public Coordinate[] coverCoords = new Coordinate[5];
    @FXML
    Label countdown; // for online

    /**
     * The initialize method sets up the game board and populates it with pieces.
     * It also sets the player names and scores and starts the countdown timer if
     * the game is online.
     *
     * @throws FileNotFoundException if the image file is not found
     */
    @FXML
    public void initialize() throws FileNotFoundException {
        int index = 0;
        for (int y = 0; y < 7; y++) {
            for (int x = 0; x < 7; x++) {
                if (x != 0 && y != 0 && x != 6 && y != 6)
                    continue;
                String selectedImage = "/ws2022/assets/Theme/" + GameManager.gameLogic.theme + "/"
                        + GameManager.gameLogic.myList.get(index).getImage();
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
                hashMapPicture.put(GameManager.gameLogic.myList.get(index).getValue(), imageView);
                boardgame.add(imageView, x, y);
                // System.out.println(GameManager.myList.get(index).getImage());
                index++;
            }
        }
        player1.setText(GameManager.playerManager.PLAYER1.getName());
        player1Score.setText("" + GameManager.playerManager.PLAYER1.getScore());
        player2.setText(GameManager.playerManager.PLAYER2.getName());
        player2Score.setText("" + GameManager.playerManager.PLAYER1.getScore());
        if (GameManager.isOnline) {
            countdown.setText("00:" + seconds);
            countdownTimer();
        }
    }

    /**
     * Sets the turn of the players.
     *
     * @param isPlayer1Turn If true, it's player 1's turn; otherwise, it's player
     *                      2's turn.
     */
    public void setTurn(boolean isPlayer1Turn) {
        status.setVisible(true);
        if (message != null)
            message.setVisible(false);
        if (isPlayer1Turn) {
            status.setText("Player " + GameManager.playerManager.PLAYER1.getName() + " turn: ");
            return;
        }
        // Player 2 turn
        status.setText("Player " + GameManager.playerManager.PLAYER2.getName() + " turn: ");
        if (GameManager.isOnline) {
            message.setVisible(true);
            message.setText("Waiting player " + GameManager.playerManager.PLAYER2.getName() + " to roll dice");
        }
    }

    /**
     * Starts a countdown timer and displays it in the countdown field.
     *
     * The timer counts down from a given time (in seconds) and stops at 0.
     * When the timer reaches 0, the `clickRememberAll` method is called.
     */
    public void countdownTimer() {
        Timeline time = new Timeline();
        time.setCycleCount(Timeline.INDEFINITE);
        if (time != null) {
            time.stop();

        }
        DecimalFormat dFormat = new DecimalFormat("00");
        KeyFrame frame = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                // TODO Auto-generated method stub
                seconds--;
                countdown.setText("00:" + dFormat.format(seconds));
                if (seconds <= 0) {
                    time.stop();
                    countdown.setVisible(false);
                    try {
                        clickRememberAll();

                    } catch (Exception e) {
                        e.printStackTrace();
                        // TODO: handle exception
                    }
                }
            }
        });
        time.getKeyFrames().add(frame);
        time.playFromStart();
    }

    /**
     * Adds an image as a cover to a specified coordinate on the board.
     *
     * @param selectedImage The path of the image file to be added as a cover.
     * @param coord         The coordinate on the board where the cover will be
     *                      placed.
     * @param color         The color of the cover, used for identification.
     */
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

        if (GameManager.gameLogic.coverHashMap.get(color) == null) {
            GameManager.gameLogic.coverHashMap.put(color, index);
        } else {
            GameManager.gameLogic.coverHashMap.replace(color, index);
        }

        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        imageView.setClip(clip);
        imageView.setOnMouseClicked(event -> alertCover(event));
        hashMapImageView.put(color, imageView);
        boardgame.add(imageView, x, y);
    }

    public void deleteCover() {
        // deleteCover by coordinate
        boardgame.getChildren().remove(hashMapImageView.get(GameManager.gameLogic.COLOR));
        boardgame.getChildren().remove(hashMapPicture.get(GameManager.getAnswer()));
    }

    /**
     * Handles the "Remember All" button click event.
     *
     * When the button is clicked, the covers are placed on the board,
     * the turn of the players is set, and the Roll Dice button is created
     * (if it is player 1's turn and the game is in online mode).
     *
     * @throws IOException If an input or output error occurs.
     */
    @FXML
    public void clickRememberAll() throws IOException {
        if (!GameManager.isOnline) {
            soundc.click();
            coverCoords = GameManager.gameLogic.setUpCover();
            GameManager.playerManager.getFirstTurn();
            boardgame.getChildren().remove(myButton);
            createRollDiceBtn();
        }
        for (int count = 0; count < Dice.numDice; count++) {
            String selectedImage = "/ws2022/assets/Covers/" + GameManager.gameLogic.colorImage[count] + ".png";
            putCover(selectedImage, coverCoords[count], GameManager.gameLogic.colorImage[count]);
        }
        setTurn(GameManager.playerManager.checkIsPlayer1Turn());
        if (GameManager.playerManager.checkIsPlayer1Turn() && GameManager.isOnline) {
            createRollDiceBtn();
        }
    }

    /**
     * 
     * Creates the Roll Dice button in the GUI.
     * This method creates the Roll Dice button with specific properties, such as
     * size and image.
     * It also sets the action when the button is clicked, either to roll the dice
     * locally or request a dice roll from the server if in online mode.
     * If the dice roll results in a joker, the method calls the getJoker() method.
     * Otherwise, it calls the getNormalColor() method.
     */
    public void createRollDiceBtn() {
        dice.setVisible(true);
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
                if (GameManager.isOnline) {
                    GameManager.client.requestDice();
                    return;
                }
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

    /**
     * 
     * Handles the event when the Roll Dice button is clicked.
     * This method rolls the dice locally (if in offline mode) or requests a dice
     * roll from the server (if in online mode).
     * If the dice roll result is a joker, it calls the getJoker() method.
     * Otherwise, it calls the getNormalColor() method.
     * 
     * @throws IOException If an input/output error occurs.
     */
    public void clickRollDice() throws IOException {
        soundc.click();
        if (!GameManager.isOnline) {
            GameManager.gameLogic.COLOR = Dice.rollDice();
        }
        if (GameManager.gameLogic.COLOR.equals("joker")) {
            soundc.joker();
            getJoker();
        } else {
            getNormalColor();
        }

    }

    /**
     * 
     * Displays the generated cover after a normal dice roll (not joker).
     * This method displays the generated cover after a dice roll in the GUI.
     * It updates the dice image, removes the Roll Dice button, and adds the Guess
     * Picture button.
     */
    public void getNormalColor() {
        // display cover has been generated by roll dice
        String coverImage = "/ws2022/assets/Covers/" + GameManager.gameLogic.COLOR + ".png";
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
                this.popUpStage = popupwindow;
                popupwindow.initModality(Modality.APPLICATION_MODAL);
                popupwindow.setTitle("Choose your option");

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

    /**
     * 
     * Method getJoker() displays the Joker dice image, removes the roll dice
     * button, and adds the choose color button.
     * The choose color button is used to display a pop-up window for the user to
     * select their color option.
     */
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
                popupwindow.setTitle("Choose your option");
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

    /**
     * 
     * Method update() updates the player scores displayed in the game board, and
     * sets the status of the game based on the current turn of the players.
     */
    public void update() {
        player1Score.setText("" + GameManager.playerManager.PLAYER1.getScore());
        player2Score.setText("" + GameManager.playerManager.PLAYER2.getScore());
        if (!GameManager.isOnline || GameManager.playerManager.checkIsPlayer1Turn()) {
            status.setText("Please choose picture to place " + GameManager.gameLogic.COLOR + " cover");
            GameManager.gameLogic.isChangeDisc = true;
        }
        // if (GameManager.isPlayer1Turn)

    }

    /**
     * 
     * Method removeGuessPictureBtn() removes the guess picture button from the game
     * board.
     */
    public void removeGuessPictureBtn() {
        boardgame.getChildren().remove(guessPicture);
    }

    /**
     * 
     * Method changeDisc() changes the color of the disc when the user clicks on it.
     * This method is used for both single player and multiplayer games.
     * It changes the color of the disc to the color determined by the current dice
     * roll and updates the turn of the players.
     * 
     * @param event MouseEvent object representing the mouse click event.
     */
    public void changeDisc(MouseEvent event) {
        if (!GameManager.gameLogic.isChangeDisc) {
            return;
        }
        soundc.click();
        GameManager.gameLogic.isChangeDisc = false;
        Node sourceComponent = (Node) event.getSource();
        Coordinate coord = (Coordinate) sourceComponent.getUserData();
        bgc.createRollDiceBtn();
        if (GameManager.isOnline) {
            GameManager.client.sendMessage(coord.toString(), API.Type.CHOOSE_COVER);
            return;
        }
        deleteCover();
        String coverImage = "/ws2022/assets/Covers/" + GameManager.gameLogic.COLOR + ".png";
        putCover(coverImage, coord, GameManager.gameLogic.COLOR);
        setTurn(GameManager.playerManager.checkIsPlayer1Turn());
    }

    /**
     * 
     * Method alertCover() shows an error message if the user tries to choose a
     * picture that already has a cover.
     * 
     * @param event MouseEvent object representing the mouse click event.
     */
    public void alertCover(MouseEvent event) {
        if (!GameManager.gameLogic.isChangeDisc) {
            return;
        }
        sc.showAlertMessage(AlertType.ERROR, "Error",
                "You can not choose this picture. Choose another one without cover");
    }
}
