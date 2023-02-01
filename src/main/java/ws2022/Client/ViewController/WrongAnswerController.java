package ws2022.Client.ViewController;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ws2022.Client.Model.GameManager;

public class WrongAnswerController {
    @FXML
    private Button nextBtn;
    @FXML
    private Text coverText;
    @FXML
    private ImageView coverImage;
    @FXML
    private Text valueText;
    @FXML
    private Label label;
    @FXML
    private Pane pane;
    SoundController soundc = new SoundController();

    @FXML
    public void initialize() throws FileNotFoundException {

        coverText.setText("The image under " + GameManager.COLOR + " cover is:");
        String selectedImage = "/ws2022/assets/" + GameManager.theme + "/"
                +
                GameManager.getCardImage();
        Image image = new Image(this.getClass()
                .getResource(selectedImage)
                .toExternalForm());
        coverImage.setImage(image);
        coverImage.setFitWidth(150);
        coverImage.setFitHeight(150);
        valueText.setText(GameManager.getAnswer());
    }

    public void closePopUp(ActionEvent event) throws IOException {
        soundc.click();
        Stage mystage = (Stage) pane.getScene().getWindow();
        mystage.close();
        BoardGameController bgc = BoardGameController.getInstance();
        if (GameManager.isOnline) {
            // handle case nay
            GameManager.client.closePopUp("wrong");
        } else {
            GameManager.changeTurn();
            bgc.createRollDiceBtn();
            bgc.setTurn(GameManager.isPlayer1Turn);
        }
    }
}
