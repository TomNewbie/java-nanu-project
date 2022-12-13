package ws2022.Client.view;

import java.io.IOException;
import javafx.fxml.FXML;
import ws2022.App;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
