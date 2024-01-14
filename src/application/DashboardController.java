package application;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

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
    private FontAwesomeIcon logout_btn;

    @FXML
    private Button minimize_btn;

	
    private String[] comboroomtype={"Single","Double","Triple"};
    private String[] comboroomstatus= {"Available","UnAvailable"};
    
    public void comboRoomType() {
    	availablerooms_roomtype.getItems().addAll(comboroomtype);
    }
    public void comboRoomStatus() {
    	availablerooms_roomstatus.getItems().addAll(comboroomstatus);
    }
	
	
    public void roomAdd() {
    	
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
    		String sql="insert into room(roomNumber,roomtype,status,price)values('"+availablerooms_roomno.
    				getText()+"','"+(String)availablerooms_roomtype.getSelectionModel().getSelectedItem()+"','"+(String)availablerooms_roomstatus
    				.getSelectionModel().getSelectedItem()+"','"+availablerooms_price.getText()+"')";
    				
    		try {
    			
    			conn.s.executeUpdate(sql);
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
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		comboRoomType();
		comboRoomStatus();
		showData();
	}
	
}
