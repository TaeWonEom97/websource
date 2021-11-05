package pattern.persistence;

import static pattern.persistence.JdbcUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import lombok.AllArgsConstructor;
import pattern.domain.MemberDTO;
@AllArgsConstructor
public class MemberDAO {
	private Connection con;
	public MemberDTO login(MemberDTO dto) {
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		MemberDTO dto1 = null;
		try {
			String sql ="select * from member where userid=? and password=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, dto.getUserid());
			pstmt.setString(2, dto.getPassword());
			rs=pstmt.executeQuery();
			if(rs.next()) {
				dto1= new MemberDTO();
				dto1.setUserid(rs.getString("userid"));
				dto1.setName(rs.getString("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return dto1;
	}
}
