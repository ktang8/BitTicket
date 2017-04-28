package controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import jdk.nashorn.internal.codegen.CompilerConstants.Call;
import models.TicketModel;
import objects.Tickets;
import objects.Users;
import javafx.scene.control.ListView;

import javafx.scene.control.TextArea;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class TicketController extends ControllerHelper{
	@FXML
	private Label currentFirstName;
	@FXML
	private Button logout;
	@FXML
	private TextField submitter;
	@FXML
	private TextField ticketId;
	@FXML
	private TextField dateCreated;
	@FXML
	private TextField dateUpdated;
	@FXML
	private TextField title;
	@FXML
	private TextArea description;
	@FXML
	private Button back;
	@FXML
	private Button edit;
	@FXML
	private Button deleteTicket;
	@FXML
	private ComboBox<String> priority;
	@FXML
	private ComboBox<String> category;
	@FXML
	private ComboBox<String> status;
	@FXML
	private ComboBox<String> assignee;
	@FXML
	private Label assigneeLabel;
	@FXML
	public void initialize() throws ParseException, SQLException {
		currentFirstName.setText("Hi! " + LoginController.currentUser.getFirstName());
		
		ticketId.setText(MainController.selectedTicket.gettID());
		submitter.setText(MainController.selectedTicket.getSubmitter());
		
		DateFormat original = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = original.parse(MainController.selectedTicket.getDateCreated());
		DateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");
		String newDateFormat = newFormat.format(date);
		dateCreated.setText(newDateFormat);
		
		date = original.parse(MainController.selectedTicket.getLastUpdated());
		dateUpdated.setText(newFormat.format(date));
		
		category.setValue(MainController.selectedTicket.getCategory());
		priority.setValue(MainController.selectedTicket.getPriority()+"");
		status.setValue(MainController.selectedTicket.getStatus());
		title.setText(MainController.selectedTicket.getTitle());
		description.setText(MainController.selectedTicket.getDescription());
		assignee.setValue(MainController.selectedTicket.getAssignee());
		
		TicketModel tm = new TicketModel();
		Users submittedUser =tm.getSubmitterUser(submitter.getText());
		Users currentUser = LoginController.currentUser;
		edit.setDisable(false);
		if((currentUser.getPrivilege() > submittedUser.getPrivilege() || currentUser.getUsername().equals(submittedUser.getUsername())&&currentUser.getPrivilege()>1)){
			//edit.setDisable(false);
			description.setEditable(true);
			title.setDisable(false);
			status.setDisable(false);
			category.setDisable(false);
			priority.setDisable(false);
		}else{
			if(currentUser.getPrivilege()<=1){
				assignee.setVisible(false);
				assigneeLabel.setVisible(false);
				deleteTicket.setVisible(false);
			}
		}
		if(LoginController.currentUser.getPrivilege()>=3){
			assignee.setDisable(false);
			assignee.getItems().addAll(tm.getAllDevs());
			deleteTicket.setDisable(false);
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
		}else if(this.description.getText().length()>1000){
			System.out.println("Description length was greater than 1000 char.");
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
		MainController.selectedTicket.setPriority(Integer.parseInt(priority.getValue()));
		MainController.selectedTicket.setStatus(status.getValue());
		MainController.selectedTicket.setLastUpdated(dateFormat.format(date));
		MainController.selectedTicket.setAssignee(assignee.getValue());
		
		
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText("About to update ticket");
		alert.setContentText("Are you sure you want to update ticket?");
		
		ButtonType YES = new ButtonType("Yes");
		ButtonType NO = new ButtonType("No");
		alert.getButtonTypes().setAll(YES, NO);
		
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get().getText().equals("Yes")){
			TicketModel tm = new TicketModel();
			tm.updateTicket(MainController.selectedTicket);
			if(status.getValue().equals("Closed")){
				alert.setHeaderText("");
				alert.setContentText("Ticket: " + MainController.selectedTicket.gettID() + " has been closed.");
				ButtonType OK = new ButtonType("Ok");
				alert.getButtonTypes().setAll(OK);
				alert.showAndWait();
			}
		} else {
			System.out.println("in else:" + result.get().toString());
		    alert.close();
		}
	}
	
	@FXML
	public void backToMainView() {
		Stage stage = (Stage) back.getScene().getWindow();
		String view = "/views/MainView.fxml";
		changeScene(stage,view);
	}
	
	@FXML
	public void logout() {
		super.logout(logout);
	}
	
	@FXML
	public void deleteTicket(){
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText("About to delete ticket: " + MainController.selectedTicket.gettID());
		alert.setContentText("Are you sure you want to delete ticket?");
		
		ButtonType YES = new ButtonType("Yes");
		ButtonType NO = new ButtonType("No");
		alert.getButtonTypes().setAll(YES, NO);
		
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get().getText().equals("Yes")){
			TicketModel tm = new TicketModel();
			tm.deleteTicket(MainController.selectedTicket);
			backToMainView();
			alert.setHeaderText("");
			alert.setContentText("Ticket: " + MainController.selectedTicket.gettID() + " has been deleted.");
			ButtonType OK = new ButtonType("Ok");
			alert.getButtonTypes().setAll(OK);
			alert.showAndWait();
		} else {
			System.out.println("in else:" + result.get().toString());
		    alert.close();
		}
		
	}

}
