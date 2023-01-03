package ws2022.Client.ViewController;

import java.io.FileNotFoundException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
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
    public void initialize() throws FileNotFoundException {
        coverText.setText(GameManager.color);
        coverText.setTextAlignment(TextAlignment.CENTER);
        System.out.println(GameManager.myList.get(GameManager.myHashMap.get(GameManager.color)).getCardImage());
        String selectedImage = "/ws2022/Client/assets/FootballTheme/"
                +
                GameManager.myList.get(GameManager.myHashMap.get(GameManager.color)).getCardImage();
        Image image = new Image(this.getClass()
                .getResource(selectedImage)
                .toExternalForm());
        coverImage.setImage(image);
        coverImage.setFitWidth(200);
        coverImage.setFitHeight(200);
        valueText.setText(GameManager.myList.get(GameManager.myHashMap.get(GameManager.color)).getValue());
    }
}
