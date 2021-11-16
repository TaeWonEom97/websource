package board.action;

//사용자의 요청에 따라 액션을 생성해주는 클래스
public class BoardActionfactory {
	private static BoardActionfactory baf;
	private BoardAction action;
	
	private BoardActionfactory() {}
	public static BoardActionfactory getInstance() {
		if(baf==null) {
			baf = new BoardActionfactory();
		}
		return baf;
	}
	
	public BoardAction action(String cmd) {
		if(cmd.equals("/insert.do")) {
			action = new BoardInsertAction("/list.do");
		}else if(cmd.equals("/list.do")){
			action = new BoardListAction("/view/qna_board_list.jsp");
		}else if(cmd.equals("/read.do")) {
			action = new BoardReadAction("/view/qna_board_view.jsp");
		}else if(cmd.equals("/countUpdate.do")) {
			action = new BoardCountUpdateAction("/read.do");
		}
		return action;
	}

}
