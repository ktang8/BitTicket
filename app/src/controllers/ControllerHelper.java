package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControllerHelper {
	
	public void changeScene(Stage stage, String view) {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource(view));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("failed to change scene to: "+view+"\n" + e);
		}
	}
	
	public void logout(Button logout){
		Stage stage = (Stage) logout.getScene().getWindow();
		String view = "/views/LoginView.fxml";
		changeScene(stage,view);
		LoginController.currentUser = null;
	}

}
