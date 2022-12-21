package ws2022.Client.ViewController;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
    // @FXML
    private TextField usernameField;
    // @FXML
    private PasswordField passwordField;
    private Stage stage;
    private Scene scene;
    private Parent root;

    // @FXML
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

    @FXML
    public static void showAlertMessage(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

    @FXML
    public void switchToSignUp(ActionEvent event) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/ws2022/Client/ViewFx/SignUp.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}