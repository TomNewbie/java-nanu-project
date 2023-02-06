package ws2022.Client.Model;

import javafx.scene.control.Alert;
import ws2022.Client.ViewController.SceneController;

public class PlayerManager {
    public Player PLAYER1;
    public Player PLAYER2;
    private boolean isPlayer1Turn = false;

    public void addScore() {
        if (isPlayer1Turn) {
            PLAYER1.addScore();
            return;
        }
        PLAYER2.addScore();

    }

    public void getFirstTurn() {
        if (PLAYER1.getAge() > PLAYER2.getAge()) {
            isPlayer1Turn = false;
            return;
        }
        isPlayer1Turn = true;
    }

    public void changeTurn() {
        isPlayer1Turn = !isPlayer1Turn; // flip turn
    }

    public boolean checkIsPlayer1Turn() {
        return isPlayer1Turn;
    }

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
