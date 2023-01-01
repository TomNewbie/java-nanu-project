package ws2022.Client.ViewController;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ws2022.Client.Model.GameManager;

public class SceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void homeScreen(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/ws2022/Client/ViewFx/HomeScreen.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    String name;

    public void enterProfile1(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ws2022/Client/ViewFx/EnterProfile.fxml"));
        root = loader.load();
        if (GameManager.PLAYER1 != null) {
            EnterProfileController epc = loader.getController();
            epc.displayProfile(GameManager.PLAYER1.getName(), GameManager.PLAYER1.getAge());
        }

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void enterProfile2(ActionEvent event, String tho) throws IOException {
        System.out.println(tho);
        Parent root = FXMLLoader.load(getClass().getResource("/ws2022/Client/ViewFx/EnterProfile2.fxml"));
        // name=
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void enterGame(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/ws2022/Client/ViewFx/boardgame.fxml"));
        // name=
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    Stage mystage;
    @FXML
    private Pane pane;

    public void closeWindow() throws IOException {
        mystage = (Stage) pane.getScene().getWindow();
        mystage.close();
    }

    @FXML
    public void switchToLogin(ActionEvent event) throws IOException {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/ws2022/Client/ViewFx/Login.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
