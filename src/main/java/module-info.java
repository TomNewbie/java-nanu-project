module ws2022 {
    requires transitive javafx.controls;
    requires transitive javafx.graphics;
    requires transitive javafx.fxml;
    requires javafx.base;
    requires transitive javafx.media;

    opens ws2022;
    opens ws2022.Client.ViewController to javafx.fxml;

    // opens ws2022.Client.ViewController to module javafx.fxml;
    exports ws2022.Client.Model;
    exports ws2022.Client.ViewController;
    exports ws2022;

}
