package models;

import java.sql.ResultSet;
import java.sql.SQLException;

import objects.*;
public class LoginModel extends Dao{
	
	public Users getUserAccount(String username){
		return getUser(username);
	}
	
	public boolean verifyUser(String username, String password){
		startConnection();
		String query = "SELECT count(pid) AS verify FROM " + usersTable + " WHERE pass=SHA('" + password + "') and username='" + username + "'";
		ResultSet rs;
		try {
			rs = stmt.executeQuery(query);
			if(rs.next()){
				if(rs.getInt("verify") == 1){
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error in verify password: " + e);
		}
		return false;
		
	}
}
