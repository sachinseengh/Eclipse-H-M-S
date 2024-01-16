package application;

import java.net.URL;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
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
	    
	    @FXML
	    private ComboBox<String> checkin_roomno;

	    @FXML
	    private ComboBox<String> checkin_roomtype;

	    @FXML
	    private Label checkin_total;

	    @FXML
	    private Label checkin_totaldays;
	    
	    
	    
	    
	    
	    
	    private String[] comboroomtype={"Single","Double","Triple"};
	    int[] comboroomno = new int[0];

	    
	    public void comboRoomType() {
	    	checkin_roomtype.getItems().addAll(comboroomtype);
	    	
	    }
	    
	    
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
	    
	    
	    
	    
	    public void comboRoomNo() {
	    
	    	
	    	String sql="Select * from room where roomType ='"+(String)checkin_roomtype.getSelectionModel().getSelectedItem()+"' and roomstatus"
	    			+ "='Available' order by  roomno  Asc";
	    	
	    	try {
	    		ObservableList listdata = FXCollections.observableArrayList();
	    		Conn c = new Conn();
	    		ResultSet rs=c.s.executeQuery(sql);
	    		
	    		
	    		
	    		while(rs.next()) {
	    			listdata.add(rs.getInt("roomno"));
	    		}
	    		
	    		checkin_roomno.setItems(listdata);
	    		
	    	}catch(Exception e) {
	    		e.printStackTrace();
	    		
	    	}
	    	
//	    	System.out.print(sql);
	    }
	    
	    String roomno;
	    public void checkin() {
	    	
	     roomno=String.valueOf(checkin_roomno.getValue());
	    	String sql= "insert into customer (customer_id,fname,lname,phone,email,roomtype,roomno,"
	    			+ "checkin,checkout)values('"+ Integer.parseInt(customer_num.getText())+"','"+firstname_txtfield
	    			.getText()+"','"+lastname_textfield.getText()+"','"+phone_txtfield.getText()+"',"
	    					+ "'"+email_txtfield.getText()+"','"+checkin_total.getText()+"','"+(String)checkin_roomtype.getSelectionModel().getSelectedItem()+"',"
	    							+ "'"+roomno+"','"+checkin_date.getValue()+"',"
	    									+ "'"+checkout_date.getValue()+"')";
	    	
	    	
	    String sql2="update room set roomstatus='Unavailable' where roomno='"+roomno+"'";
	    	
	    	
	    	
	    	try {
	    		
	    		
	    	Alert alert = new Alert(AlertType.CONFIRMATION);
	    	alert.setTitle("SuccessFul");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure to checkin ?");
            
            Optional<ButtonType> option = alert.showAndWait();
        	
        	if(option.get().equals(ButtonType.OK)) {
        		
        		Conn c= new Conn();
	    		
	    		c.s.executeUpdate(sql);
	    		
	    		c.s.executeUpdate(sql2);
	    		
	    		
	    		
	    		reset();

	    		
	    		Alert alert2 = new Alert(AlertType.INFORMATION);
		    	alert2.setTitle("SuccessFul");
	            alert2.setHeaderText(null);
	            alert2.setContentText("Successfully Checkin");
	            
	            alert2.showAndWait();
	    		
        	}
	    		
	    	}catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    }
	    
//	    calculation total price
	    int totaldays;
	    public void totalDays() {
	    	Alert alert;
	    	
	    	
	    	if(checkin_date.getValue()!=null) {
	    	if(checkout_date.getValue().isAfter(checkin_date.getValue())) {
	    		
	    		 totaldays=checkout_date.getValue().compareTo(checkin_date.getValue());
	    	}else {
	    		alert = new Alert(AlertType.ERROR);
	    		alert.setTitle("Error Message");
	    		alert.setHeaderText(null);
	    		alert.setContentText("Invalid Check-out date" );
	    		alert.show();
	    		checkout_date.getEditor().clear();
	    	}
	    	}else {
	    		alert = new Alert(AlertType.ERROR);
	    		alert.setTitle("Error Message");
	    		alert.setHeaderText(null);
	    		alert.setContentText("Enter Check-in date" );
	    		alert.show();
	    	}
	    	
	    	
	    }
	    
	    
//	    calculation total price
	    double totalprice;
	    public void totalprice() {
	    	totalDays();
	    	String sql="select * from room where roomno='"+String.valueOf(checkin_roomno.getValue())+"'";
	    	
	    	try {
	    		Conn conn = new Conn();
	    		ResultSet rs=conn.s.executeQuery(sql);
	    		if(rs.next()) {
	    			double total=rs.getDouble("price");
	    			
	    			totalprice=totaldays*total;
	    		}else {
	    			System.out.print("wwwww");
	    		}
	    	}catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    	
	    	checkin_totaldays.setText(String.valueOf(totaldays));
	    	checkin_total.setText("$"+String.valueOf(totalprice));
	    	
	    }
	    
	    
	    
	    public void reset() {
	    	String sql= "insert into customer (customer_id,fname,lname,phone,email,roomtype,roomno,"
	    			+ "checkin,checkout)values('"+ Integer.parseInt(customer_num.getText())+"','"+firstname_txtfield
	    			.getText()+"','"+lastname_textfield.getText()+"','"+phone_txtfield.getText()+"',"
	    					+ "'"+email_txtfield.getText()+"','"+(String)checkin_roomtype.getSelectionModel().getSelectedItem()+"',"
	    							+ "'"+roomno+"','"+checkin_date.getValue()+"',"
	    									+ "'"+checkout_date.getValue()+"')";
	    	
	    	customer_num.setText("");
	    	firstname_txtfield.setText("");
	    	lastname_textfield.setText("");
	    	phone_txtfield.setText("");
	    	email_txtfield.setText("");
	    	checkin_roomtype.getSelectionModel().clearSelection();
	    	checkin_roomno.getSelectionModel().clearSelection();
//	    	checkin_date.setValue(null);
//	    	checkout_date.setValue(null);
	    	checkin_totaldays.setText("");
	    	checkin_total.setText("$0.0");
	    	
	    	
	    }
	    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
       displaycNum();
       comboRoomType();

	}
	
	
}