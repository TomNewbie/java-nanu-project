package ws2022.Client.ViewController;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
public class ManualPageController {
   //private Stage stage;
    Scene scene;
    Parent root;
   Stage stage;
  
   SceneController sc = SceneController.getInstance();
   public void returnHome(ActionEvent event) throws IOException{
    sc.homeScreen(event);
   }
   public void switchtomanualpage2(ActionEvent event)throws IOException{
      Parent root = FXMLLoader.load(getClass().getResource("ws2022/fxml/ManualpagePane2.fxml"));
      stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      scene = new Scene(root);
      scene.getStylesheets()
            .add(getClass().getResource("/ws2022/Client/assets/styles/style.css").toExternalForm());
      stage.setScene(scene);
      stage.setScene(scene);
      stage.show();
      // sc.gotomanualpage(event);
    
   }
   public void switchtomanualpage3(ActionEvent event) throws IOException {
     
      Parent root = FXMLLoader.load(getClass().getResource("ws2022/fxml/ManPage3.fxml"));
      stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
      scene = new Scene(root);
      scene.getStylesheets()
            .add(getClass().getResource("/ws2022/Client/assets/styles/style.css").toExternalForm());
      stage.setScene(scene);
      stage.setScene(scene);
      stage.show();
      // sc.gotomanualpage(event);
 
  }
  public void switchtomanualpage4(ActionEvent event) throws IOException {
     
   Parent root = FXMLLoader.load(getClass().getResource("ws2022/fxml/ManualPage4.fxml"));
   stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
   scene = new Scene(root);
   scene.getStylesheets()
         .add(getClass().getResource("/ws2022/Client/assets/styles/style.css").toExternalForm());
   stage.setScene(scene);
   //stage.setScene(scene);
   stage.show();
   // sc.gotomanualpage(event);

}
public void switchtomanualpage5(ActionEvent event) throws IOException {
     
   Parent root = FXMLLoader.load(getClass().getResource("ws2022/fxml/test.fxml"));
   stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
   scene = new Scene(root);
   scene.getStylesheets()
         .add(getClass().getResource("/ws2022/Client/assets/styles/style.css").toExternalForm());
   stage.setScene(scene);
   stage.setScene(scene);
   stage.show();
   // sc.gotomanualpage(event);

}
public void switchtomanualpage6(ActionEvent event) throws IOException {
     
   Parent root = FXMLLoader.load(getClass().getResource("ws2022/fxml/ManualPage6.fxml"));
   stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
   scene = new Scene(root);
   scene.getStylesheets()
         .add(getClass().getResource("/ws2022/Client/assets/styles/style.css").toExternalForm());
   stage.setScene(scene);
   stage.setScene(scene);
   stage.show();
   // sc.gotomanualpage(event);

}
public void switchtomanualpage7(ActionEvent event) throws IOException {
     
   Parent root = FXMLLoader.load(getClass().getResource("ws2022/fxml/ManualPage7.fxml"));
   stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
   scene = new Scene(root);
   scene.getStylesheets()
         .add(getClass().getResource("/ws2022/Client/assets/styles/style.css").toExternalForm());
   stage.setScene(scene);
   stage.setScene(scene);
   stage.show();
   // sc.gotomanualpage(event);

}
public void switchtomanualpage8(ActionEvent event) throws IOException {
     
   Parent root = FXMLLoader.load(getClass().getResource("ws2022/fxml/ManualPage8.fxml"));
   stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
   scene = new Scene(root);
   scene.getStylesheets()
         .add(getClass().getResource("/ws2022/Client/assets/styles/style.css").toExternalForm());
   stage.setScene(scene);
   stage.setScene(scene);
   stage.show();
   // sc.gotomanualpage(event);

}
}
