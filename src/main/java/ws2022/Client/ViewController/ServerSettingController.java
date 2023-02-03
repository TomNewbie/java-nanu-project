package ws2022.Client.ViewController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.ServerSocket;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import ws2022.Client.Model.Dice;
import ws2022.Client.Model.GameManager;
import ws2022.Server.Server;
import ws2022.Server.ServerThread;

public class ServerSettingController {
    @FXML
    Button HomeButton;
    @FXML
    ComboBox<Integer> difficulty = new ComboBox<>();
    @FXML
    Label ipLabel;
    SceneController sc = SceneController.getInstance();
    SoundController soundc = new SoundController();
    ServerThread st;
    private static ServerSettingController ssc = new ServerSettingController();

    private ServerSettingController() {

    }

    public static ServerSettingController getInstance() {
        return ssc;
    }

    @FXML
    public void initialize() throws IOException {
        Integer number[] = { 1, 2, 3, 4, 5 };
        difficulty.getItems().addAll(number);
        setUpServer();
        GameManager.startGame();
        ipLabel.setText("Your IP address is: " + Inet4Address.getLocalHost().getHostAddress());

    }

    public void setUpServer() throws IOException {
        ServerSocket serverSocket = new ServerSocket(1809);
        Server server = new Server(serverSocket);
        st = new ServerThread(server);
        Thread thread = new Thread(st);
        thread.setDaemon(true);
        thread.start();
    }

    public void returnBtn(ActionEvent event) throws IOException {
        st.requestStop();
        soundc.click();
        sc.chooseRole(event);
    }

    @FXML
    public void setDifficulty() {
        Integer myChoice = difficulty.getValue();
        Dice.numDice = myChoice;
    }

}
