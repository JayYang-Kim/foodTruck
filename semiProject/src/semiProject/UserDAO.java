package semiProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.util.DBConn;

public class UserDAO {
	private Connection conn = DBConn.getConnection();
	
	public int insertEmployee(UserDTO dto, String userNum, String userCode) {
		int result = 0; 
		PreparedStatement pstmt = null;
		String sql;

		try {
			sql = "INSERT INTO TB_USER(usernum, id, pwd, tel, blacklist, usercode) VALUES(?, ?, ?, ?, ? ,?)";
			
			pstmt = conn.prepareStatement(sql);
			
			// Oracle에서 Index가 1부터 시작이기 때문에 1로 시작
			pstmt.setString(1, userNum);
			pstmt.setString(2, dto.getId());
			pstmt.setString(3, dto.getPassword());
			pstmt.setString(4, dto.getTel());
			pstmt.setString(5, "N");
			pstmt.setString(6, userCode);
			
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {

				}
			}
		}
		
		return result;
	}
}
