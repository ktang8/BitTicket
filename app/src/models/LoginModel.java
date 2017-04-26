package models;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

import objects.*;
public class LoginModel extends Dao{
	
	public Users getUserAccount(String username){
		return getUser(username);
	}
	
	public boolean verifyUser(String username, String password) throws NoSuchAlgorithmException{
		startConnection();
		
		//hashes the password before sending the hashed password to compare to the database
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		md.update(password.getBytes());
	    BigInteger hash = new BigInteger(1, md.digest());
	    String hashStr = hash.toString(16);
	    
		String query = "SELECT count(pid) AS verify FROM " + usersTable + " WHERE pass='" + hashStr + "' and username='" + username + "'";
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
