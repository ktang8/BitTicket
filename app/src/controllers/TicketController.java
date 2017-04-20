package controllers;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

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

}
