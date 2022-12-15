module ws2022 {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens ws2022;

    exports ws2022.Client.RunScreen;
    exports ws2022.Client.ViewController;
    exports ws2022.Client.Logic;
    exports ws2022;
}
