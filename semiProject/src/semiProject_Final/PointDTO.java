package semiProject_Final;

public class PointDTO {

	private int point; //포인트 사용(음수), 적립(양수)
	private String date; //사용하거나 적립한 날짜
	
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
