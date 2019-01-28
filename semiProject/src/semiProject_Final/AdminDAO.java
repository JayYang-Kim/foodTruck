package semiProject_Final;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.DBConn;

public class AdminDAO {
	private Connection conn = DBConn.getConnection();
	
	//로그인
	public AdminDTO loginUser(String id, String pwd) {
		AdminDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "select userNum, id, pwd, tel, blackList, userCode";
			sql += " from tb_user";
			sql += " where id = ? AND pwd = ? AND userCode = 'ADMIN'";

			pstmt = conn.prepareCall(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				dto = new AdminDTO();
				dto.setId(rs.getString("userNum"));
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
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}
		}
		
		return dto;
	}
	
	// 유저 리스트 조회
	public List<UserDTO> searchUser() {
		List<UserDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "select userNum, id, pwd, tel, blackList, userCode";
			sql += " from tb_user";
			sql += " where userCode = 'USER'";

			pstmt = conn.prepareCall(sql);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				UserDTO dto = new UserDTO();
				
				dto.setUserNum(rs.getString("userNum"));
				dto.setId(rs.getString("id"));
				dto.setPassword(rs.getString("pwd"));
				dto.setTel(rs.getString("tel"));
				dto.setBlacklist(rs.getString("blackList"));
				
				list.add(dto);
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
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}
		}
		
		return list;
	}
	
	// 점주 리스트 조회
	public List<UserDTO> searchFoodTruck() {
		List<UserDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "select userNum, id, pwd, tel, blackList, userCode";
			sql += " from tb_user";
			sql += " where userCode = 'TRUCK'";

			pstmt = conn.prepareCall(sql);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				UserDTO dto = new UserDTO();
				
				dto.setUserNum(rs.getString("userNum"));
				dto.setId(rs.getString("id"));
				dto.setPassword(rs.getString("pwd"));
				dto.setTel(rs.getString("tel"));
				dto.setBlacklist(rs.getString("blackList"));
				
				list.add(dto);
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
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}
		}
		
		return list;
	}
}
