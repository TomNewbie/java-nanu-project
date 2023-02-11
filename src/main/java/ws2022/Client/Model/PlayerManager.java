package ws2022.Client.Model;

import javafx.scene.control.Alert;
import ws2022.Client.ViewController.SceneController;

/**
 * PlayerManager is a class that manages the players in a game.
 * 
 * The class has two players: PLAYER1 and PLAYER2.
 * 
 * It also has a variable isPlayer1Turn which controls which
 * player has the first turn.
 * 
 * Finally, it has three methods: addScore to add score
 * for the player who has right guess;
 * getFirstTurn to decide which player has first turn depend on their age;
 * changeTurn to give turn for the other player when the current one guesses
 * wrong.
 */

public class PlayerManager {
    public Player PLAYER1;
    public Player PLAYER2;
    private boolean isPlayer1Turn = false;

    /**
     * The addScore method adds the score to the player who's turn it is.
     * If it's player 1's turn, player 1's score will be increased, otherwise,
     * player 2's score will be increased.
     */

    public void addScore() {
        if (isPlayer1Turn) {
            PLAYER1.addScore();
            return;
        }
        PLAYER2.addScore();

    }

    /**
     * The getFirstTurn method sets the first turn of the game based on the players'
     * ages.
     * The player with the older age will start the game.
     */

    public void getFirstTurn() {
        if (PLAYER1.getAge() > PLAYER2.getAge()) {
            isPlayer1Turn = false;
            return;
        }
        isPlayer1Turn = true;
    }

    /**
     * The changeTurn method changes the current player's turn.
     * If it's player 1's turn, the turn will be changed to player 2 and vice versa.
     */

    public void changeTurn() {
        isPlayer1Turn = !isPlayer1Turn; // flip turn
    }

    /**
     * The checkIsPlayer1Turn method returns the boolean value indicating whether
     * it's player 1's turn or not.
     * 
     * @return a boolean value indicating whether it's player 1's turn or not.
     */
    public boolean checkIsPlayer1Turn() {
        return isPlayer1Turn;
    }

    /**
     * 
     * This method is used to validate the player's name and age.
     * It makes sure that the name and age entered by the player are not empty and
     * of a proper format.
     * If either of the two is incorrect, an error message will be displayed to the
     * user.
     * 
     * @param name the name of the player
     * @param age  the age of the player
     * @return true if the name and age are valid, false otherwise
     */
    public boolean validateValue(String name, String age) {
        SceneController sc = SceneController.getInstance();
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
