package semiProject_Final;

public class UserDTO {
	private String userNum;      //���� ȸ����ȣ
	private String id;	         //���� ���̵�
	private String password;	 //���� ��й�ȣ
	private String tel;	         //���� ��ȭ��ȣ
	private String blacklist;    //���� ���ܿ��� (Y�� ����, N�̸� ��������, Default�� N)
	private int point;           //����Ʈ �Ѿ�
	
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
