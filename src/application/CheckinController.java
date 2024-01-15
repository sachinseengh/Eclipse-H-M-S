package application;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class CheckinController implements Initializable{
	
	
	
	
	   @FXML
	    private AnchorPane Checkin_form;

	    @FXML
	    private Button checkin_btn;
	    
	    @FXML
	    
	    private Label customer_num;

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
	    
	    
	    
	    
	    
	    int cnum;
	    public void customerNum() {
	    	Conn c = new Conn();
	    	
	    	try {
	    		String sql= "select count(customer_id) from customer";
	    		ResultSet rs = c.s.executeQuery(sql);
	    		
	    		
	    		if(rs.next()) {
	    			cnum= rs.getInt(1);
	    		}
	    	
	    		
	    	}catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    
	    }
	   
	   
	    public void displaycNum() {
	    	
	    	customerNum();
	    	cnum++;
	    	customer_num.setText(String.valueOf(cnum));;
	    }
	    
	    
	    public void close() {
	    	System.exit(0);
	    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
       displaycNum();
	}
	
	
}