package application;
	
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import objects.Dao;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {			
			//load LoginView
			FXMLLoader loader = new  FXMLLoader(getClass().getResource("/views/LoginView.fxml"));
            AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("BitTicket");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		Scanner scan = new Scanner(System.in);
		System.out.println("Console Controller");
		System.out.println("Select an Option");
		System.out.println("D| Display Duration of All Tickets.");
		System.out.println("R| Display Ratio of Closed Ticket to Open Ticket.");
		System.out.println("H| Display Tickets that are currently Open with High Priority.");
		System.out.println("Q| Quit/End program");
		String userInput =scan.next();
		switch(userInput){
			case "d":
			case "D":
				displayTicketDurations();
				break;
			case "r":
			case "R":
			case "h":
			case "H":
			case "q":
			case "Q":
				return;
			default:
				System.out.println("Option: " + userInput + " not recognized.");
				break;
			
		}
	}
	
	public static void displayTicketDurations() {
		Dao d = new Dao();
		ResultSet rs = d.getAllTickets(d.ticketsTable, "tid, dateCreated, lastUpdated");
		String ticketID;
		String dateCreated;
		String lastUpdated;
		DateFormat original = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			while(rs.next()){
				ticketID = rs.getString("tid");
				dateCreated = rs.getString("dateCreated");
				lastUpdated = rs.getString("lastUpdated");
				Date startDate = original.parse(dateCreated);
				Date lastDate = original.parse(lastUpdated);
				long milliseconds = lastDate.getTime() - startDate.getTime();
				if(milliseconds == 0){
					System.out.println("TicketID: " + ticketID + "\tno update since the date ticket was created, " + startDate);
				}else{
					Calendar c = Calendar.getInstance();
					c.setTimeInMillis(milliseconds);
					System.out.println("TicketID: " + ticketID + "\t" + (c.get(Calendar.YEAR)-1970) + " years, " + c.get(Calendar.MONTH) + " months, " + (c.get(Calendar.DAY_OF_MONTH)-1) + " days, " + c.get(Calendar.HOUR) + " hours, " + c.get(Calendar.MINUTE) + " minutes, " + c.get(Calendar.SECOND) + " seconds from start to last updated.");
				}
			}
		} catch (SQLException e) {
			System.out.println("Problem reading in ticket: " + e);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
