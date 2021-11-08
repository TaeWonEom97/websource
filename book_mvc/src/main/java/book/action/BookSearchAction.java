package book.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import book.domain.BookDTO;
import book.service.BookSearchService;
import lombok.AllArgsConstructor;
@AllArgsConstructor
public class BookSearchAction implements BookAction {
	private String path;
	@Override
	public BookActionForward execute(HttpServletRequest request) throws Exception {
		// 값 받아오기
		String criteria = request.getParameter("criteria");
		String keyword = request.getParameter("keyword");
		BookSearchService service = new BookSearchService();
		List<BookDTO> searchList = service.search(criteria, keyword);
		request.setAttribute("list", searchList);
		
		//페이지 넘기기
		return new BookActionForward(path,false);
	}

}
