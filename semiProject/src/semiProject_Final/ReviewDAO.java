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

	public List<ReviewDTO> readReviews(String truckNum) {
		List<ReviewDTO> reviewList = new ArrayList<>();
		ResultSet rs = null;
		PreparedStatement psmt = null;
		String sql = null;

		try {
			sql = "Select id, reviewcontent, reviewday, reviewscore from tb_review r JOIN tb_user u ON r.cnum = u.usernum  where tnum = ? order by reviewday desc" ;

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

}
