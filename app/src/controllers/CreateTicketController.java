package controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.CreateTicketModel;
import objects.Tickets;

public class CreateTicketController{
	@FXML
	private Label currentFirstName;
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
	public void initialize() {
		currentFirstName.setText("Hi! " + LoginController.currentUser.getFirstName());
	}
	
	@FXML
	public void createTicket() throws IOException{
		if(this.title.getText().length()>150){
			System.out.println("Title length was greater than 150 char.");
			return;
		}else if(this.title.getText().length()<=0){
			System.out.println("Title length cannot be empty");
			return;
		}else if(this.description.getText().length()>250){
			System.out.println("Description length was greater than 250 char.");
			return;
		}else if(this.description.getText().length()<=0){
			System.out.println("Description length cannot be empty");
			return;
		}
		String newTitle = this.title.getText();
		String newDescription = this.description.getText();
		int newPriority = Integer.parseInt(this.priority.getValue());
		String newCategory = (String) this.category.getValue();
		
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DateFormat ticketID = new SimpleDateFormat("yyyyMMddHHmmss");
		
		Tickets newTicket = new Tickets(ticketID.format(date), dateFormat.format(date), dateFormat.format(date), "Open", newTitle,
				newDescription, LoginController.currentUser.getUsername(), newPriority, newCategory);
		newTicket.setAssignee("");
		CreateTicketModel ctm = new CreateTicketModel();
		ctm.createTicket(newTicket);
		
		MainController.selectedTicket = newTicket;
		toTicketView();
	}
	
	@FXML
	public void backToMainView() {
		Stage stage = (Stage) back.getScene().getWindow();
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/views/MainView.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("failed to go back to MainView: " + e);
			
		}
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
	
	public void toTicketView(){
		Stage stage = (Stage) submit.getScene().getWindow();
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/views/TicketView.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("failed to go to TicketView: " + e);
			
		}	
		
	}
}
