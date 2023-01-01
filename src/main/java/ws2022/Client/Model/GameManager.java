package ws2022.Client.Model;

import java.util.ArrayList;

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
}