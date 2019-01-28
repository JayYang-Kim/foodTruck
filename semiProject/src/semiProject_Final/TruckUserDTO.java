package semiProject_Final;

public class TruckUserDTO {
	private String truckNum;  //트럭 회원번호
	private String id;        //아이디
	private String password;  //패스워드
	private String tel;       //전화번호
	private String blaklist;  //차단여부: Y면 차단 N이면 차단X. Default는 N
	
	private String truckName; //상호명
	private String owner;	  //대표자
	private String address;   //트럭 위치
	private String cartegoryCode; //대분류코드
	private String openHour;  //오픈시간
	private String closeHour; //마감시간	
	private String memo;      //게시판
	private String reserveOK; //예약가능 여부
	private double reviewScoreAve; //평균 평점(10점 만점, 소수점 한자리 가능)

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
