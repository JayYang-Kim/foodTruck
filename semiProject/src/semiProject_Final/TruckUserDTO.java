package semiProject_Final;

public class TruckUserDTO {
	private String truckNum;  //Ʈ�� ȸ����ȣ
	private String id;        //���̵�
	private String password;  //�н�����
	private String tel;       //��ȭ��ȣ
	private String blaklist;  //���ܿ���: Y�� ���� N�̸� ����X. Default�� N
	
	private String truckName; //��ȣ��
	private String owner;	  //��ǥ��
	private String address;   //Ʈ�� ��ġ
	private String cartegoryCode; //��з��ڵ�
	private String openHour;  //���½ð�
	private String closeHour; //�����ð�	
	private String memo;      //�Խ���
	private String reserveOK; //���డ�� ����
	private double reviewScoreAve; //��� ����(10�� ����, �Ҽ��� ���ڸ� ����)

	public String getTruckNum() {
		return truckNum;
	}

	public void setTruckNum(String truckNum) {
		this.truckNum = truckNum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getBlaklist() {
		return blaklist;
	}

	public void setBlaklist(String blaklist) {
		this.blaklist = blaklist;
	}

	public String getTruckName() {
		return truckName;
	}

	public void setTruckName(String truckName) {
		this.truckName = truckName;
	}
	
	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getCartegoryCode() {
		return cartegoryCode;
	}

	public void setCartegoryCode(String cartegoryCode) {
		this.cartegoryCode = cartegoryCode;
	}

	public String getOpenHour() {
		return openHour;
	}

	public void setOpenHour(String openHour) {
		this.openHour = openHour;
	}

	public String getCloseHour() {
		return closeHour;
	}

	public void setCloseHour(String closeHour) {
		this.closeHour = closeHour;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public double getReviewScoreAve() {
		return reviewScoreAve;
	}

	public void setReviewScoreAve(double reviewScoreAve) {
		this.reviewScoreAve = reviewScoreAve;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getReserveOK() {
		return reserveOK;
	}

	public void setReserveOK(String reserveOK) {
		this.reserveOK = reserveOK;
	}
}
