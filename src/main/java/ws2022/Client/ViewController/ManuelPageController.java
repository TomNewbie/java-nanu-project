package ws2022.Client.ViewController;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ManuelPageController {
   private Stage stage;
   private Scene scene;
   private Parent root;
   Stage mystage;
  
   SceneController sc = SceneController.getInstance();
   public void returnHome(ActionEvent event) throws IOException{
    sc.homeScreen(event);
   }
   
 
}
