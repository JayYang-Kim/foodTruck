package semiProject_Final;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
					"  FROM tb_foodtruck t JOIN (Select userNum, id, pwd, tel, blacklist FROM tb_user WHERE id = ? AND usercode = 'TRUCK') i ");
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
		String sql = null;

		try {

			sql = "SELECT userNum FROM tb_user WHERE userNum = ? ";

			psmt = conn.prepareStatement(sql);
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
		String sql = null;

		try {

			sql = "SELECT id FROM tb_user WHERE id = ? AND usercode = 'TRUCK'";

			psmt = conn.prepareStatement(sql);
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

	// 회원가입 시 트럭 정보 입력
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

	public int open(Map<String, Object> analysisData) {
		PreparedStatement psmt = null;
		String sql = null;
		int rs = 0;

		try {
			sql = "INSERT INTO tb_analysis (tnum, openTime, place, sale) VALUES (?, SYSDATE, ?, 0)";

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, (String) analysisData.get("truckNum"));
			psmt.setString(2, (String) analysisData.get("place"));
			rs = psmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (psmt != null) {
				try {
					psmt.close();
				} catch (Exception e2) {
					System.out.println(e2);
				}
			}
		}
		
		return rs;
	}

	public boolean move(Map<String, Object> analysisData) {
		CallableStatement cstmt = null;
		String sql;

		try {
			sql = "{CALL insertMove(?,?,?)}";

			cstmt = conn.prepareCall(sql);
			cstmt.setString(1, (String) analysisData.get("truckNum"));
			cstmt.setInt(2, (int) analysisData.get("sale"));
			cstmt.setString(3, (String) analysisData.get("place"));

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

	public boolean close(Map<String, Object> analysisData) {
		CallableStatement cstmt = null;
		String sql;

		try {
			sql = "{CALL insertClose(?,?)}";

			cstmt = conn.prepareCall(sql);
			cstmt.setString(1, (String) analysisData.get("truckNum"));
			cstmt.setInt(2, (int) analysisData.get("sale"));
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

	public int deleteUser(String userNum) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {
			sql = "UPDATE tb_user SET userCode = 'DELETE' WHERE userNum = ?";

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

	// 예약여부 변경
	public int updateReservation(TruckUserDTO loginUserdto) {

		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {
			sql = "UPDATE TB_FOODTRUCK SET reserveOk= ? WHERE tNum = ? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginUserdto.getReserveOK());
			pstmt.setString(2, loginUserdto.getTruckNum());

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

	// 공지사항 수정
	public int updateMemo(TruckUserDTO dto) {

		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {
			sql = "UPDATE TB_FOODTRUCK SET post = ? WHERE tNum = ? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getMemo());
			pstmt.setString(2, dto.getTruckNum());

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

	// 트럭유저 정보 보기
	public TruckUserDTO showUserInfo(String TruckNum) {
		TruckUserDTO dto = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		StringBuilder sb = new StringBuilder();

		try {
			sb.append("SELECT id, tel, tName, owner");
			sb.append(" FROM TB_USER");
			sb.append(" LEFT OUTER JOIN TB_FOODTRUCK ON");
			sb.append(" TB_USER.userNum= TB_FOODTRUCK.tNum");
			sb.append(" WHERE TB_USER.userNum = ? ");

			pstmt = conn.prepareStatement(sb.toString());

			pstmt.setString(1, TruckNum);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto = new TruckUserDTO();
				dto.setId(rs.getString("id"));
				dto.setTel(rs.getString("tel"));
				dto.setTruckName(rs.getString("tName"));
				dto.setOwner(rs.getString("owner"));
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

	// 트럭유저 정보 수정
	public int updateUserInfo(TruckUserDTO dto) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql;

		try {
			sql = "UPDATE TB_USER SET pwd = ?, tel = ? WHERE userNum = ? ";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto.getPassword());
			pstmt.setString(2, dto.getTel());
			pstmt.setString(3, dto.getTruckNum());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.toString());
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

	// 예약 정보 확인
	public List<ReservationDTO> confirmBookDAO(String num) { // 유저번호, 트럭번호 받아서 예약 리스트 리턴
		List<ReservationDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		StringBuilder sb = new StringBuilder();
		ResultSet rs = null;

		try {
			sb.append("select b.tnum,b.cnum,tname,id,nvl(money,0) money,pointuse,");
			sb.append("payname,cardcom, to_char(reserveday,'yyyy-mm-dd hhmiss') reserveday, reservemenu");
			sb.append("from tb_reservation b");
			sb.append("left outer join tb_payment p on  b.paycode= p.paycode");
			sb.append("left outer join tb_foodtruck f on b.tnum = f.tnum");
			sb.append("left outer join tb_user u on b.cnum = u.usernum");
			sb.append("left outer join tb_card c on b.reservenum = c.reservenum");
			sb.append("left outer join tb_reservemenu m on b.reservenum = m.reservenum");
			sb.append("where b.tnum = ?");
			sb.append("order by reserveday desc");

			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, num);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ReservationDTO dto = new ReservationDTO();
				dto.setTruckNum(rs.getString("tnum"));
				dto.setUserNum(rs.getString("cnum"));
				dto.setTruckName(rs.getString("tname"));
				dto.setUserId(rs.getString("id"));
				dto.setTotalPay(rs.getInt("money"));
				dto.setPointUse(rs.getInt("pointuse"));
				dto.setPaySort(rs.getString("payname"));
				dto.setCardName(rs.getString("cardcom"));
				dto.setPayDate(rs.getString("reserveday"));
				dto.setMenu(rs.getString("reservemenu"));

				list.add(dto);
			}
		} catch (Exception e) {
		}
		
		return list;
	}
}