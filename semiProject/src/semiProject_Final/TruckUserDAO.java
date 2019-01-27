package semiProject_Final;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.util.DBConn;

public class TruckUserDAO {
	private Connection conn = DBConn.getConnection();

	public TruckUserDTO readUser(String id) {
		TruckUserDTO dto = null;
		ResultSet rs = null;
		PreparedStatement psmt = null;
		StringBuilder sb = new StringBuilder();

		try {

			sb.append("SELECT i.*, tname, owner, post, opentime, closetime, foodcode, reserveok ");
			sb.append(
					"  FROM tb_foodtruck t JOIN (Select userNum, id, pwd, tel, blacklist FROM tb_user WHERE id = ?) i ");
			sb.append("  ON t.tnum = i.userNum");

			psmt = conn.prepareStatement(sb.toString());
			psmt.setString(1, id);
			rs = psmt.executeQuery();

			// minzdduck
			// dduckbokki

			if (rs.next()) {
				dto = new TruckUserDTO();

				dto.setId(id);
				dto.setTruckName(rs.getString("tname"));
				dto.setTruckNum(rs.getString("userNum"));
				dto.setPassword(rs.getString("pwd"));
				dto.setBlaklist(rs.getString("blacklist"));
				dto.setTel(rs.getString("tel"));
				dto.setOwner(rs.getString("owner"));
				dto.setCartegoryCode(rs.getString("foodcode"));
				dto.setOpenHour(rs.getString("opentime"));
				dto.setCloseHour(rs.getString("closetime"));
				dto.setMemo(rs.getString("post"));
				dto.setReserveOK(rs.getString("reserveok"));
			}

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
					System.out.println(e2.getMessage());
				}
			}
		}

		return dto;
	}

	// userNum 중복 체크
	public boolean checkUserNum(String userNum) {
		ResultSet rs = null;
		PreparedStatement psmt = null;
		StringBuilder sb = new StringBuilder();

		try {

			sb.append("SELECT userNum FROM tb_user WHERE userNum = ? ");

			psmt = conn.prepareStatement(sb.toString());
			psmt.setString(1, userNum);
			rs = psmt.executeQuery();

			// minzdduck
			// 45852

			if (rs.next())
				return true;
			else
				return false;

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
					System.out.println(e2.getMessage());
				}
			}
		}

		return false;
	}

	// userID 중복체크
	public boolean checkUserID(String id) {
		ResultSet rs = null;
		PreparedStatement psmt = null;
		StringBuilder sb = new StringBuilder();

		try {

			sb.append("SELECT id FROM tb_user WHERE id = ? ");

			psmt = conn.prepareStatement(sb.toString());
			psmt.setString(1, id);
			rs = psmt.executeQuery();

			// minzdduck

			if (rs.next())
				return true;
			else
				return false;

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
					System.out.println(e2.getMessage());
				}
			}
		}

		return false;
	}

	//회원가입 시 트럭 정보 입력
	public boolean insertTruckInfo(TruckUserDTO dto) {
		CallableStatement cstmt = null;
		String sql;

		try {
			sql = "{CALL insertTruckInfo(?,?,?,?,?,?,?)}";

			cstmt = conn.prepareCall(sql);
			cstmt.setString(1, dto.getTruckNum());
			cstmt.setString(2, dto.getId());
			cstmt.setString(3, dto.getPassword());
			cstmt.setString(4, dto.getTruckName());
			cstmt.setString(5, dto.getOwner());
			cstmt.setString(6, dto.getTel());
			cstmt.setString(7, dto.getCartegoryCode());

			cstmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		} finally {
			if (cstmt != null) {
				try {
					cstmt.close();
				} catch (Exception e2) {
					System.out.println(e2);
				}
			}
		}
	}
}