package ws2022.Client.ViewController;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class WaitingController {
    @FXML
    public Text player1;
    @FXML
    public Text player2;
    @FXML
    public Text player3;
    @FXML
    public Text player4;
    @FXML
    public Text status1;
    @FXML
    public Text status2;
    @FXML
    public Text status3;
    @FXML
    public Text status4;
    private static WaitingController wc;

    private WaitingController() {
    }

    public static WaitingController getInstance() {
        if (WaitingController.wc == null) {
            wc = new WaitingController();
        }
        return wc;
    }

    @FXML
    public void initialize() {
        // player1.setText();
    }
}
