package ws2022.Client.ViewController;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ws2022.Client.Model.GameManager;

public class SceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    Stage mystage;
    @FXML
    private Pane pane;
    String name;

    private static SceneController sc;

    private SceneController() {
    }

    public static SceneController getInstance() {
        if (SceneController.sc == null) {
            sc = new SceneController();
        }
        return sc;
    }

    public Stage createScene(ActionEvent event, String name) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/ws2022/fxml/" + name + ".fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setScene(scene);
        stage.show();
        return stage;
    }

    // Create scene with loader
    public void createScene(ActionEvent event, FXMLLoader loader) throws IOException {
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void gotomanualpage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/ws2022/fxml/ManualPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setScene(scene);
        stage.show();
    }

    public void homeScreen(ActionEvent event) throws IOException {
        createScene(event, "HomeScreen");
    }

    // public void leaderboard(Stage stage) throws IOException {
    // root =
    // FXMLLoader.load(getClass().getResource("/ws2022/fxml/Leaderboard.fxml"));
    // Scene scene = new Scene(root);
    // stage.setScene(scene);
    // stage.setScene(scene);
    // stage.show();
    // }

    public void loadSceneByStage(Stage stage, String name) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/ws2022/fxml/" + name + ".fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setScene(scene);
        stage.show();
    }

    public void enterProfile1(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ws2022/fxml/EnterProfile.fxml"));
        root = loader.load();
        if (GameManager.PLAYER1 != null) {
            EnterProfileController epc = loader.getController();
            epc.displayProfile(GameManager.PLAYER1.getName(),
                    GameManager.PLAYER1.getAge());
        }
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void enterProfile2(ActionEvent event, String tho) throws IOException {
        createScene(event, "EnterProfile2");
    }

    public void enterGame(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ws2022/fxml/boardgame.fxml"));
        loader.setController(BoardGameController.getInstance());
        createScene(event, loader);
    }

    public void enterProfileOnline(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ws2022/fxml/EnterProfileOnl.fxml"));
        loader.setController(EnterProfileOnlController.getInstance());
        createScene(event, loader);
    }

    public void enterGameOnline(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ws2022/fxml/boardgameOnl.fxml"));
        loader.setController(BoardGameController.getInstance());
        scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
        // System.out.println("game start");
    }

    public void closeWindow() throws IOException {
        mystage = (Stage) pane.getScene().getWindow();
        mystage.close();
    }

    @FXML
    public void switchToLogin(ActionEvent event) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/ws2022/fxml/Login.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void showAlertMessage(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

}
