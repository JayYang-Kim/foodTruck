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
	private List<String> cartegoryCode = new ArrayList<>(); // CaregoryCode: �з��ڵ�
	private List<String> cartegoryName = new ArrayList<>(); // CaregoryName: �з�����Ʈ
	
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

			//������ ��� �̾ �� �� ������ �о�� ������ ��������.
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
	
	//ī�װ� �޴� ������. 1.�߽� 2.�Ͻ� 3.�ѽ� 4.��� 5.�н�  �䷸��.
	public void showCartegory() {		
		for(int i=0; i<cartegoryName.size(); i++)
			System.out.print(i+1+"."+cartegoryName.get(i)+" ");
		System.out.println();
	}

}
