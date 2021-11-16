package board.action;

import javax.servlet.http.HttpServletRequest;

import board.domain.BoardDTO;
import board.service.BoardCountUpdateService;
import board.service.BoardReadService;
import lombok.AllArgsConstructor;
@AllArgsConstructor
public class BoardReadAction implements BoardAction {
	private String path;
	@Override
	public BoardActionForward execute(HttpServletRequest request) throws Exception {
		// list.do?bno=? bno값 가져오기
		int bno=Integer.parseInt(request.getParameter("bno"));
		
		//조회수 변경
		BoardCountUpdateService updateService = new BoardCountUpdateService();
		updateService.readUpdate(bno);
		//서비스 요청
		BoardReadService service = new BoardReadService();
		BoardDTO dto =service.read(bno);
		request.setAttribute("dto", dto);
		//이동
		return new BoardActionForward(path,false);
	}

}
