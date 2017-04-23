package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.LoginModel;
import objects.Users;
import java.io.IOException;

public class LoginController {
	public static Users currentUser;
	
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	@FXML
	private Button login;
	
	@FXML
	public void login() throws IOException {
		String username = this.username.getText();
		String password = this.password.getText();
		
		LoginModel l= new LoginModel();
		currentUser = l.getUserAccount(username);
		
		if (currentUser == null || !currentUser.getPassword().equals(password)){
			System.out.println("Incorrect username or password");
			return;
		}else{
			Stage stage = (Stage) login.getScene().getWindow();
			Parent root;
			try {
				root = FXMLLoader.load(getClass().getResource("/views/MainView.fxml"));
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			} catch (IOException e) {
				System.out.println("failed to go back to MainView: " + e);
				
			}
		}
	}
}
