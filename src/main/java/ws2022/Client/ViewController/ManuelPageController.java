package ws2022.Client.ViewController;

import java.io.IOException;

import javafx.event.ActionEvent;

public class ManuelPageController {
   SceneController sc = SceneController.getInstance();
   public void returnHome(ActionEvent event) throws IOException{
    sc.homeScreen(event);
   }
   
   public void manualPage2(ActionEvent event) throws IOException {
      sc.gotomanualpage(event);
  }

}
