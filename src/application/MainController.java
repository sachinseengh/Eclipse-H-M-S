package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class MainController {
	
	 @FXML
	    private Button exit_button;

	    @FXML
	    private Button login;

	    @FXML
	    private PasswordField password;

	    @FXML
	    private TextField username;

	    @FXML
	    void exit(ActionEvent event) {

	    	
	    	System.exit(0);
	    }

	    @FXML
	    void login(ActionEvent event) {

	    }
}
