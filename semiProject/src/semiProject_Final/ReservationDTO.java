package semiProject_Final;

public class ReservationDTO {
	public String truckNum;   //Ʈ�� ȸ����ȣ
	public String userNum;    //���� ȸ����ȣ
	public String truckName;  //��ȣ�� 
	public String userId;	  //������ 
	public int totalPay;      //������
	public int pointUse;      //����Ʈ ����
	public String paySort;    //�������и� (ī������ ��������)
	public String cardName;   //ī���(ī�� ���������� �Է��ϱ�)
	public String payDate;    //������
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
