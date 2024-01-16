package application;

public class CustomerData {
	
	private Integer customer_no;
	private String fname;
	private String lname;
	private String phone;
	private String email;
	private String checkin;
	private String checkout;
	public CustomerData(int custno,String fname,String lname,String phone,String email,String Checkin,String checkout) {
		this.customer_no=custno;
		this.fname=fname;
		this.lname=lname;
		this.phone=phone;
		this.email=email;
	this.checkin=Checkin;
		this.checkout=checkout;
	}
	
	public int getCustomerNo() {
		return customer_no;
	}
	
	public String getFname() {
		return fname;
	}
	public String getLname() {
		return lname;
	}
	public String getPhone() {
		return phone;
		
	}
	public String getEmail() {
		return email;
	}
	public String getCheckin() {
		return checkin;
	}
	public String getCheckout() {
		return checkout;
	}
	
	
}