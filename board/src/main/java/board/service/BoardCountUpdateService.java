package board.service;
import static board.persistence.JdbcUtil.*;

import java.sql.Connection;

import board.domain.BoardDTO;
import board.persistence.BoardDAO;
public class BoardCountUpdateService {
	public boolean readUpdate(int bno) {
		Connection con = getConnection();
		BoardDAO dao = new BoardDAO(con);
		boolean updateFlag = dao.readCountUpdate(bno);
		if(updateFlag) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return updateFlag;
		
	}
}
