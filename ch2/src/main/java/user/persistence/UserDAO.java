package user.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import user.domain.UserDTO;

public class UserDAO {
	static {
	      try {
	         Class.forName("oracle.jdbc.OracleDriver");
	      } catch (ClassNotFoundException e) {
	         e.printStackTrace();
	      }   
	   }

	public Connection getConnection() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "c##java";
		String password = "12345";
		try {
			Connection con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public UserDTO isLogin(String userid, String password) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserDTO dto = null;

		try {
			con = getConnection();
			String sql = "select * from member where userid=? and password=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto = new UserDTO();
				dto.setUserid(rs.getString("userid"));
				dto.setName(rs.getString("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return dto;
	}

}
