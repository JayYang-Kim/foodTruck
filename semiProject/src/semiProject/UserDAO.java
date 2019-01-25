package semiProject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.util.DBConn;

public class UserDAO {
	private Connection conn = DBConn.getConnection();
	
	// 회원가입
	public int insertEmployee(UserDTO dto, String userCode) throws SQLException {
		int result = 0; 
		PreparedStatement pstmt = null;
		String sql;

		try {
			sql = "INSERT INTO TB_USER(usernum, id, pwd, tel, blacklist, usercode) VALUES(?, ?, ?, ?, ? ,?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getUserNum());
			pstmt.setString(2, dto.getId());
			pstmt.setString(3, dto.getPassword());
			pstmt.setString(4, dto.getTel());
			pstmt.setString(5, "N");
			pstmt.setString(6, userCode);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw e;
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
	
	// 로그인
	public UserDTO loginUser(String id, String pwd) {
		UserDTO dto = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "select userNum, id, pwd, tel, blackList, userCode";
			sql += " from tb_user";
			sql += " where id = ? AND pwd = ?";

			cstmt = conn.prepareCall(sql);
			cstmt.setString(1, id);
			cstmt.setString(2, pwd);
			
			rs = cstmt.executeQuery();
			
			if (rs.next()) {
				dto = new UserDTO();
				dto.setId(rs.getString("id"));
				dto.setPassword(rs.getString("pwd"));
			}
		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
				}
			}
			if (cstmt != null) {
				try {
					cstmt.close();
				} catch (Exception e2) {
				}
			}
		}

		return dto;
	}
	
	// 회원정보 조회
	public UserDTO searchMyInfo(UserDTO dto) {
		int result = 0; 
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuilder sb=new StringBuilder();

		try {
			sb.append("SELECT id, tel, NVL(point, 0) as point");
			sb.append("	FROM tb_user u");
			sb.append(" LEFT OUTER JOIN tb_point p");
			sb.append(" ON u.usernum = p.cnum");
			sb.append(" WHERE id = ?");
			
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, dto.getId());
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				dto.setUserNum(rs.getString("tel"));
				dto.setPoint(rs.getInt("point"));
			}
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
		
		return dto;
	}
}
