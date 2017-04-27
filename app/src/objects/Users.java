package objects;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Users {
	private int uID;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private int privilege;
	private String email;

	public Users() {
	}

	public Users(String username, String password, String firstName, String lastName, int privilege, String email) throws NoSuchAlgorithmException {
		this.username = username;
		this.password = hashPassword(password);
		this.firstName = firstName;
		this.lastName = lastName;
		this.privilege = privilege;
		this.email = email;
	}

	public int getuID() {
		return uID;
	}

	public void setuID(int uID) {
		this.uID = uID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getPrivilege() {
		return privilege;
	}

	public void setPrivilege(int privilege) {
		this.privilege = privilege;
	}
	
	public String getEmail(){
		return email;
	}
	public void setEmail(String email){
		this.email=email;
	}
	public String hashPassword(String pass) throws NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		md.update(pass.getBytes());
	    BigInteger hash = new BigInteger(1, md.digest());
	    String hashStr = hash.toString(16);
	    return hashStr;
	}

	@Override
	public String toString() {
		return "Users [uID=" + uID + ", username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", privilege=" + privilege + ",email= "+email+"]";
	}
}
