package member.controller;

import member.action.Action;
import member.action.DuplicationAction;
import member.action.JoinAction;
import member.action.LeaveAction;
import member.action.LoginAction;
import member.action.LogoutAction;
import member.action.ModifyAction;

public class MemberActionFactory {
	//singleton 객체 생성
	private static MemberActionFactory maf;
	private Action action;
	
	private MemberActionFactory() {}
	public static MemberActionFactory getInstance() {
		if(maf == null) {
			maf = new MemberActionFactory();
		}
		return maf;
	}
	public Action action(String cmd) {
		//action이 성공한 후 가야할 페이지
		if(cmd.equals("/login.do")) {
			action = new LoginAction("/member/view/loginForm.jsp");
		}else if(cmd.equals("/logout.do")) {
			action = new LogoutAction("/member/index.jsp");
		}else if(cmd.equals("/leave.do")) {
			action = new LeaveAction("/member/index.jsp");
		}else if(cmd.equals("/modify.do")) {
			action = new ModifyAction("/member/view/loginForm.jsp");
		}else if(cmd.equals("/join.do")) {
			action = new JoinAction("/member/view/joinForm.jsp");
		}else if(cmd.equals("/dupId.do")) {
			action = new DuplicationAction("/member/view/dupId.jsp");
		}
		return action;
	}
}
