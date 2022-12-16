package ws2022.Client.ViewController;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class WhichColor {

    @FXML
    public ListView<String> List;
    String pictures[] = { "doll", "cherry", "bike", "frog", "elephant", "Fish", "ballon", "teddy", "present", "shoes",
            "boat", "ball", "cow", "cup", "parrot", "snake", "apple", "plane", "kite", "skaters", "tree", "cat",
            "drum" };

    @FXML
    public void initialize() {
        // TODO Auto-generated method stub
        List.getItems().addAll(pictures);
    }
}
