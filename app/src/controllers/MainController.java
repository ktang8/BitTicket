package controllers;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.Tab;

import javafx.scene.control.TableColumn;

public class MainController {
	@FXML
	private Button logout;
	@FXML
	private Tab activetickets;
	@FXML
	private TableColumn at_id;
	@FXML
	private TableColumn at_name;
	@FXML
	private TableColumn at_priority;
	@FXML
	private TableColumn at_status;
	@FXML
	private TableColumn at_dateCreated;
	@FXML
	private TableColumn at_createdBy;
	@FXML
	private Tab closedtickets;
	@FXML
	private TableColumn ct_id;
	@FXML
	private TableColumn ct_name;
	@FXML
	private TableColumn ct_priority;
	@FXML
	private TableColumn ct_dateCreated;
	@FXML
	private TableColumn ct_dateClosed;
	@FXML
	private TableColumn ct_createdBy;

}
