package models;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.ResultSet;
import java.sql.SQLException;

import objects.Dao;
import objects.Users;

public class UserModel extends Dao {

	public ResultSet userRs(){
		
		String sql = "SELECT * FROM w_mei_users ORDER BY pid DESC";
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
