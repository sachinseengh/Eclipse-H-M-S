package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class CheckinController implements Initializable{
	
	
	
	
	   @FXML
	    private AnchorPane Checkin_form;

	    @FXML
	    private Button checkin_btn;

	    @FXML
	    private DatePicker checkin_date;

	    @FXML
	    private DatePicker checkout_date;

	    @FXML
	    private Button close_btn;

	    @FXML
	    private TextField email_txtfield;

	    @FXML
	    private TextField firstname_txtfield;

	    @FXML
	    private TextField lastname_textfield;

	    @FXML
	    private TextField phone_txtfield;

	    @FXML
	    private Button receipt_btn;

	    @FXML
	    private Button reset_btn;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	
}