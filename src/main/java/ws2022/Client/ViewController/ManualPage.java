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
  
   public void gotohomescreen(ActionEvent event) throws IOException {
	   Parent root = FXMLLoader.load(getClass().getResource("/ws2022/Client/ViewFx/HomeScreen.fxml"));
	   stage=(Stage)((Node)event.getSource()).getScene().getWindow();
	  
	   scene = new Scene(root);
	   stage.setScene(scene);
	   stage.show();
   }
}
