package models;

import objects.*;

public class TicketModel extends Dao {
	public void updateTicket(Tickets t){
		String table = ticketsTable;
		String updateValues = "lastUpdated='" + t.getLastUpdated() + "', status='" + t.getStatus() + "', title='" + t.getTitle() + 
				"', description='" + t.getDescription() + "', priority=" + t.getPriority() + ", category='" + t.getCategory() + "'";
		String where = "tid='" + t.gettID() + "'";
		updateQuery(table, updateValues, where);
	}
	
	public Users getSubmitterUser(String username) {
		return getUser(username);
	}
}
