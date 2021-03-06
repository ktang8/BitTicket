package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import javafx.util.Callback;
import models.MainModel;
import models.UserModel;
import objects.Tickets;
import objects.Users;

public class UserListController extends ControllerHelper {
	public static Users selectedUser=null;
	@FXML
	private Button logout;
	@FXML
	private Button createUser;
	@FXML
	private Label currentFirstName;
	@FXML
	private TableView userTable;
	@FXML 
	private Button back;

	public void initialize() throws ParseException, SQLException {
		
		currentFirstName.setText("Hi! " + LoginController.currentUser.getFirstName());
		UserModel um = new UserModel();
		try{	
			um.startConnection();
			Users lc = LoginController.currentUser;
			
			ResultSet userRS = um.userRs();
			populateTable(userTable,userRS);
			
	        
		}catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");             
        }finally{
        	um.closeConnection();
        }
	}

public void populateTable(TableView table, ResultSet rs){
	ObservableList<ObservableList> data = FXCollections.observableArrayList();
	UserModel um = new UserModel();
	try{
		for(int i=1 ; i<rs.getMetaData().getColumnCount(); i++){
            //We are using non property style for making dynamic table
            final int j = i;
           
            TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
            col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
                public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
                    return new SimpleStringProperty(param.getValue().get(j).toString());                        
                }                    
            });
            table.getColumns().addAll(col);
        
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
	                	selectedUser = um.getUser(clickedRow.get(1));
	                	toUserView();
	                    
	                }
	            });
	            return row ;
	        });
	}catch(Exception e){
        e.printStackTrace();
        System.out.println("Error on Building Data"+ rs.toString());             
    }
}
	// Event Listener on Button[#logout].onAction
	@FXML
	public void logout() {
		super.logout(logout);
	}
	// Event Listener on Button[#createUser].onAction
	@FXML
	public void createUser(ActionEvent event) {
		UserListController.selectedUser = null;
		Stage stage = (Stage) createUser.getScene().getWindow();
		String view = "/views/UserView.fxml";
		changeScene(stage,view);
	}
	
	public void toUserView(){
		Stage stage = (Stage) logout.getScene().getWindow();
		String view = "/views/UserView.fxml";
		changeScene(stage,view);
	}
	
	@FXML
	public void back() {
		Stage stage = (Stage) back.getScene().getWindow();
		String view = "/views/MainView.fxml";
		changeScene(stage,view);
	}
}
