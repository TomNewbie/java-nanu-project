// package ws2022.Client.ViewController;

// import javafx.fxml.FXML;
// import javafx.scene.control.ChoiceBox;
// import javafx.scene.text.Text;
// import ws2022.Client.Model.Disc;
// import ws2022.Client.Model.GameManager;

// public class GuessPictureController {
//     @FXML
//     private Text cover;

//     @FXML
//     public ChoiceBox<Disc> choiceBox;

//     public void initialize() {
//         choiceBox.getItems().addAll(GameManager.myList);
//         String color;
//         cover.setText(color + " cover ?");
//     }
// }
package ws2022.Client.ViewController;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ws2022.Client.Model.Disc;
import ws2022.Client.Model.GameManager;

public class GuessPictureController {

    @FXML
    private Text cover;

    @FXML
    private ChoiceBox<String> choicebox = new ChoiceBox<>();
    // public void clickGuessPicture(ActionEvent event, String color) throws
    // IOException {
    // Stage popupwindow = new Stage();

    // popupwindow.initModality(Modality.APPLICATION_MODAL);
    // popupwindow.setTitle("This is a pop up window");

    // Parent popUp = FXMLLoader
    // .load(PopUpController.class.getResource("/ws2022/Client/ViewFx/guessPicture.fxml"));
    // // choiceBox.getItems().addAll(GameManager.myList);
    // cover.setText(color + " cover ?");
    // Scene scene = new Scene(popUp);
    // popupwindow.setScene(scene);

    // popupwindow.showAndWait();
    // }
    public void display(String color) {
        cover.setText(color + " cover ?");
        ArrayList<String> answer = new ArrayList();
        for (Disc i : GameManager.myList) {
            if (!i.checkGuessed()) {

                answer.add(i.getValue());
            }
        }

        choicebox.getItems().addAll(answer);
    }
}
