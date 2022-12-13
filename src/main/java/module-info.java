module ws2022 {
    requires javafx.controls;
    requires javafx.fxml;

    opens ws2022 to javafx.fxml;
    exports ws2022;
}
