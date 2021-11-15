package member.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static member.persistence.JdbcUtil.*;
import member.domain.MemberDTO;
import member.domain.UpdateDTO;

public class MemberDAO {
	private Connection con;
	
	public MemberDAO(Connection con) {
		this.con = con;
	}
	//login => select
	public MemberDTO login(String userid, String password) {
		PreparedStatement pstmt = null;
		MemberDTO loginDto = null;
		ResultSet rs = null;
		try {
			String sql="select userid,name from member where userid=? and password=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setString(2, password);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				loginDto = new MemberDTO();
				loginDto.setUserid(rs.getString(1));
				loginDto.setName(rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return loginDto;
	}
	
	//delete
	public boolean delete(String userid,String current_password) {
		PreparedStatement pstmt=null;
		boolean deleteFlag=false;
		try {
			String sql="delete from member where userid=? and password=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setString(2, current_password);
			int result=pstmt.executeUpdate();
			if(result>0) {
				deleteFlag=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return deleteFlag;
	}
	//비밀번호 변경
	public boolean update(UpdateDTO updateDto) {
		PreparedStatement pstmt=null;
		boolean updateFlag = false;
		try {
			String sql="update member set password=? where userid =? and password=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, updateDto.getNewPassword());
			pstmt.setString(2, updateDto.getUserid());
			pstmt.setString(3, updateDto.getCurrentPassword());
			int result=pstmt.executeUpdate();
			if(result>0) {
				updateFlag=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return updateFlag;
	}
	//회원 가입
	public boolean insert(MemberDTO insertDto) {
		PreparedStatement pstmt = null;
		boolean insertFlag=false;
		try {
			String sql="insert into member values(?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, insertDto.getUserid());
			pstmt.setString(2, insertDto.getPassword());
			pstmt.setString(3, insertDto.getName());
			pstmt.setString(4, insertDto.getGender());
			pstmt.setString(5, insertDto.getEmail());
			int result=pstmt.executeUpdate();
			if(result>0) {
				insertFlag=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return insertFlag;
	}
	public boolean dup(String userid) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean dupFlag = true;
		try {
			String sql="select * from member where userid=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dupFlag=false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return dupFlag;
	}
}
