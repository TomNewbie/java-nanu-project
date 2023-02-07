package ws2022.Client.ViewController;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * HomeScreenController class is responsible for handling the home screen of the
 * application.
 * It has four buttons to represent the user's choices: play offline, play
 * online, quit, and manual.
 * 
 * The class has four methods, playOffline(), playOnline(), quit(), and
 * manualPage(),
 * to handle the button events and navigate to the corresponding screens.
 * The class also has two instance variables, sc and soundc, for accessing the
 * SceneController and SoundController instances, respectively.
 * 
 * 
 */
public class HomeScreenController {
    @FXML
    private Pane pane;
    SceneController sc = SceneController.getInstance();
    SoundController soundc = new SoundController();

    public void playOffline(ActionEvent event) throws IOException {
        soundc.click();
        sc.enterProfile1(event);
    }

    public void playOnline(ActionEvent event) throws IOException {
        // sc.enterProfile1(event);
        soundc.click();
        sc.chooseRole(event);
    }

    public void quit(ActionEvent event) throws IOException {
        soundc.click();
        Stage mystage = (Stage) pane.getScene().getWindow();
        mystage.close();
    }

    Stage stage;
    Scene scene;
    Parent root;

    public void manualPage(ActionEvent event) throws IOException {
        soundc.click();
        Parent root = FXMLLoader.load(getClass().getResource("/ws2022/fxml/ManualPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setScene(scene);
        stage.show();
    }

    public void setting(ActionEvent event) throws IOException {
        soundc.click();
        Parent root = FXMLLoader.load(getClass().getResource("/ws2022/fxml/Menu.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setScene(scene);
        stage.show();
    }

}
