package application;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;

public class DashboardController implements Initializable{

	
	
	@FXML
    private Label Username;

    @FXML
    private Button availableroom_deletebtn;

    @FXML
    private Button availablerooms_addbtn;

    @FXML
    private Button availablerooms_btn;

    @FXML
    private Button availablerooms_checkin;

    @FXML
    private Button availablerooms_clearbtn;

    @FXML
    private AnchorPane availablerooms_form;

    @FXML
    private TextField availablerooms_price;

    @FXML
    private TextField availablerooms_roomno;

    @FXML
    private ComboBox<String> availablerooms_roomstatus;

    @FXML
    private ComboBox<String> availablerooms_roomtype;

    @FXML
    private TextField availablerooms_searchtxtfield;

    @FXML
    private TableColumn<Data, Integer> availablerooms_tablecol_price;

    @FXML
    private TableColumn<Data, Integer> availablerooms_tablecol_roomno;

    @FXML
    private TableColumn<Data, String> availablerooms_tablecol_roomstatus;

    @FXML
    private TableColumn<Data, String> availablerooms_tablecol_roomtype;

    @FXML
    private TableView<Data> availablerooms_tableview;

    @FXML
    private Button availablerooms_update;

    @FXML
    private FontAwesomeIcon close_btn;

    @FXML
    private Button customer_btn;

    @FXML
    private AnchorPane customer_form;

    @FXML
    private TextField customer_searchfield;

    @FXML
    private TableColumn<?, ?> customer_tablecol_checkin;

    @FXML
    private TableColumn<?, ?> customer_tablecol_checkout;

    @FXML
    private TableColumn<?, ?> customer_tablecol_customerno;

    @FXML
    private TableColumn<?, ?> customer_tablecol_firstname;

    @FXML
    private TableColumn<?, ?> customer_tablecol_lastname;

    @FXML
    private TableColumn<?, ?> customer_tablecol_phoneno;

    @FXML
    private TableColumn<?, ?> customer_tablecol_totalpayment;

    @FXML
    private TableView<?> customer_tableview;

    @FXML
    private AreaChart<?, ?> dashboard_areachart;

    @FXML
    private Button dashboard_btn;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private Label dashboard_todaybooktxt;

    @FXML
    private Label dashboard_todayincometxt;

    @FXML
    private Label dashboard_totalincome;

   

    @FXML
    private Button minimize_btn;
    
    @FXML
    private Button logout_btn;

	
    private String[] comboroomtype={"Single","Double","Triple"};
    private String[] comboroomstatus= {"Available","UnAvailable"};
    
    public void comboRoomType() {
    	availablerooms_roomtype.getItems().addAll(comboroomtype);
    }
    public void comboRoomStatus() {
    	availablerooms_roomstatus.getItems().addAll(comboroomstatus);
    }
	
	
    
    
    
    //Adding room
    public void roomAdd() throws SQLException {
    	
    	if(availablerooms_roomno.getText().isEmpty()||availablerooms_roomtype.getSelectionModel().isEmpty()
    			|| availablerooms_roomstatus.getSelectionModel().
    			isEmpty()|| availablerooms_price.getText().isEmpty()) {
    		
    		
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Fill all the Blank fields");
            alert.showAndWait();
    	}else {
    		Conn conn= new Conn();
    		//first check if the room already exist or not
    		
    		String sql = "Select * from room where roomNumber='"+availablerooms_roomno.getText()+"'";
    		
    		ResultSet rs = null;
    		try {
    		
    		rs= conn.s.executeQuery(sql);
    		}catch(Exception e) {
    			
    			e.printStackTrace();
    		}
    		
    		if(rs.next()) {
    			Alert alert = new Alert(AlertType.ERROR);
        		alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Room Already Exists");
                alert.showAndWait();
    		}else {
    	
    		
    		
    		
    		String sql2="insert into room(roomNumber,roomtype,status,price)values('"+availablerooms_roomno.
    				getText()+"','"+(String)availablerooms_roomtype.getSelectionModel().getSelectedItem()+"','"+(String)availablerooms_roomstatus
    				.getSelectionModel().getSelectedItem()+"','"+availablerooms_price.getText()+"')";
    				
    		try {
    			
    			conn.s.executeUpdate(sql2);
    			Alert alert = new Alert(AlertType.INFORMATION);
        		alert.setTitle("SuccessFul");
                alert.setHeaderText(null);
                alert.setContentText("Room Added Successfully");
                alert.show();
                
                
                
                
                
                clearData();
                showData();
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    		}
    	}
    }
    
    //clear Data
    public void clearData() {
    	 
    	availablerooms_roomno.setText("");
    	availablerooms_roomtype.getSelectionModel().clearSelection();
    	availablerooms_roomstatus.getSelectionModel().clearSelection();
    	availablerooms_price.setText("");
    	
    	
    		
    }
    
//  for Table data
    ObservableList<Data> listdata;
    public ObservableList<Data> dataList(){
        Conn conn= new Conn();
        
        listdata= FXCollections.observableArrayList();
        
        String sql = "select * from room";
        
        try{
            
            ResultSet result = conn.s.executeQuery(sql);
            
            Data data;
            
            while(result.next()){
                data = new Data(result.getInt("roomNumber"),result.getString("roomtype"),result.getString("status"),result.getDouble("price"));
                
                listdata.add(data);
            }
        }catch(Exception e){
           e.printStackTrace(); 
        }
        
        return listdata;
        
        
    }
    
////    To show data
    public void showData(){
    	 ObservableList<Data> showList = dataList();
        
    	 availablerooms_tablecol_roomno.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
    	    availablerooms_tablecol_roomtype.setCellValueFactory(new PropertyValueFactory<>("roomType"));
    	    availablerooms_tablecol_roomstatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    	    availablerooms_tablecol_price.setCellValueFactory(new PropertyValueFactory<>("price"));

    	    // Set the items of the TableView to the populated ObservableList
    	    availablerooms_tableview.setItems(showList);
    	   
       
    }
    
    
    //TO SELECT TABLE DATA
    
    
    
//    Add this function to the mouseclick of table view
    
    public void AvailableselectData() {
    	Data data = availablerooms_tableview.getSelectionModel().getSelectedItem();
    	
    	int num = availablerooms_tableview.getSelectionModel().getSelectedIndex();
    	
    	if((num-1)<-1)
    		return;
    	
    	availablerooms_roomno.setText(String.valueOf(data.getRoomNumber()));
    	availablerooms_roomtype.setValue(data.getRoomType());
    	 	
    	availablerooms_roomstatus.setValue(data.getStatus());
    	availablerooms_price.setText(String.valueOf(data.getPrice()));
    
    }
	
    private double x =0;
    private double y=0;
    
    //logout functionality
    public void logout() {
    	
    	
    	
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Confirm logout");
        alert.setHeaderText(null);
        alert.setContentText("Do you want to logout?");
//      alert.showAndWait();
    	
    	Optional<ButtonType> option = alert.showAndWait();
    	
    	if(option.get().equals(ButtonType.OK)) {
    	
    	try {
    	Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
    	Stage stage = new Stage();
    	Scene scene= new Scene(root);
    	
    	
    	
    	
    	

		root.setOnMousePressed(new EventHandler<MouseEvent>(){
	           @Override
	           public void handle(MouseEvent event) {
	               x= event.getSceneX();
	               y= event.getSceneY();
	           }

	         });

	         root.setOnMouseDragged(new EventHandler<MouseEvent>(){
	           @Override
	           public void handle(MouseEvent event) {
	              stage.setX(event.getScreenX()-x);
	              stage.setY(event.getScreenY()-y);
	              
	              stage.setOpacity(0.9);
	           }

	         });
	         
	         root.setOnMouseReleased(new EventHandler<MouseEvent>(){
	           @Override
	           public void handle(MouseEvent event) {
	              stage.setOpacity(1);
	           }

	         });
    	
    	
    	
    	
    	
    	logout_btn.getScene().getWindow().hide();
	         
	         
    	stage.setScene(scene);
    	stage.initStyle(StageStyle.TRANSPARENT);
    	stage.show();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}}
    }
	
    //update functionality
    
    public void update() {
    	if(availablerooms_roomno.getText().isEmpty()||availablerooms_roomtype.getSelectionModel().isEmpty()
    			|| availablerooms_roomstatus.getSelectionModel().
    			isEmpty()|| availablerooms_price.getText().isEmpty()) {
    		
    		
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Fill all the Blank fields");
            alert.showAndWait();
    	}else {
    		Conn conn= new Conn();
    		
    		
    		String sql="update room set roomtype='"+(String)availablerooms_roomtype.getSelectionModel().getSelectedItem()+"',"
    				+ "status='"+(String)availablerooms_roomstatus.getSelectionModel().getSelectedItem()+"'"
    						+ ",price='"+availablerooms_price.getText()+"' where roomNumber='"+availablerooms_roomno.getText()+"'";
    				

    				
    		try {
    			
    			conn.s.executeUpdate(sql);
    			
    			
    			Alert alert = new Alert(AlertType.INFORMATION);
        		alert.setTitle("SuccessFul");
                alert.setHeaderText(null);
                alert.setContentText("Room Updated Successfully");
                alert.show();
                
                
                
                
                
                clearData();
                showData();
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}
    	
    	
    }
    
    public void delete() {
    	if(availablerooms_roomno.getText().isEmpty()) {
    		
    		
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Enter the Room Number");
            alert.showAndWait();
            
    	}else {
    		

			Alert alert1 = new Alert(AlertType.CONFIRMATION);
    		alert1.setTitle("SuccessFul");
            alert1.setHeaderText(null);
            alert1.setContentText("Are you sure to delete the room ?");
            
            Optional<ButtonType> option = alert1.showAndWait();
        	
        	if(option.get().equals(ButtonType.OK)) {
           
    		Conn conn= new Conn();
    		
    		
    		String sql="DELETE FROM ROOM where roomNumber='"+availablerooms_roomno.getText()+"'";
    				

    				
    		try {
    			
    			conn.s.executeUpdate(sql);
    			
    			Alert alert2 = new Alert(AlertType.INFORMATION);
        		alert2.setTitle("Room Deleted");
                alert2.setHeaderText(null);
                alert2.setContentText("Room Delted Successfully");
                alert2.show();
                clearData();
                showData();
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	}}
    }
   
    
    
    
    //checkin form
    private double x1 =0;
    private double y1=0;
    public void checkinform() throws IOException {
    	try {
    	Parent root = FXMLLoader.load(getClass().getResource("Checkin.fxml"));
    	
    	Stage stage= new Stage();
    	
    	
    	root.setOnMousePressed(new EventHandler<MouseEvent>(){
	           @Override
	           public void handle(MouseEvent event) {
	               x1= event.getSceneX();
	               y1= event.getSceneY();
	           }

	         });

	         root.setOnMouseDragged(new EventHandler<MouseEvent>(){
	           @Override
	           public void handle(MouseEvent event) {
	             stage.setX(event.getScreenX()-x1);
	              stage.setY(event.getScreenY()-y1);
	              
	              stage.setOpacity(0.9);
	           }

	         });
	         
	         root.setOnMouseReleased(new EventHandler<MouseEvent>(){
	           @Override
	           public void handle(MouseEvent event) {
	        	   stage.setOpacity(1);
	           }

	         });
    	
	         stage.initStyle(StageStyle.UNDECORATED);
    	Scene scene= new Scene(root);
    	stage.setScene(scene);
    	stage.show();
    	
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		comboRoomType();
		comboRoomStatus();
		showData();
	}
	
}
