package models;

public class LoginModel{
	
	
}

/*
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAO.DBConnection;
 

public class LoginModel extends DBConnection {
	String role;
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Boolean findByUsername(String username) {
		 
		String query = "SELECT * FROM user WHERE username = ?;";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
            	        this.role=resultSet.getString("role");
            			resultSet.getString("password");
                    if (username.equals(resultSet.getString("username"))){
                    	setRole(this.role);
                    	return true;
                    }
            }
        } catch(SQLException e){
            System.out.println("Error Finding User by Username: " + e);
        }
        return false;
    }
}

*/

