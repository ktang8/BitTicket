package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.ChoiceBox;
import models.CreateTicketModel;
import objects.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class CreateTicketController extends Dao {
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
	private ChoiceBox priority;
	@FXML
	private ChoiceBox category;

	@FXML
	public void createTicket(){
		String newTitle = this.title.getText();
		String newDescription = this.description.getText();
		int newPriority = this.priority.getValue();
		String newCategory = this.category.getValue();
		
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		DateFormat ticketID = new SimpleDateFormat("yyyyMMddHHmmss");
		
		Ticket newTicket = new Tickets(ticketID.format(date), dateFormat.format(date), dateFormat.format(date), "Open", newTitle,
				newDescription, currentUser.getUsername(), newPriority, String newCategory);
		
		submitTicket(newTicket);
	}
}
