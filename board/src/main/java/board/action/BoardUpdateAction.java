package board.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import board.domain.BoardDTO;
import board.service.BoardUpdateService;
import board.util.UploadUtil;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BoardUpdateAction implements BoardAction {
	private String path;

	@Override
	public BoardActionForward execute(HttpServletRequest request) throws Exception {
		// request를 UploadUtil에 넘기기
		UploadUtil uploadUtil = new UploadUtil();
		Map<String, String> map = uploadUtil.requestParse(request);

		// map에서 값 가져오기

		BoardDTO boardDto = new BoardDTO();
		boardDto.setBno(Integer.parseInt(map.get("bno")));
		boardDto.setName(map.get("name"));
		boardDto.setTitle(map.get("title"));
		boardDto.setContent(map.get("content"));
		boardDto.setPassword(map.get("password")); 
		// 파일첨부
		boardDto.setAttach(map.get("attach"));

		BoardUpdateService service = new BoardUpdateService();
		boolean updateFlag = service.update(boardDto);
		if (updateFlag) {
			path += "?bno="+boardDto.getBno();
		}else {
			path="/modify.do?bno="+boardDto.getBno();
		}
		return new BoardActionForward(path, true);
	}

}
