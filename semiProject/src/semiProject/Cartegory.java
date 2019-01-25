package semiProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.DBConn;

public class Cartegory {

	private Connection conn = DBConn.getConnection();
	private List<String> cartegoryCode = new ArrayList<>(); // CaregoryCode: 분류코드
	private List<String> cartegoryName = new ArrayList<>(); // CaregoryName: 분류명리스트
	
	public Cartegory() {
		readCartegory();
	}
	
	public List<String> getCartegoryCode() {
		return cartegoryCode;
	}

	public List<String> getCartegoryName() {
		return cartegoryName;
	}

	public void readCartegory() {

		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			String sql = "Select * FROM tb_usermenu";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			//정보가 계속 이어서 들어갈 수 있으니 읽어올 때마다 비워줘야함.
			cartegoryCode.clear();
			cartegoryName.clear();
			
			while (rs.next()) {
				cartegoryCode.add(rs.getString("foodCode"));
				cartegoryName.add(rs.getString("codeName"));			
			}

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (psmt != null) {
				try {
					psmt.close();
				} catch (Exception e) {
					System.out.println(e);
				}

			}
		}
	}
	
	//카테고리 메뉴 보여줌. 1.중식 2.일식 3.한식 4.양식 5.분식  요렇게.
	public void showCartegory() {		
		for(int i=0; i<cartegoryName.size(); i++)
			System.out.print(i+1+"."+cartegoryName.get(i)+" ");
		System.out.println();
	}

}
