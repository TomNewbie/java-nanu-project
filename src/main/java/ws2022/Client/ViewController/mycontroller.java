package ws2022.Client.ViewController;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class mycontroller {
   private Stage stage;
   private Scene scene;
   private Parent root;
	public void homescreen(ActionEvent event) throws IOException{
		Parent root=FXMLLoader.load(getClass().getResource("/ws2022/Client/ViewFx/newhomescrre.fxml"));
		stage=(Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	String name;
	public void enterprofile(ActionEvent event) throws IOException{
		Parent root=FXMLLoader.load(getClass().getResource("/ws2022/Client/ViewFx/enterprofile.fxml"));
		//name=
		stage=(Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void highscore(ActionEvent event) throws IOException{
		Parent root=FXMLLoader.load(getClass().getResource("/ws2022/Client/ViewFx/highscore.fxml"));
		stage=(Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	 Stage mystage;
	@FXML
	private AnchorPane scenepane;
	public void closethewindow(ActionEvent event) throws IOException{
		mystage=(Stage) scenepane.getScene().getWindow();
		mystage.close();
	}

}
