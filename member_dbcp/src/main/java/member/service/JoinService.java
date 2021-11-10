package member.service;
import static member.persistence.JdbcUtil.*;

import java.sql.Connection;

import member.domain.MemberDTO;
import member.persistence.MemberDAO;
public class JoinService {
	public boolean register(MemberDTO insertDto) {
		Connection con = getConnection();
		MemberDAO dao = new MemberDAO(con);
		
		boolean insertFlag=dao.insert(insertDto);
		if(insertFlag) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return insertFlag;
	}
}
