package ws2022.Client.ViewController;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class WhichColor {

    @FXML
    public ListView<String> List;
    String pictures[] = { "yellow","green","blue","orange","red","joker"};

    @FXML
    public void initialize() {
        // TODO Auto-generated method stub
        List.getItems().addAll(pictures);
    }
}
