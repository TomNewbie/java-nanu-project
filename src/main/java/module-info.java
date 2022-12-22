module ws2022 {
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens ws2022;
    opens ws2022.Client.ViewController to javafx.fxml;

    // opens ws2022.Client.ViewController to module javafx.fxml;
    exports ws2022.Client.Model;
    exports ws2022.Client.RunScreen;
    exports ws2022.Client.ViewController;
    exports ws2022.Client.Logic;
    exports ws2022;

}
