package member.service;
import static member.persistence.JdbcUtil.*;

import java.sql.Connection;

import member.persistence.MemberDAO;
public class DuplicationService {
	public boolean dup(String userid) {
		Connection con = getConnection();
		MemberDAO dao = new MemberDAO(con);
		boolean dupFlag=dao.dup(userid);
		
		close(con);
		return dupFlag;
	}
}
