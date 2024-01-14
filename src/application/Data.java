package application;


public class Data{
	private Integer room_no;
	private String  room_type;
	private String room_status;
	private Integer room_price;
	
	
	
	public Data(Integer room_no,String room_type,String room_status,Integer room_price) {
		this.room_no=room_no;
		this.room_type=room_type;
		this.room_status=room_status;
		this.room_price=room_price;
		
	}
	
	public Integer getRoomNo(){
		return room_no;
	}
	public String getRoomType() {
		return room_type;
	}
	public String getRoomStatus() {
		return room_status;
	}
	public Integer getRoomPrice() {
		return room_price;
	}
	
}