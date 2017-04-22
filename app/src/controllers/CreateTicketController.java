package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import models.CreateTicketModel;
import objects.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.IOException;
import java.text.DateFormat;

public class CreateTicketController{
	@FXML
	private Button logout;
	@FXML
	private TextField title;
	@FXML
	private TextArea description;
	@FXML
	private Button back;
	@FXML
	private Button submit;
	@FXML
	private ComboBox<String> priority;
	@FXML
	private ComboBox<String> category;

	@FXML
	public void createTicket(){
		CreateTicketModel ctm = new CreateTicketModel();
		
		String newTitle = this.title.getText();
		String newDescription = this.description.getText();
		int newPriority = Integer.parseInt(this.priority.getValue());
		String newCategory = (String) this.category.getValue();
		
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		DateFormat ticketID = new SimpleDateFormat("yyyyMMddHHmmss");
		
		Tickets newTicket = new Tickets(ticketID.format(date), dateFormat.format(date), dateFormat.format(date), "Open", newTitle,
				newDescription, "ktang", newPriority, newCategory);
		
		ctm.createTicket(newTicket);
	}

	@FXML
	private void ButtonAction(ActionEvent event) throws IOException {
		Stage stage;
		Parent root;
		if (event.getSource() == logout) {
			// get reference to the button's stage
			stage = (Stage) logout.getScene().getWindow();
			// load up OTHER FXML document
			root = FXMLLoader.load(getClass().getResource("/view/LoginView.fxml"));
		} else {
			stage = (Stage) back.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("/view/MainView.fxml"));
		}
		// create a new scene with root and set the stage
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
