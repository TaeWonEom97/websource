package book.action;

import javax.servlet.http.HttpServletRequest;

import book.service.BookDeleteSerivce;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BookDeleteAction implements BookAction {
	private String path;
	@Override
	public BookActionForward execute(HttpServletRequest request) throws Exception {
		String code = request.getParameter("code");
		BookDeleteSerivce service = new BookDeleteSerivce();
		boolean deleteFlag=service.bookDelete(code);
		if(!deleteFlag) {
			path="/index.jsp?tab=delete";
		}
		return new BookActionForward(path,true);
	}

}
