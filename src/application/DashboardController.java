package application;

import java.net.URL;
import java.util.ResourceBundle;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    private ComboBox<?> availablerooms_roomstatus;

    @FXML
    private ComboBox<?> availablerooms_roomtype;

    @FXML
    private TextField availablerooms_searchtxtfield;

    @FXML
    private TableColumn<?, ?> availablerooms_tablecol_price;

    @FXML
    private TableColumn<?, ?> availablerooms_tablecol_roomno;

    @FXML
    private TableColumn<?, ?> availablerooms_tablecol_roomstatus;

    @FXML
    private TableColumn<?, ?> availablerooms_tablecol_roomtype;

    @FXML
    private TableView<?> availablerooms_tableview;

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

	
	
	
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
}
