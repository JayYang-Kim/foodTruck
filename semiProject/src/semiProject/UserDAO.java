package semiProject;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.util.DBConn;

public class UserDAO {
	private Connection conn = DBConn.getConnection();
	
	// 회원가입
	public int insertUser(UserDTO dto, String userCode) throws SQLException {
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
				dto.setUserNum(rs.getString("userNum"));
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
	public UserDTO searchMyInfo(String userNum) {
		UserDTO udto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "SELECT id, tel FROM tb_user WHERE usernum = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userNum);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				udto = new UserDTO();
				udto.setId(rs.getString("id"));
				udto.setTel(rs.getString("tel"));
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
		
		return udto;
	}
	
	// 회원정보 수정
	public int updateMyInfo(UserDTO dto, String userNum) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {
			sql = "UPDATE tb_user SET pwd = ?, tel = ? WHERE userNum = ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto.getPassword());
			pstmt.setString(2, dto.getTel());
			pstmt.setString(3, userNum);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.toString());
		}catch (Exception e) {
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
	
	// 포인트 조회
	public PointDTO searchMyPoint(String userNum) {
		PointDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "SELECT point, pointdate FROM tb_user tu JOIN tb_point tp ON tu.usernum = tp.cnum WHERE tu.usernum = ?";

			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userNum);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto = new PointDTO();
				dto.setPoint(rs.getInt("point"));
				dto.setDate(rs.getString("pointdate"));
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
	
	// 회원 탈퇴
	public int deleteUser(String userNum) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {
			sql = "DELETE FROM tb_user WHERE usernum = ?";

			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userNum);
			
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

			if (rs.next()) {
				return true;
			} else {
				return false;
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

		return false;
	}

	// userID 중복체크
	public boolean checkUserID(String id) {
		ResultSet rs = null;
		PreparedStatement psmt = null;
		StringBuilder sb = new StringBuilder();

		try {
			sb.append("SELECT id FROM tb_user WHERE id = ? AND usercode = 'USER'");

			psmt = conn.prepareStatement(sb.toString());
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			
			if (rs.next()) {
				return true;
			} else {
				return false;
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

		return false;
	}
}
