package ws2022.Client.ViewController;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ManualPage {
   private Stage stage;
   private Scene scene;
   private Parent root;
   ImageView myImageView;
   Image ImageOne = new Image(getClass().getResourceAsStream("/ws2022/Client/assets/ManualPageImage/EnterPlayer1.PNG"));
   Image ImageTwo = new Image(getClass().getResourceAsStream("/ws2022/Client/assets/ManualPageImage/EnterPlayer2.PNG"));
   Image ImageThree = new Image(
         getClass().getResourceAsStream("/ws2022/Client/assets/ManualPageImage/SelectPicture.PNG"));
   Image ImageFour = new Image(
         getClass().getResourceAsStream("/ws2022/Client/assets/ManualPageImage/ChooseAnswer.PNG"));
   Image ImageFive = new Image(getClass().getResourceAsStream("/ws2022/Client/assets/ManualPageImage/Wrong.PNG.PNG"));
   Image ImageSix = new Image(getClass().getResourceAsStream("/ws2022/Client/assets/ManualPageImage/Correct.PNG.PNG"));
   Image ImageSeven = new Image(
         getClass().getResourceAsStream("/ws2022/Client/assets/ManualPageImage/LeatherBoard.PNG"));

   public void gotohomescreen(ActionEvent event) throws IOException {
      Parent root = FXMLLoader.load(getClass().getResource("/ws2022/Client/ViewFx/HomeScreen.fxml"));
      stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      myImageView.setImage(ImageOne);
      myImageView.setImage(ImageTwo);
      myImageView.setImage(ImageThree);
      myImageView.setImage(ImageFour);
      myImageView.setImage(ImageFive);
      myImageView.setImage(ImageSix);
      myImageView.setImage(ImageSeven);
      scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
   }
}
