package board.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import board.domain.BoardDTO;
import board.domain.SearchDTO;
import board.service.SearchService;
import lombok.AllArgsConstructor;
@AllArgsConstructor
public class SearchAction implements BoardAction {

	private String path;
	@Override
	public BoardActionForward execute(HttpServletRequest request) throws Exception {
		SearchDTO searchDto = new SearchDTO();
		searchDto.setCriteria(request.getParameter("criteria"));
		searchDto.setKeyword(request.getParameter("keyword"));
		SearchService service = new SearchService();
		List<BoardDTO> list = service.search(searchDto);
		request.setAttribute("list", list);
		request.setAttribute("searchDto", searchDto);
		return new BoardActionForward(path,false);
	}

}
