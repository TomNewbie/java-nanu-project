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
        soundValue.setValue(SoundController.volume * 100);
        difficulty.getItems().addAll(number);
        displayThemes();
        // Image diceImage = new Image(this.getClass()
        // .getResource("/ws2022/assets/Theme/Classic/bird.jpg")
        // .toExternalForm());
        // dice.setImage(diceImage);
        // dice.setFitWidth(100);
        // dice.setFitHeight(100);
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
        String[] pathnames;

        // Creates a new File instance by converting the given pathname string
        // into an abstract pathname
        File f = new File(
                "D:\\coding_Shit\\Project\\java-nanu-project\\src\\main\\resources\\ws2022\\assets\\Theme");

        // Populates the array with names of files and directories
        pathnames = f.list();

        for (String pathname : pathnames) {
            System.out.println(pathname);
        }
        theme.getItems().addAll(pathnames);
    }

    @FXML
    public void setDifficulty() {
        Integer myChoice = difficulty.getValue();
        Dice.numDice = myChoice;
    }

    @FXML
    public void setTheme() {
        String myTheme = theme.getValue();
        GameManager.theme = myTheme;
    }

}
