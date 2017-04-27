package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.UserModel;
import objects.Users;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ComboBox;

public class UserController extends ControllerHelper{
	@FXML
	private Text title;
	@FXML
	private Button logout;
	@FXML
	private TextField username;
	@FXML
	private Button back;
	@FXML
	private Button submit;
	@FXML
	private ComboBox privilege;
	@FXML
	private Label currentFirstName;
	@FXML
	private TextField firstName;
	@FXML
	private TextField lastName;
	@FXML
	private TextField email;
	@FXML
	private PasswordField password;
	@FXML
	private Label privLabel;
	@FXML
	private Text passLabel;
	
	public void initialize(){
		Users currentUser = LoginController.currentUser;
		Users selectedUser = UserListController.selectedUser;
		if(currentUser ==null){
			//new user
			privLabel.setVisible(false);
			privilege.setVisible(false);
			submit.setText("Create");
			logout.setVisible(false);
			currentFirstName.setVisible(false);
			
		}else{
			submit.setText("Update");
			passLabel.setVisible(false);
			password.setVisible(false);
			username.setText(selectedUser.getUsername());
			firstName.setText(selectedUser.getFirstName());
			lastName.setText(selectedUser.getLastName());
			privilege.setValue(selectedUser.getPrivilege());
			email.setText(selectedUser.getEmail());
			title.setText("User View");
			
		}
		
	}

	// Event Listener on Button[#logout].onAction
	@FXML
	public void logout() {
		super.logout(logout);
	}
	// Event Listener on Button[#back].onAction
	@FXML
	public void backToUserView(ActionEvent event) {
		Stage stage = (Stage) back.getScene().getWindow();
		String view;
		if(LoginController.currentUser==null){
			view = "/views/LoginView.fxml";
		}else{
			view = "/views/UserListView.fxml";
		}
		changeScene(stage,view);
	}
	// Event Listener on Button[#submit].onAction
	@FXML
	public void createUser(ActionEvent event) throws NoSuchAlgorithmException {
		UserModel um = new UserModel();
		Alert alert = new Alert(AlertType.WARNING);
		ButtonType YES = new ButtonType("Yes");
		ButtonType NO = new ButtonType("No");
		alert.getButtonTypes().setAll(YES, NO);
		
		if(LoginController.currentUser==null){
			alert.setTitle("Creating User");
			alert.setHeaderText("Are you Sure you want to create User?");
			Optional<ButtonType> result = alert.showAndWait();
			
			if (result.get().getText().equals("Yes")){
				Users u = new Users(username.getText(), password.getText(), firstName.getText(), lastName.getText(), 1, email.getText());
				um.addUsers(u);
	
				alert.setTitle("Created new User");
				alert.setHeaderText(u.toString());
				alert.getButtonTypes().setAll(new ButtonType("Ok"));

				alert.showAndWait();
				Stage stage = (Stage) submit.getScene().getWindow();
				String view = "/views/LoginView.fxml";
				changeScene(stage, view);
			}
		} else{
			alert.setTitle("Updating User");
			alert.setHeaderText("Are you Sure you want to update User?");
			Optional<ButtonType> result = alert.showAndWait();
			
			if (result.get().getText().equals("Yes")){
				Users u = new Users(username.getText(), password.getText(), firstName.getText(), lastName.getText(), 1, email.getText());
				um.updateQuery(um.usersTable, "username='" + u.getUsername() + "', pass='" + u.getPassword() + "', firstName='" + u.getFirstName() + "', lastName='" + u.getLastName() + "', privilege=" + u.getPrivilege() + ", email='" + u.getEmail() + "'", "pid=" + UserListController.selectedUser.getuID() + "");
	
				alert.setTitle("Update User");
				alert.setHeaderText(u.toString());
				alert.getButtonTypes().setAll(new ButtonType("Ok"));

				alert.showAndWait();
				Stage stage = (Stage) submit.getScene().getWindow();
				String view = "/views/UserListView.fxml";
				changeScene(stage, view);
			}
		}
	}
}
