package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lombok.AllArgsConstructor;
import member.domain.MemberDTO;
import member.service.JoinService;
@AllArgsConstructor
public class JoinAction implements Action {
	private String path;
	@Override
	public ActionForward execute(HttpServletRequest request) throws Exception {
		//joinForm에서 넘긴 값 가져오기
		MemberDTO insertDto = new MemberDTO();
		insertDto.setUserid(request.getParameter("userid"));
		insertDto.setPassword(request.getParameter("password"));
		insertDto.setName(request.getParameter("name"));
		insertDto.setGender(request.getParameter("gender"));
		insertDto.setEmail(request.getParameter("email"));
		

		
		//db작업 요청
		JoinService service = new JoinService();
		boolean insertFlag = service.register(insertDto);
		//결과에 따라 페이지 이동
		if(!insertFlag) {
			path="/member/view/joinForm.jsp";
		}
		return new ActionForward(path,true);
	}

}
