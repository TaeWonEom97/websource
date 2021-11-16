package board.service;

import board.domain.BoardDTO;
import board.persistence.BoardDAO;

import static board.persistence.JdbcUtil.*;

import java.sql.Connection;
public class BoardInsertService {
	public boolean boardInsert(BoardDTO boardDto) {
		Connection con = getConnection();
		BoardDAO dao = new BoardDAO(con);
		
		boolean insertFlag = dao.insert(boardDto);
		if(insertFlag) {
			commit(con);
		}else {
			rollback(con);
		}
		close(con);
		return insertFlag;
	}
}
