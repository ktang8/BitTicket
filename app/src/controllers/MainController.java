package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

public class MainController {
	public static Tickets selectedTicket = new Tickets("20170420141305", "2017-04-20 14:13:05",
			 "2017-04-20 14:13:05", "open", "testTitle", "testDescription",
			 "ktang", 3, "Open");
	
	@FXML
	private Button logout;
	@FXML
	private TableView table;
	
	public void initialize() throws ParseException, SQLException {
		ObservableList<ObservableList> data;
		TableView tableview = new TableView();
		data = FXCollections.observableArrayList();
		MainModel mm = new MainModel();
		try{
			ResultSet rs = mm.selectQuery("w_mei_tickets", "*");
			
			/**********************************
	         * TABLE COLUMN ADDED DYNAMICALLY *
	         **********************************/
	        for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
	            //We are using non property style for making dynamic table
	            final int j = i;                
	            TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
	            col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
	                public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
	                    return new SimpleStringProperty(param.getValue().get(j).toString());                        
	                }                    
	            });
	           
	            table.getColumns().addAll(col); 
	            System.out.println("Column ["+i+"] ");
	        }
	
	        /********************************
	         * Data added to ObservableList *
	         ********************************/
	        while(rs.next()){
	            //Iterate Row
	            ObservableList<String> row = FXCollections.observableArrayList();
	            for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
	                //Iterate Column
	                row.add(rs.getString(i));
	            }
	            System.out.println("Row [1] added "+row );
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
            System.out.println("Error on Building Data");             
        }
		
	}
	
	
	// Event Listener on Button[#logout].onAction
	@FXML
	public void logout(ActionEvent event) {
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
		Stage stage = (Stage) table.getScene().getWindow();
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/views/TicketView.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("failed to go TicketView: " + e);
		}
	}
}
