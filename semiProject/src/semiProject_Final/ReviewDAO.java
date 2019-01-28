package semiProject_Final;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.DBConn;

public class ReviewDAO {
	private Connection conn = DBConn.getConnection();

	// 푸드트럭 후기 리스트
	public List<ReviewDTO> readReviews(String truckNum) {
		List<ReviewDTO> reviewList = new ArrayList<>();
		ResultSet rs = null;
		PreparedStatement psmt = null;
		String sql = null;

		try {
			sql = "Select id, reviewcontent, reviewday, reviewscore";
			sql += " from tb_review r";
			sql += " JOIN tb_user u ON r.cnum = u.usernum";
			sql += " where tnum = ? order by reviewday desc";

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, truckNum);
			rs = psmt.executeQuery();

			while (rs.next()) {
				ReviewDTO dto = new ReviewDTO();

				dto.setUserid(rs.getString("id"));
				dto.setReviewContent(rs.getString("reviewcontent"));
				dto.setDate(rs.getString("reviewday"));
				dto.setReviewScore(rs.getInt("reviewscore"));

				reviewList.add(dto);
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
		return reviewList;
	}

	public List<ReviewDTO> userReview(String cNum) {
		List<ReviewDTO> list = new ArrayList<>();
		ResultSet rs = null;
		PreparedStatement psmt = null;
		String sql = null;

		try {
			sql = "Select tName, reviewcontent, reviewday, reviewscore";
			sql += " from tb_review r";
			sql += " JOIN tb_foodTruck f ON r.tNum = t.tNum";
			sql += " where cnum = ? order by reviewday desc";

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, cNum);
			rs = psmt.executeQuery();

			while (rs.next()) {
				ReviewDTO dto = new ReviewDTO();

				dto.setUserid(rs.getString("tName"));
				dto.setReviewContent(rs.getString("reviewcontent"));
				dto.setDate(rs.getString("reviewday"));
				dto.setReviewScore(rs.getInt("reviewscore"));

				list.add(dto);
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
		return list;
	}

	public int insertReview(String cNum, String tNum) {
		int result = 0;
		PreparedStatement pstmt = null;
		ReviewDTO rdto = null;
		String sql;

		try {
			sql = "insert into tb_review(reviewNum, cNum, tNum, reviewContent, reviewDay, reviewScore)";
			sql += "values(foodtruck_seq1.NEXTVAL,?,?,?,sysdate,?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cNum);
			pstmt.setString(2, tNum);
			pstmt.setString(3, rdto.getReviewContent());
			pstmt.setInt(4, rdto.getReviewScore());
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.toString());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
				}
			}
		}
		return result;
	}

	public int updateReview(String cNum, String tNum) {
		String sql;
		PreparedStatement pstmt = null;
		int result = 0;
		ReviewDTO rdto = null;

		try {
			sql = "update tb_review set reviewcontent = ?, reviewscore = ?";
			sql += " where cNum=? AND tNum=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rdto.getReviewContent());
			pstmt.setInt(2, rdto.getReviewScore());
			pstmt.setString(3, cNum);
			pstmt.setString(4, tNum);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
				}
			}
		}
		return result;
	}

	public int deleteReview(String cNum, String tNum) {
		PreparedStatement pstmt = null;
		ReviewDTO rdto = null;
		String sql;
		int result = 0;

		try {
			sql = "delete from tb_review where cNum=? AND tNum=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cNum);
			pstmt.setString(2, tNum);
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
				}
			}
		}
		return result;
	}
}
