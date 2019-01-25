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

	public List<String> getCartegoryCode() {
		return cartegoryCode;
	}

	public void setCartegoryCode(List<String> cartegoryCode) {
		this.cartegoryCode = cartegoryCode;
	}

	public List<String> getCartegoryName() {
		return cartegoryName;
	}

	public void setCartegoryName(List<String> cartegoryName) {
		this.cartegoryName = cartegoryName;
	}

	public void readCartegoryMap() {

		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			String sql = "Select * FROM tb_user_menu";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			if (rs.next()) {
				cartegoryCode.add(rs.getString("foodCode"));
				cartegoryCode.add(rs.getString("codeName"));
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

	// 카테고리 추가 삭제는 추가하삼.

}
