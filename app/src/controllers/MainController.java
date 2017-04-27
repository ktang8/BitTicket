package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
import javafx.stage.Stage;
import javafx.util.Callback;
import models.MainModel;
import objects.Tickets;
import objects.Users;

public class MainController extends ControllerHelper {
	public static Tickets selectedTicket = new Tickets("20170420141305", "2017-04-20 14:13:05",
			 "2017-04-20 14:13:05", "open", "testTitle", "testDescription",
			 "ktang", 3, "Open");
	
	@FXML
	private Button logout;
	@FXML
	private Label currentFirstName;
	@FXML
	private TableView table1;
	@FXML
	private TableView table2;
	@FXML
	private TableView table3;
	@FXML
	private Button toUsers;
	@FXML
	private TabPane tabpane;
	@FXML 
	private Tab tab1;
	@FXML 
	private Tab tab2;
	@FXML 
	private Tab tab3;
	
	@FXML
	private Button submit;
	
	public void initialize() throws ParseException, SQLException {
		//ObservableList<ObservableList> data;
		//TableView tableview = new TableView();
		//data = FXCollections.observableArrayList();
		currentFirstName.setText("Hi! " + LoginController.currentUser.getFirstName());
		MainModel mm = new MainModel();
		try{	
			mm.startConnection();
			Users lc = LoginController.currentUser;
			String conditional;
			if(lc.getPrivilege()>=3){
				toUsers.setDisable(false);
				conditional = "assignee ='"+lc.getUsername()+"'";
				ResultSet assignedRS = mm.userRs(conditional);
				populateTable(table1, assignedRS);
				
				conditional = "NOT status='Closed'";
				ResultSet activeRS = mm.userRs(conditional);
				populateTable(table2, activeRS);
				
				conditional = "status='Closed'";
				ResultSet closedRS = mm.userRs(conditional);
				populateTable(table3, closedRS);
			}else{
				toUsers.setVisible(false);
				conditional = "submitter = '"+lc.getUsername()+"' AND NOT status ='Closed'";
				ResultSet userRS = mm.userRs(conditional);
				populateTable(table1, userRS);
				
				conditional ="submitter = '"+lc.getUsername()+"' AND status='Closed'";
				ResultSet closedRs = mm.userRs(conditional);
				populateTable(table2,closedRs);
				tab1.setText("My Tickets");
				tab2.setText("Closed Tickets");
				
				tabpane.getTabs().remove(tab3);
				//tabpane.getTabs().remove(3);			
			}
	        
		}catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");             
        }finally{
        	mm.closeConnection();
        }
		
	}
	
	
	// Event Listener on Button[#logout].onAction
	@FXML
	public void logout(ActionEvent event) {
		super.logout(logout);
	}
	
	@FXML
	public void submit() {
		Stage stage = (Stage) submit.getScene().getWindow();
		String view = "/views/CreateTicketView.fxml";
		changeScene(stage,view);
	}
	public void populateTable(TableView table, ResultSet rs){
		ObservableList<ObservableList> data = FXCollections.observableArrayList();
		MainModel mm = new MainModel();
		try{
			for(int i=1 ; i<rs.getMetaData().getColumnCount(); i++){
	            //We are using non property style for making dynamic table
	            final int j = i;
	            if(!rs.getMetaData().getColumnName(i+1).equals("description")){
		            TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
		            col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
		                public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
		                    return new SimpleStringProperty(param.getValue().get(j).toString());                        
		                }                    
		            });
		            table.getColumns().addAll(col);
	            }
			}
		
			while(rs.next()){
		            //Iterate Row
		            ObservableList<String> row = FXCollections.observableArrayList();
		            for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
		                //Iterate Column
		                row.add(rs.getString(i));
		            }
		            //System.out.println("Row [1] added "+row );
		            data.add(row);
		        }
		        //FINALLY ADD TO TableView
		        table.setItems(data);
		        
		        table.setRowFactory(tv -> {
		            TableRow<ObservableList<String>> row = new TableRow<>();
		            row.setOnMouseClicked(event -> {
		                if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY 
		                     && event.getClickCount() == 2) {
	
		                	ObservableList<String>  clickedRow = row.getItem();
		                    System.out.println(clickedRow.get(1));
		                	selectedTicket = mm.getTicket(clickedRow.get(1));
		                	toTicketView();
		                    
		                }
		            });
		            return row ;
		        });
		}catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data"+ rs.toString());             
        }
	}
	public void toUserList(){
		Stage stage = (Stage) toUsers.getScene().getWindow();
		String view = "/views/UserListView.fxml";
		changeScene(stage,view);
		
	}
	public void toTicketView(){
		Stage stage = (Stage) logout.getScene().getWindow();
		String view = "/views/TicketView.fxml";
		changeScene(stage,view);
	}
}