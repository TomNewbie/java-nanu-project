package ws2022.Client.Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import ws2022.Client.ViewController.BoardGameController;
import ws2022.Client.ViewController.SceneController;

// public class GameManager {
//     // Singleton pattern
//     private static GameManager gameManager;
//     private Player player1;
//     private Player player2;

//     private GameManager(Player player1, Player player2) {
//         // private constructor
//         this.player1 = player1;
//         this.player2 = player2;
//     }

//     public static GameManager getInstance(Player player1, Player player2) {
//         if (GameManager.gameManager == null) {
//             gameManager = new GameManager(player1, player2);

//         }
//         return gameManager;
//     }

//     public int getPlayer1Score() {
//         return player1.getScore();
//     }

//     public int getPlayer2Score() {
//         return player2.getScore();
//     }

// }
public class GameManager {
    // public static
    public static Player PLAYER1;
    public static Player PLAYER2;
    public static ArrayList<Disc> myList = new ArrayList<>();
    public static ArrayList<String> value = new ArrayList<>();
    public static HashMap<String, Integer> myHashMap = new HashMap<>();
    private static int totalDisc = 24;
    public static String COLOR;
    public static boolean isChangeDisc = false;
    public static Stage currentPopUp;
    public static boolean isCorrect;

    public static String getCardImage() {
        return GameManager.myList.get(GameManager.myHashMap.get(GameManager.COLOR)).getCardImage();
    }

    public static String getAnswer() {
        return GameManager.myList.get(GameManager.myHashMap.get(GameManager.COLOR)).getValue();
    }

    public static boolean isPlayer1Turn;

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

    public static void updateGame(ActionEvent event) throws IOException {
        totalDisc--;
        if (totalDisc > 4) {
            BoardGameController bgc = BoardGameController.getInstance();
            bgc.removeGuessPictureBtn();
            bgc.update();
            return;
        } else {
            SceneController sc = SceneController.getInstance();
            sc.createScene(event, "Leaderboard");
        }
        // create leaderboard here
    }

    public static Coordinate getCurrentColorCoord() {
        return Coordinate.convertFromIndex(GameManager.myHashMap.get(GameManager.COLOR));
    }
}