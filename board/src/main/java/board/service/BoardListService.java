package board.service;

import java.sql.Connection;
import java.util.List;
import static board.persistence.JdbcUtil.*;
import board.domain.BoardDTO;
import board.domain.PageDTO;
import board.persistence.BoardDAO;

public class BoardListService {
	public List<BoardDTO>all(PageDTO pageDto){
		Connection con = getConnection();
		BoardDAO dao = new BoardDAO(con);
		
		List<BoardDTO> boardList = dao.getList(pageDto);
		close(con);
		return boardList;
	}
}
