package board.service;

import board.domain.BoardDTO;
import board.persistence.BoardDAO;

import static board.persistence.JdbcUtil.*;

import java.sql.Connection;
public class BoardReplyService {
	public boolean reply(BoardDTO dto) {
		Connection con = getConnection();
		BoardDAO dao = new BoardDAO(con);
		//replyUpdate
		dao.replyUpdate(dto);
		commit(con);
		
		//insertUpdate
		boolean insertFlag = dao.replyInsert(dto);
		if(insertFlag) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return insertFlag;
	}
}
