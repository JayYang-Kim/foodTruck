package semiProject_Final;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.DBConn;

public class UserDAO {
	private Connection conn = DBConn.getConnection();
	//ȸ������
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
	//�α���
	public UserDTO loginUser(String id, String pwd) {
		UserDTO dto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
			sql = "select userNum, id, pwd, tel, blackList, userCode";
			sql += " from user";
			sql += " where id = ? AND pwd = ?";

			pstmt = conn.prepareCall(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();
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
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
				}
			}
		}
		return dto;
	}
	// ȸ������ ��ȸ
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
	// ȸ������ ����
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
	// ����Ʈ ��ȸ
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
	// ȸ�� Ż��
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
	// userNum �ߺ� üũ
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
	// userID �ߺ�üũ
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
	//���ã�� ���
	public UserDTO insertBookmark(String cNum, String tNum) {
		PreparedStatement pstmt = null;		
		StringBuilder sb = new StringBuilder();
		UserDTO udto=null;

		try {
			sb.append("insert into tb_bookmark (cNum,tNum) values (?,?)");

			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, cNum);
			pstmt.setString(2, tNum);
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
		return udto;
	}
	//���ã�� ����
	public UserDTO deleteBookmark(String cNum, String tNum) {
		PreparedStatement pstmt = null;
		String sql = null;
		UserDTO udto = null;
		

		try {
			sql = "delete from tb_bookmark";
			sql += " where cNum = ? AND tNum = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cNum);
			pstmt.setString(2, tNum);
			pstmt.executeUpdate();
			
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
		return udto;
	}
	//���ã�� ���
	public List<UserDTO> printBookmark(String cNum) {
		List<UserDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		UserDTO udto = null;
		StringBuilder sb = new StringBuilder();
		ResultSet rs = null;

		try {
			sb.append("select b.tNum, tName, avgscore, place");
			sb.append(" from tb_bookmark b");
			sb.append(" left outer join tb_foodTruck t on b.tNum = t.tNum");
			sb.append(" left outer join tb_user u on b.cNum = u.userNum");
			sb.append(" where b.cNum = ?");

			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, cNum);
			rs = pstmt.executeQuery();
			list.add(udto);

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
	//����Ȯ��
	public List<ReservationDTO> confirmBookDAO(String cnum){	//������ȣ �޾Ƽ�  ���� ����Ʈ ����
		List<ReservationDTO> list  = new ArrayList<>();
		PreparedStatement pstmt = null;
		StringBuilder sb = new StringBuilder();
		ResultSet rs = null;

		try {
			sb.append("select b.tnum,b.cnum,tname,u.id,nvl(money,0) money,pointuse,");
			sb.append("p.payname,cardcom, to_char(reserveday,'yyyy-mm-dd hhmiss') reserveday");
			sb.append("from tb_reservation b");
			sb.append("left outer join tb_payment p on  b.paycode= p.paycode");
			sb.append("left outer join tb_foodtruck f on b.tnum = f.tnum");
			sb.append("left outer join tb_user u on b.cnum = u.usernum");
			sb.append("left outer join tb_card c on b.reservenum = c.reservenum");
			sb.append("where b.cnum = ?");
			sb.append("order by reserveday");
			
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, cnum);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
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
				
				list.add(dto);	
			}
		} catch (Exception e) {			
		}		
		return list;
	}
	//��ó Ʈ��
	public List<PlaceVO> searchNearTruck(String userAddr){
		List<PlaceVO> tlist = new ArrayList<>();	//��ü Ǫ��Ʈ�� ����Ʈ ��������
		List<PlaceVO> pvlist = new ArrayList<>();	//1km �̳��� Ǫ��Ʈ�� ��������
		PreparedStatement pstmt = null;
		StringBuilder sb = new StringBuilder();
		ResultSet rs = null;
		

		try {
			sb.append("select f.tnum,tname avgscore, place");
			sb.append(" from tb_foodtruck f");
			sb.append(" left outer join tb_analysis a on f.tnum = a.tnum");
		
			pstmt = conn.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				PlaceVO pv = new PlaceVO();
				pv.setTnum(rs.getString("tnum"));
				pv.setTname(rs.getString("tname"));
				pv.setAvgscore(rs.getDouble("avgscore"));
				pv.setPlace("place");
				
				tlist.add(pv);
			}
			MapAPI mapi = new MapAPI();
			pvlist = mapi.searchTruckApi(tlist, userAddr); //1km �̳��� Ǫ��Ʈ�� ��������
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pvlist;
	} 
	//PlaceVO
	public class PlaceVO{
		private String tnum;
		private String tname;
		private double avgscore;
		private String place;
		
		public String getTname() {
			return tname;
		}
		public void setTname(String tname) {
			this.tname = tname;
		}
		public String getTnum() {
			return tnum;
		}
		public void setTnum(String tnum) {
			this.tnum = tnum;
		}
		public double getAvgscore() {
			return avgscore;
		}
		public void setAvgscore(double avgscore) {
			this.avgscore = avgscore;
		}
		public String getPlace() {
			return place;
		}
		public void setPlace(String place) {
			this.place = place;
		}
	}
}
