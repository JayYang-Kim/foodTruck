package semiProject_Final;

public class ReservationDTO {
	public String truckNum;   //트럭 회원번호
	public String userNum;    //유저 회원번호
	public String truckName;  //상호명 
	public String userId;	  //유저명 
	public int totalPay;      //결제액
	public int pointUse;      //포인트 사용액
	public String paySort;    //결제구분명 (카드인지 현금인지)
	public String cardName;   //카드명(카드 선택했으면 입력하기)
	public String payDate;    //결제일
	private String menu;      //
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPayDate() {
		return payDate;
	}
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}
	public String getTruckNum() {
		return truckNum;
	}
	public void setTruckNum(String truckNum) {
		this.truckNum = truckNum;
	}
	public String getUserNum() {
		return userNum;
	}
	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}
	public String getTruckName() {
		return truckName;
	}
	public void setTruckName(String truckName) {
		this.truckName = truckName;
	}
	public int getTotalPay() {
		return totalPay;
	}
	public void setTotalPay(int totalPay) {
		this.totalPay = totalPay;
	}
	public String getPaySort() {
		return paySort;
	}
	public void setPaySort(String paySort) {
		this.paySort = paySort;
	}
	public int getPointUse() {
		return pointUse;
	}
	public void setPointUse(int pointUse) {
		this.pointUse = pointUse;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}	
}
