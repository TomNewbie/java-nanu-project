package ws2022.Client.Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import javafx.scene.control.Alert;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.stage.Stage;
import ws2022.Client.ViewController.BoardGameController;
import ws2022.Client.ViewController.SceneController;
import ws2022.Client.utils.GenerateData;
import ws2022.Server.Client;

public class GameManager {
    // public static
    public static Player PLAYER1;
    public static Player PLAYER2;
    public static Client client;
    public static ArrayList<Disc> myList = new ArrayList<>();
    public static ArrayList<String> pictureName = new ArrayList<>();
    public static HashMap<String, Integer> coverHashMap = new HashMap<>();
    private static int totalDisc = 24;
    public static String COLOR;
    public static boolean isChangeDisc = false;
    public static Stage stage;
    public static boolean isCorrect;
    public static boolean isPlayer1Turn;
    public static boolean isClient = false;
    private static SceneController sc = SceneController.getInstance();

    public static String getCardImage() {
        return GameManager.myList.get(GameManager.coverHashMap.get(GameManager.COLOR)).getCardImage();
    }

    public static ArrayList<String> getArrayValue() {
        ArrayList<String> result = Disc.convertToValue(myList);
        Collections.sort(result);
        return result;
    }

    public static String getAnswer() {
        return GameManager.myList.get(GameManager.coverHashMap.get(GameManager.COLOR)).getValue();
    }

    public static void getFirstTurn() {
        if (GameManager.PLAYER1.getAge() > GameManager.PLAYER2.getAge()) {
            GameManager.isPlayer1Turn = false;
            return;
        }
        GameManager.isPlayer1Turn = true;
    }

    public static void addScore() {
        if (GameManager.isPlayer1Turn) {
            GameManager.PLAYER1.addScore();
            return;
        }
        GameManager.PLAYER2.addScore();

    }

    public static void changeTurn() {
        isPlayer1Turn = !isPlayer1Turn; // flip turn
    }

    public static void updateGame(Stage stage) throws IOException {
        if (isClient) {
            return;
        }
        totalDisc--;
        if (totalDisc > 4) {
            BoardGameController bgc = BoardGameController.getInstance();
            GameManager.pictureName.remove(GameManager.getAnswer());
            bgc.removeGuessPictureBtn();
            bgc.update();
            myList.get(coverHashMap.get(COLOR)).setGuess();
            return;
        } else {
            // create leaderboard here
            sc.leaderboard(stage);
        }
    }

    public static void startGame() {
        if (isClient) {
            System.out.println("Game start");
            return;
        }
        GenerateData.generateDisc(myList);
        Collections.shuffle(myList);
        pictureName = getArrayValue();
    }

    public static Coordinate[] setUpCover() {
        Coordinate[] coordinates = new Coordinate[5];
        int count = 0;
        while (count < 5) {
            Random random = new Random();
            // int x = random.nextInt(7);
            // int y = random.nextInt(7);
            int indexList = random.nextInt(totalDisc);
            // if (x != 0 && y != 0 && x != 6 && y != 6)
            // continue;

            // int indexPane = y * 7 + x;
            // int indexList = indexPane - (indexPane - 1) / 7 * 5;
            // String selectedImage = "/ws2022/assets/Covers/" + colorImage[count] + ".png";

            if (GameManager.myList.get(indexList).checkCover())
                continue;
            GameManager.myList.get(indexList).setCover();
            coordinates[count] = Coordinate.convertToCoordinate(indexList);
            System.out.println("set up cover");
            System.out.println("index: " + indexList);
            System.out.println("x, y" + ": " + coordinates[count].getColumn() + ", " + coordinates[count].getRow());
            // putCover(selectedImage, new Coordinate(x, y), colorImage[count]);
            count++;
        }
        return coordinates;
    }

    public static Coordinate getCurrentColorCoord() {
        return Coordinate.convertToCoordinate(GameManager.coverHashMap.get(GameManager.COLOR));
    }

    public static void validateValue(String name, String age) {
        if (name.isEmpty()) {
            sc.showAlertMessage(Alert.AlertType.ERROR, "Name Required!",
                    "Please enter your name");
            return;
        }
        if (age == null) {
            sc.showAlertMessage(Alert.AlertType.ERROR, "Age Required!",
                    "Please enter your age");
            return;
        }
        if (!age.matches("\\d+")) {
            sc.showAlertMessage(Alert.AlertType.ERROR, "Wrong format!",
                    "Please enter your age again!");
            return;
        }
    }
}