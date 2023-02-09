package ws2022.Client.ViewController;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.Node;

public class ManualOnlineController {
    @FXML
    public ImageView imageView;
    @FXML
    public Label label;
    @FXML
    public Button returnBtn, nextBtn;
    private int count = 0;
    SoundController soundc = new SoundController();
    SceneController sc = SceneController.getInstance();

    String images[] = { "turnOffFireWall.jpg", "chooseRole.jpg", "serverInput.jpg", "serverSuccessful.jpg",
            "clientInput.jpg",
            "clientSuccessful.jpg" };
    String labels[] = {
            "This version can only works in private network. You also need to turn off the firewall in order to be discoverable in the network",
            "Second, after clicking play online, you have to choose to be the server (to control the game) or the client (to play the game).",
            "For server: You have to set up the game. You can choose the theme, the difficulty and the remember time. Then you click on save.",
            "After saving the setting, you will have the IP address.",
            "For client: You have to enter your name and age, the the address on the server and click Enter Game.",
            "The game will announce if you successfully connect. Now you can wait until another player join and start the game. The instructions for game just like in offline game." };

    Stage stage;
    Scene scene;

    public void returnManual(ActionEvent event) throws IOException {
        soundc.click();
        Parent root = FXMLLoader.load(getClass().getResource("/ws2022/fxml/ManualOptions.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setScene(scene);
        stage.show();
    }

    public void initialize() throws FileNotFoundException {
        displayImage(0);
    }

    public void displayImage(Integer count) {
        if (count == 5) {
            nextBtn.setText("End");
        } else {
            nextBtn.setText("Next");
        }
        String selectedImage = "/ws2022/assets/ManualPageImage/" + images[count];
        Image image = new Image(this.getClass()
                .getResource(selectedImage)
                .toExternalForm());
        imageView.setImage(image);
        label.setText(labels[count]);
    }

    @FXML
    public void handleReturn(ActionEvent event) throws IOException {
        soundc.click();
        if (count == 0) {
            returnManual(event);
        } else {
            count--;
            displayImage(count);
        }
    }

    @FXML
    public void handleNext(ActionEvent event) throws IOException {
        soundc.click();
        if (count == 5) {
            returnManual(event);
        } else {
            count++;
            displayImage(count);
        }
    }
}
