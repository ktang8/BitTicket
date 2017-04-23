package models;

import objects.*;

public class CreateTicketModel extends Dao{
	public void createTicket(Tickets t){
		submitTicket(t);
	}
}
