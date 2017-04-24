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
	
	public List<String> getAllUsers(){
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
	
	public Popup createPopup(final String message) {
	    final Popup popup = new Popup();
	    popup.setAutoFix(true);
	    popup.setAutoHide(true);
	    popup.setHideOnEscape(true);
	    Label label = new Label(message);
	    label.setOnMouseReleased(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent e) {
	            popup.hide();
	        }
	    });
	    label.getStyleClass().add("popup");
	    popup.getContent().add(label);
	    return popup;
	}

	public void showPopupMessage(final String message, final Stage stage) {
	    final Popup popup = createPopup(message);
	    popup.setOnShown(new EventHandler<WindowEvent>() {
	        @Override
	        public void handle(WindowEvent e) {
	            popup.setX(stage.getX() + stage.getWidth()/2 - popup.getWidth()/2);
	            popup.setY(stage.getY() + stage.getHeight() - popup.getWidth());
	        }
	    });        
	    popup.show(stage);
	}
}
