package ws2022.Client.ViewController;

import java.io.IOException;

import javafx.event.ActionEvent;

public class HomeScreenController {
    SceneController sc = SceneController.getInstance();

    public void playOffline(ActionEvent event) throws IOException {
        sc.enterProfile1(event);
    }

    public void playOnline(ActionEvent event) throws IOException {
        // sc.enterProfile1(event);
        System.out.println("On progress");
    }

    public void quit(ActionEvent event) throws IOException {
        sc.closeWindow();
    }

    public void manualPage(ActionEvent event) throws IOException {
        sc.gotomanualpage(event);
    }
}
