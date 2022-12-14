package application.Nanu.Login;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
public class LoginController {
    // @FXML
    private TextField usernameField;
    //@FXML
    private PasswordField passwordField;
    //@FXML
    private void loginAction(ActionEvent event) {
        String username = usernameField.getText().trim();
        String password = passwordField.getText();
        if (username.isEmpty()) {
            showAlertMessage(Alert.AlertType.ERROR, "Email Required!",
                    "Please enter your email");
            return;
        }
        if (password.isEmpty()) {
            showAlertMessage(Alert.AlertType.ERROR, "Password Required!",
                    "Please enter your password");
            return;
        }
        showAlertMessage(Alert.AlertType.INFORMATION, "Details sent to database!",
                "Username and password have been sent to database for validation");
    }
    public static void showAlertMessage(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }
}