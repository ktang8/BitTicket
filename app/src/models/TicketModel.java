package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import objects.*;

public class TicketModel extends Dao {
	public void updateTicket(Tickets t){
		String table = ticketsTable;
		String updateValues = "lastUpdated='" + t.getLastUpdated() + "', status='" + t.getStatus() + "', title='" + t.getTitle() + 
				"', description='" + t.getDescription() + "', priority=" + t.getPriority() + ", category='" + t.getCategory() + "', assignee='" + t.getAssignee() + "'";
		String where = "tid='" + t.gettID() + "'";
		updateQuery(table, updateValues, where);
	}
	
	public Users getSubmitterUser(String username) {
		return getUser(username);
	}
	
	public List<String> getAllDevs(){
		ResultSet rs = selectQuery(usersTable, "username");
		List<String> usernames = new ArrayList<String>();
		try {
			while(rs.next()){
				usernames.add(rs.getString("username"));
			}
		} catch (SQLException e) {
			System.out.println("Error in getting username: " + e);
		}
		closeConnection();
		return usernames;
	}
}
