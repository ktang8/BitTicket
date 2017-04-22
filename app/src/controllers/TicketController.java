package controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.ListView;

import javafx.scene.control.TextArea;

import javafx.scene.control.ChoiceBox;

public class TicketController {
	@FXML
	private Button logout;
	@FXML
	private TextField submitter;
	@FXML
	private TextField ticketId;
	@FXML
	private TextField dateCreated;
	@FXML
	private TextField title;
	@FXML
	private TextArea description;
	@FXML
	private ListView comments;
	@FXML
	private Button back;
	@FXML
	private Button edit;
	@FXML
	private Button addcomment;
	@FXML
	private ChoiceBox priority;
	@FXML
	private ChoiceBox category;
	
	@FXML
	public void clickUpdateButton(){
		
	}
	
	@FXML
	public void logout() {
		Stage stage = (Stage) logout.getScene().getWindow();
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/views/LoginView.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("failed to logout: " + e);
		}finally{
			LoginController.currentUser = null;
		}
	}

}
