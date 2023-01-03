package ws2022.Client.ViewController;

import java.io.FileNotFoundException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import ws2022.Client.Model.GameManager;

public class WrongAnswerController {
    @FXML
    private Button nextBtn;
    private Text coverText;
    private ImageView coverImg;

    @FXML
    public void initialize() throws FileNotFoundException {
        coverText.setText("The image under " + GameManager.color + " is: ");
        String selectedImage = "/ws2022/Client/assets/FootballTheme/"
                +
                GameManager.myList.get(GameManager.myHashMap.get(GameManager.color)).getImage();
        Image image = new Image(this.getClass()
                .getResource(selectedImage)
                .toExternalForm());
        Circle clip = new Circle(50, 50, 45);
        coverImg.setImage(image);
        coverImg.setFitWidth(100);
        coverImg.setFitHeight(100);
        coverImg.setClip(clip);
    }
}
