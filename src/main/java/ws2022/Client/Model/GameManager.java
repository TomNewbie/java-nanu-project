package ws2022.Client.Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javafx.stage.Stage;
import ws2022.Client.Client;
import ws2022.Client.ViewController.BoardGameController;
import ws2022.Client.ViewController.SceneController;
import ws2022.Client.utils.GenerateData;

/**
 * The GameManager class is responsible for managing game states and data between different classes.
 * <p>
 * It is contains all states, logic, and data of the application.
 * 
 * @since 08/02/2023
 */

public class GameManager {

    public static PlayerManager playerManager = new PlayerManager();
    public static GameLogic gameLogic = new GameLogic();
    public static Client client;
    public static Stage stage;

    private static SceneController sc = SceneController.getInstance();

    public static boolean isOnline = false;
    public static String imageString;
    public static String answer;
    public static int countDownTimer;

    public static String getCardImage() {
        if (GameManager.isOnline) {
            System.out.println("game is not online");
            return imageString;
        }
        System.out.println(gameLogic.getCardImage());
        return gameLogic.getCardImage();
    }

    public static ArrayList<String> getArrayValue() {
        ArrayList<String> result = Disc.convertToValue(gameLogic.myList);
        Collections.sort(result);
        return result;
    }

    public static String getAnswer() {
        if (GameManager.isOnline) {
            return answer;
        }
        return gameLogic.getAnswer();
    }

    public static void updateGame(Stage stage) throws IOException {
        gameLogic.totalDisc--;
        if (gameLogic.totalDisc > 15) {
            BoardGameController bgc = BoardGameController.getInstance();
            gameLogic.pictureName.remove(GameManager.getAnswer());
            bgc.removeGuessPictureBtn();
            bgc.update();
            return;
        } else {
            // create leaderboard here
            sc.loadSceneByStage(stage, "Leaderboard");
        }
    }

    public static boolean updateGameOnline() {
        gameLogic.totalDisc--;
        playerManager.addScore();
        if (gameLogic.totalDisc > 20) {
            return false;
        }
        return true;
    }

    public static void startGame() {
        GenerateData.generateDisc(gameLogic.myList);
        Collections.shuffle(gameLogic.myList);
        gameLogic.pictureName = getArrayValue();
    }

}