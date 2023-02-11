package ws2022.Client.ViewController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import ws2022.Client.Model.Dice;
import ws2022.Client.Model.GameManager;

/**
 * 
 * The MenuController class provides functionality for the menu screen in a
 * game. It has sliders to adjust sound volume,
 * combo boxes to select game difficulty and theme, and buttons to navigate to
 * different parts of the game.
 * 
 */
public class MenuController {
    @FXML
    Slider soundValue;
    @FXML
    Button HomeButton;
    @FXML
    ComboBox<String> theme = new ComboBox<>();
    @FXML
    ComboBox<Integer> difficulty = new ComboBox<>();
    @FXML
    ImageView dice;
    SoundController soundc = new SoundController();
    SceneController sc = SceneController.getInstance();

    @FXML
    public void initialize() throws FileNotFoundException {
        Integer number[] = { 1, 2, 3, 4, 5 };
        difficulty.getItems().addAll(number);
        soundValue.setValue(SoundController.volume * 100);
        displayThemes();
        theme.setPromptText(GameManager.gameLogic.theme);
        difficulty.setPromptText(Dice.numDice + "");
    }

    public void returnHome(ActionEvent event) throws IOException {
        soundc.click();
        sc.homeScreen(event);
    }

    @FXML
    public void updateVolume() {
        Double sound = soundValue.getValue();
        SoundController.volume = sound / 100;
    }

    public void displayThemes() {

        // Creates an array in which we will store the names of files and directories
        String[] themes = { "Classic", "Football" };
        // File directory = new File("target/classes/ws2022/assets/Theme");
        // pathnames = directory.list();

        theme.getItems().addAll(themes);
    }

    @FXML
    public void setDifficulty() {
        Integer myChoice = difficulty.getValue();
        Dice.numDice = myChoice;
    }

    @FXML
    public void setTheme() {
        String myTheme = theme.getValue();
        GameManager.gameLogic.theme = myTheme;
    }

}
