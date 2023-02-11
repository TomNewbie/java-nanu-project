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
 * The GameManager class is responsible for managing game states and data
 * between different classes.
 * 
 * It is contains all states, logic, and data of the application.
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

    /**
     * 
     * Gets the image string for a card in the game.
     * 
     * @return the image string for the card in the game
     */
    public static String getCardImage() {
        if (GameManager.isOnline) {
            System.out.println("game is not online");
            return imageString;
        }
        System.out.println(gameLogic.getCardImage());
        return gameLogic.getCardImage();
    }

    /**
     * 
     * Gets the list of values in the game, sorted in ascending order.
     * 
     * @return the list of values in the game, sorted in ascending order
     */
    public static ArrayList<String> getArrayValue() {
        ArrayList<String> result = Disc.convertToValue(gameLogic.myList);
        Collections.sort(result);
        return result;
    }

    /**
     * This method retrieves the answer of the current game. If the game is online,
     * it returns the answer, otherwise it returns the result of
     * {@link GameLogic#getAnswer()}
     * 
     * @return the answer of the current game
     */
    public static String getAnswer() {
        if (GameManager.isOnline) {
            return answer;
        }
        return gameLogic.getAnswer();
    }

    /**
     * This method updates the current game. If the total number of discs is greater
     * than or equal to the number of dices, it removes the guess picture button and
     * updates the game, otherwise it loads the leaderboard scene.
     * 
     * @param stage the stage on which the game is currently being played
     * @throws IOException if the leaderboard scene could not be loaded
     */
    public static void updateGame(Stage stage) throws IOException {
        gameLogic.totalDisc--;
        if (gameLogic.totalDisc >= Dice.numDice) {
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

    /**
     * 
     * This method updates the online game information when the game is ongoing.
     * It reduces the totalDisc by 1 and adds score to the player.
     * 
     * @return false if the totalDisc is not greater than 20, true otherwise.
     */
    public static boolean updateGameOnline() {
        gameLogic.totalDisc--;
        playerManager.addScore();
        if (gameLogic.totalDisc > 20) {
            return false;
        }
        return true;
    }

    /**
     * This method starts the game in online mode. It generates the discs
     * information
     * and shuffles them to create randomness in the game. It also sets the
     * pictureName
     * field with the sorted array of the discs information.
     */
    public static void startGame() {
        GenerateData.generateDisc(gameLogic.myList);
        Collections.shuffle(gameLogic.myList);
        gameLogic.pictureName = getArrayValue();
    }

}