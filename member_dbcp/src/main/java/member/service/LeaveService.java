package member.service;

import java.sql.Connection;

import member.persistence.MemberDAO;

import static member.persistence.JdbcUtil.*;

public class LeaveService {
	public boolean leave(String userid, String current_password) {
		Connection con = getConnection();
		MemberDAO dao = new MemberDAO(con);
		
		boolean deleteFlag = dao.delete(userid, current_password);
		if(deleteFlag) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return deleteFlag;
	}
}
