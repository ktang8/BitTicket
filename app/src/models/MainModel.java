package models;

import java.sql.ResultSet;
import java.sql.SQLException;

import controllers.LoginController;
import objects.*;

public class MainModel extends Dao{
	
	public ResultSet userRs(String condition){
		
		String sql = "SELECT * FROM w_mei_tickets WHERE "+condition+" ORDER BY pid DESC";
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