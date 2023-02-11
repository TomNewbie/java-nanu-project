package ws2022.Client.ViewController;

import java.io.File;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.util.Arrays;
import java.util.stream.Collectors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import ws2022.Client.Model.Dice;
import ws2022.Client.Model.GameManager;
import ws2022.Server.Server;
import ws2022.Server.ServerThread;

/**
 * The ServerSettingController class is responsible for handling the server-side
 * settings of the game.
 *
 * It contains various UI components such as the Home button, theme combobox,
 * difficulty combobox, and IP label.
 * 
 * The class provides simple functionality for setting up the server and
 * displaying themes.
 * 
 * It also allows the server to set the game difficulty and
 * return to the home screen.
 */

public class ServerSettingController {
    @FXML
    Button HomeButton;
    @FXML
    ComboBox<String> theme = new ComboBox<>();
    @FXML
    ComboBox<Integer> timer = new ComboBox<>();
    @FXML
    ComboBox<Integer> difficulty = new ComboBox<>();
    @FXML
    Label ipLabel;
    @FXML
    Button settingBtn;
    SceneController sc = SceneController.getInstance();
    SoundController soundc = new SoundController();
    ServerThread st;
    private static ServerSettingController ssc = new ServerSettingController();

    private ServerSettingController() {

    }

    public static ServerSettingController getInstance() {
        return ssc;
    }

    /**
     * 
     * The initialize method is called automatically when the FXML file is loaded.
     * It sets up the difficulty combobox, starts the game, sets up the server,
     * displays the themes, and sets the IP label text.
     * 
     * @throws IOException If an input or output exception occurs.
     */
    @FXML
    public void initialize() throws IOException {
        Integer number[] = { 1, 2, 3, 4, 5 };
        timer.getItems().addAll(Arrays.asList(number)
                .stream()
                .map(hehe -> hehe * 5)
                .collect(Collectors.toList()));
        difficulty.getItems().addAll(number);
        setUpServer();
        displayThemes();
        ipLabel.setVisible(false);
    }

    /**
     * 
     * The displayThemes method lists all the available themes in the theme
     * combobox.
     */
    public void displayThemes() {

        String[] themes = { "Classic", "Football" };
        theme.getItems().addAll(themes);
    }

    public void setTimer() {
        int myChoice = timer.getValue();
        GameManager.countDownTimer = myChoice;
    }

    /**
     * 
     * The setUpServer method sets up the server.
     * 
     * @throws IOException If an input or output exception occurs.
     */
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

    public void setTheme() {
        String myChoice = theme.getValue();
        GameManager.gameLogic.theme = myChoice;
    }

    public void saveSetting() {
        if (difficulty.getValue() == null || theme.getValue() == null || timer.getValue() == null) {
            sc.showAlertMessage(AlertType.ERROR, "Missing input", "Please choose all input in Choice Box");
            return;
        }
        settingBtn.setVisible(false);
        GameManager.startGame();
        try {
            ipLabel.setText("Save succesfully! Your IP address is: " + Inet4Address.getLocalHost().getHostAddress());
            ipLabel.setVisible(true);
            timer.setDisable(true);
            theme.setDisable(true);
            difficulty.setDisable(true);
        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }

    }

}
