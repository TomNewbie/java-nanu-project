package ws2022.Client.ViewController;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import ws2022.Client.Client;
import ws2022.Client.Model.GameManager;
import ws2022.Client.Model.Player;
import ws2022.Middleware.API.Type;

public class EnterProfileOnlController {
    private static EnterProfileOnlController epoc;

    private EnterProfileOnlController() {
    }

    @FXML
    private Text status;
    @FXML
    private TextField nameTF;
    @FXML
    private TextField ageTF;
    @FXML
    private TextField IPserver;
    @FXML
    private Button okButton;
    @FXML
    private AnchorPane pane;

    public static EnterProfileOnlController getInstance() {
        if (epoc == null) {
            epoc = new EnterProfileOnlController();
        }
        return epoc;
    }

    public void setStatus() {
        status.setText("Connect successfully! Please wait player 2 enter the game!");
    }

    public void home(ActionEvent event) throws IOException {
        SceneController sc = SceneController.getInstance();
        sc.homeScreen(event);
    }

    public void enterOnlineGame(ActionEvent event) throws IOException, InterruptedException {
        String name = nameTF.getText();
        String age = ageTF.getText();
        String ipv4 = IPserver.getText();
        if (!GameManager.validateValue(name, age))
            return;
        GameManager.PLAYER1 = new Player(name, Integer.parseInt(age));
        GameManager.client = new Client(ipv4);
        GameManager.client.listenForMessage();
        GameManager.client.sendMessage(GameManager.PLAYER1.getAge() + "", Type.ENTER_PROFILE);
        pane.getChildren().remove(okButton);
    }
}
