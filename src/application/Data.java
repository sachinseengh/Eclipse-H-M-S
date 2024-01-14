package application;


public class Data{
	private Integer roomNumber;
	private String  roomType;
	private String status;
	private Double price;
	
	
	
	public Data(Integer roomNumber,String roomType,String status,Double price) {
		this.roomNumber=roomNumber;
		this.roomType=roomType;
		this.status=status;
		this.price=price;
		
	}
	
	
	//to Show in table it matches the name  and set property value check the function name like set PropertyValueFactory("id") it means it call getid function
	//note it ignore case
	
	public Integer getRoomNumber(){
		return roomNumber;
	}
	public String getRoomType() {
		return roomType;
	}
	public String getStatus() {
		return status;
	}
	public Double getPrice() {
		return price;
	}
	
}