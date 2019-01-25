package semiProject;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class UserDTO {
	private String userNum;     //���� ȸ����ȣ
	private String id;	        //���� ���̵�
	private String password;	//���� ��й�ȣ
	private String tel;	        //���� ��ȭ��ȣ
	private String blacklist;	//���� ���ܿ��� (Y�� ����, N�̸� ��������, Default�� N)
	private int point;       	//����Ʈ �Ѿ�
	
	private List<String> bookMark = new LinkedList<>();			//�ϸ�ũ, truckNum(Ʈ�� ȸ����ȣ) ����
	private Map<Integer, ReviewDTO> review = new HashMap<>();	//���� , reviewNum�� Ű��
	
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
	public List<String> getBookMark() {
		return bookMark;
	}
	public void setBookMark(List<String> bookMark) {
		this.bookMark = bookMark;
	}
	public Map<Integer, ReviewDTO> getReview() {
		return review;
	}
	public void setReview(Map<Integer, ReviewDTO> review) {
		this.review = review;
	}
}
