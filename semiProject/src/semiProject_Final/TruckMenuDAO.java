package semiProject_Final;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.DBConn;

public class TruckMenuDAO {
	private Connection conn = DBConn.getConnection();

	public List<TruckMenuDTO> showFoodMenu(String TruckNum) {

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		List<TruckMenuDTO> list = new ArrayList<>();
		TruckMenuDTO mdto = null;
		try {
			sql = "SELECT menuName, price, explain FROM TB_MENU WHERE tNum = ? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, TruckNum);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				mdto = new TruckMenuDTO();

				mdto.setMenuName(rs.getString("menuName"));
				mdto.setPrice(rs.getInt("price"));
				mdto.setAboutMenu(rs.getString("explain"));

				list.add(mdto);
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			if (pstmt != null) {
				try {
					rs.close();
				} catch (Exception e2) {
				}
			}

		}
		
		return list;
	}

	public int insertMenu(TruckMenuDTO mdto, String tNum) throws SQLException {
		int result = 0;
		PreparedStatement pstmt = null;
		StringBuilder sb = new StringBuilder();

		try {
			sb.append("INSERT INTO TB_MENU (menuCode, tNum, menuName, price, explain)");
			sb.append(" VALUES (seq1.NEXTVAL, ?, ?, ?, ?) ");

			pstmt = conn.prepareStatement(sb.toString());

			pstmt.setString(1, tNum);
			pstmt.setString(2, mdto.getMenuName());
			pstmt.setInt(3, mdto.getPrice());
			pstmt.setString(4, mdto.getAboutMenu());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw e;
		} catch (NumberFormatException e) {
			throw e;

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

	public int updateMenu(TruckMenuDTO mdto) {
		int result = 0;
		PreparedStatement pstmt = null;
		StringBuilder sb = new StringBuilder();

		try {
			sb.append("UPDATE TB_MENU SET menuName=?, price=?, explain=?");
			sb.append(" WHERE menuCode = ?");

			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, mdto.getMenuName());
			pstmt.setInt(2, mdto.getPrice());
			pstmt.setString(3, mdto.getAboutMenu());
			pstmt.setInt(4, mdto.getMenuCode()); // 20190129 Check »çÇ×

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

	public int readMenuCode(String menuName, String TruckNum) {

		PreparedStatement pstmt = null;
		String sql = null;
		ResultSet rs = null;

		try {
			sql = "SELECT menuCode FROM TB_MENU WHERE menuName = ? AND tNum = ? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, menuName);
			pstmt.setString(2, TruckNum);
			rs = pstmt.executeQuery();

			if (rs.next())
				return rs.getInt("menuCode");

		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			if (pstmt != null) {
				try {
					rs.close();
				} catch (Exception e2) {
				}
			}

		}
		
		return -1;
	}

	public int deleteMenu(String menuName, String TruckNum) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			sql = "DELETE FROM TB_MENU WHERE menuName = ? AND tNum = ? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, menuName);
			pstmt.setString(2, TruckNum);
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
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
