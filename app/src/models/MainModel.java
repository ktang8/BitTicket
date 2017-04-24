package models;

import java.sql.ResultSet;
import java.sql.SQLException;

import controllers.LoginController;
import objects.*;

public class MainModel extends Dao{

	public ResultSet assignedRs(){
		LoginController lc = new LoginController();
		Users cu = lc.currentUser;
		String name = cu.getUsername();
		String sql = "SELECT * FROM w_mei_tickets WHERE assignee ='"+name+"' ORDER BY pid DESC";
		ResultSet rs = null;
		try{
			//startConnection();
			System.out.println("Running select query...");
			rs = stmt.executeQuery(sql);
			
			//rs will be closed later after it is printed out.
			System.out.println("Query completed...");
		} catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return rs;
	}		
	
	public ResultSet activeRs(){
		String sql = "SELECT * FROM w_mei_tickets WHERE status='Open' OR status='In Progress' OR status='On Hold' ORDER BY pid DESC";
		ResultSet rs = null;
		try{
			System.out.println("Running select query...");
			rs = stmt.executeQuery(sql);
			//rs will be closed later after it is printed out.
			System.out.println("Query completed...");
		} catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return rs;
	}
	
	public ResultSet closedRs(){
		String sql = "SELECT * FROM w_mei_tickets WHERE status='Closed' ORDER BY pid DESC";
		ResultSet rs = null;
		try{
			System.out.println("Running select query...");
			rs = stmt.executeQuery(sql);
			//rs will be closed later after it is printed out.
			System.out.println("Query completed...");
		} catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return rs;
	}
}