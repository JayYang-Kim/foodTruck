package semiProject_Final;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.DBConn;

public class PaySortCode {
	private Connection conn = DBConn.getConnection();
	
	private List<String> paymentCode = new ArrayList<>(); // 결제구분코드
	private List<String> paymentName = new ArrayList<>(); // 결제구분명
	
	public List<String> getPaymentCode() {
		return paymentCode;
	}

	public void setPaymentCode(List<String> paymentCode) {
		this.paymentCode = paymentCode;
	}

	public List<String> getPaymentName() {
		return paymentName;
	}

	public void setPaymentName(List<String> paymentName) {
		this.paymentName = paymentName;
	}

	public void readCartegoryMap() {
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			String sql = "Select * FROM tb_payment";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			if (rs.next()) {
				paymentCode.add(rs.getString("foodCode"));
				paymentName.add(rs.getString("codeName"));
			}

		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (psmt != null) {
				try {
					psmt.close();
				} catch (Exception e) {
					System.out.println(e);
				}

			}
		}
	}
}
