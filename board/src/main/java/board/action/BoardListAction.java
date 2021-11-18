package board.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import board.domain.BoardDTO;
import board.domain.PageDTO;
import board.domain.SearchDTO;
import board.service.BoardListService;
import board.service.BoardTotalRowsService;
import lombok.AllArgsConstructor;
@AllArgsConstructor
public class BoardListAction implements BoardAction {
	private String path;
	@Override
	public BoardActionForward execute(HttpServletRequest request) throws Exception {
		//페이지 나누기 개념 적용
		int page = 1;
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		int amount = 10;
		
		//검색기준과 검색어
		SearchDTO searchDto = new SearchDTO();
		searchDto.setCriteria(request.getParameter("criteria"));
		searchDto.setKeyword(request.getParameter("keyword"));
		
		BoardTotalRowsService rowsService = new BoardTotalRowsService();
		int total = rowsService.totalRows(searchDto);
		
		PageDTO pageDto = new PageDTO(total,page,amount,searchDto);
		request.setAttribute("searchDto", searchDto);
		request.setAttribute("pageDto", pageDto);
		// ----------------- 페이지 나누기
			
//		PageDTO pageDto = new PageDTO(page, amount);
		
		BoardListService service = new BoardListService();
		List<BoardDTO> boardList=service.all(pageDto);
		
		request.setAttribute("list", boardList);
		return new BoardActionForward(path,false);
	}

}
