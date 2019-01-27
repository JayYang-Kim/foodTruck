package semiProject_Final;

public class UserDTO {
	private String userNum;      //유저 회원번호
	private String id;	         //유저 아이디
	private String password;	 //유저 비밀번호
	private String tel;	         //유저 전화번호
	private String blacklist;    //유저 차단여부 (Y면 차단, N이면 차단해제, Default는 N)
	private int point;           //포인트 총액
	
	public String getUserNum() {
		return userNum;
	}
	public void setUserNum(String userNum) {
		this.userNum = userNum;
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
	public String getBlacklist() {
		return blacklist;
	}
	public void setBlacklist(String blacklist) {
		this.blacklist = blacklist;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
}
