package models;

import objects.*;
public class LoginModel extends Dao{
	
	public Users getUserAccount(String username){
		return getUser(username);
	}
}
