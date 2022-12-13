package ws2022.Client.view;

import java.io.IOException;
import javafx.fxml.FXML;
import ws2022.App;

public class SecondaryController {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}