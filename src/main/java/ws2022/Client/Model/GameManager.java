package ws2022.Client.Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import javafx.scene.control.Alert;

import javafx.stage.Stage;
import ws2022.Client.Client;
import ws2022.Client.ViewController.BoardGameController;
import ws2022.Client.ViewController.SceneController;
import ws2022.Client.utils.GenerateData;

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
    private static SceneController sc = SceneController.getInstance();
    public static String theme = "Classic";
    // for online
    public static boolean isOnline = false;
    public static String imageString;
    public static String answer;
    public static String[] colorImage = { "red", "green", "blue", "yellow", "orange" };

    public static String[] imageArray() {
        String[] result = new String[Dice.numDice];
        for (int i = 0; i < Dice.numDice; i++) {
            result[i] = colorImage[i];
        }
        return result;
    }

    public static String getCardImage() {
        if (GameManager.isOnline) {
            return imageString;
        }
        return GameManager.myList.get(GameManager.coverHashMap.get(GameManager.COLOR)).getCardImage();
    }

    public static ArrayList<String> getArrayValue() {
        ArrayList<String> result = Disc.convertToValue(myList);
        Collections.sort(result);
        return result;
    }

    public static String getAnswer() {
        if (GameManager.isOnline) {
            return answer;
        }
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
        totalDisc--;
        if (totalDisc > 4) {
            BoardGameController bgc = BoardGameController.getInstance();
            GameManager.pictureName.remove(GameManager.getAnswer());
            bgc.removeGuessPictureBtn();
            bgc.update();
            return;
        } else {
            // create leaderboard here
            sc.loadSceneByStage(stage, "Leaderboard");
        }
    }

    public static boolean updateGameOnline() {
        totalDisc--;
        addScore();
        if (totalDisc > 22) {
            return false;
        }
        return true;
    }

    public static void startGame() {
        GenerateData.generateDisc(myList);
        Collections.shuffle(myList);
        pictureName = getArrayValue();
    }

    public static Coordinate[] setUpCover() {
        Coordinate[] coordinates = new Coordinate[5];
        int count = 0;
        while (count < Dice.numDice) {
            Random random = new Random();
            int indexList = random.nextInt(totalDisc);
            if (GameManager.myList.get(indexList).checkCover())
                continue;
            GameManager.myList.get(indexList).setCover();
            coordinates[count] = Coordinate.convertToCoordinate(indexList);
            System.out.println("set up cover");
            System.out.println("index: " + indexList);
            System.out.println("x, y" + ": " + coordinates[count].getColumn() + ", " + coordinates[count].getRow());
            count++;
        }
        return coordinates;
    }

    public static Coordinate getCurrentColorCoord() {
        return Coordinate.convertToCoordinate(GameManager.coverHashMap.get(GameManager.COLOR));
    }

    public static boolean validateValue(String name, String age) {
        if (name.isEmpty()) {
            sc.showAlertMessage(Alert.AlertType.ERROR, "Name Required!",
                    "Please enter your name");
            return false;
        }
        if (age == null) {
            sc.showAlertMessage(Alert.AlertType.ERROR, "Age Required!",
                    "Please enter your age");
            return false;
        }
        if (!age.matches("\\d+")) {
            sc.showAlertMessage(Alert.AlertType.ERROR, "Wrong format!",
                    "Please enter your age again!");
            return false;
        }
        if (Integer.parseInt(age) > 100) {
            sc.showAlertMessage(Alert.AlertType.ERROR, "Wrong logic!",
                    "Please another age! Why you are so old?");
            return false;
        }
        return true;
    }
}