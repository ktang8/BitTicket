package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import jdk.nashorn.internal.codegen.CompilerConstants.Call;
import models.TicketModel;
import objects.Tickets;
import objects.Users;
import javafx.scene.control.ListView;

import javafx.scene.control.TextArea;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;

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
	private ComboBox<Integer> priority;
	@FXML
	private ComboBox<String> category;
	
	@FXML
	public void initialize() throws ParseException, SQLException {
		ticketId.setText(MainController.selectedTicket.gettID());
		submitter.setText(MainController.selectedTicket.getSubmitter());
		
		DateFormat original = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = original.parse(MainController.selectedTicket.getDateCreated());
		DateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");
		String newDateFormat = newFormat.format(date);
		dateCreated.setText(newDateFormat);
		
		category.setValue(MainController.selectedTicket.getCategory());
		priority.setValue(MainController.selectedTicket.getPriority());
		title.setText(MainController.selectedTicket.getTitle());
		description.setText(MainController.selectedTicket.getDescription());
		
		TicketModel tm = new TicketModel();
		Users submittedUser =tm.getSubmitterUser(submitter.getText());
		if(LoginController.currentUser.getPrivilege() > submittedUser.getPrivilege() || LoginController.currentUser.getUsername().equals(submittedUser.getUsername())){
			edit.setDisable(false);
			description.setDisable(false);
			title.setDisable(false);
		}
	}
	
	@FXML
	public void clickUpdateButton(){
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
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		MainController.selectedTicket.setTitle(title.getText());
		MainController.selectedTicket.setDescription(description.getText());
		MainController.selectedTicket.setCategory(category.getValue());
		MainController.selectedTicket.setPriority(priority.getValue());
		MainController.selectedTicket.setLastUpdated(dateFormat.format(date));
		
		TicketModel tm = new TicketModel();
		tm.updateTicket(MainController.selectedTicket);
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

}