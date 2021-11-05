package member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import lombok.AllArgsConstructor;
import member.service.LeaveService;

@AllArgsConstructor
public class LeaveAction implements Action {

	private String path;
	@Override
	public ActionForward execute(HttpServletRequest request) throws Exception {
		//사용자가 입력값 가져오기
		String userid=request.getParameter("userid");
		String current_password=request.getParameter("current_password");
		//db작업 서비스에게 부탁
		LeaveService service = new LeaveService();
		boolean deleteFlag = service.leave(userid,current_password);
		
		//결과에 따라서 페이지 이동
		if(deleteFlag) {
			//세션해제
			HttpSession session = request.getSession();
			session.removeAttribute("loginDto");
		}else {
			path="/member/view/leaveForm.jsp";
		}
		return new ActionForward(path,true);
	}

}
