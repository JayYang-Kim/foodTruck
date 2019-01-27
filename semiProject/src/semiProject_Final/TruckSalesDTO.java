package semiProject_Final;

public class TruckSalesDTO {
	private String date;     //날짜
	private String address;  //위치
	private int netProfit;   //총매출
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getNetProfit() {
		return netProfit;
	}
	public void setNetProfit(int netProfit) {
		this.netProfit = netProfit;
	}	
}
