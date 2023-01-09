package ws2022.Client.ViewController;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
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
    public void initialize() throws FileNotFoundException {

        coverText.setText(GameManager.COLOR);
        String selectedImage = "/ws2022/Client/assets/FootballTheme/"
                +
                GameManager.getCardImage();
        Image image = new Image(this.getClass()
                .getResource(selectedImage)
                .toExternalForm());
        coverImage.setImage(image);
        coverImage.setFitWidth(200);
        coverImage.setFitHeight(200);
        valueText.setText(GameManager.getAnswer());
    }

    public void closePopUp(ActionEvent event) throws IOException {
        GameManager.currentPopUp.close();
        GameManager.changeTurn();
        BoardGameController bgc = BoardGameController.getInstance();
        bgc.setTurn(GameManager.isPlayer1Turn);
    }
}
