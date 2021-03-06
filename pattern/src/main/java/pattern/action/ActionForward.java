package pattern.action;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ActionForward {
	//이동 경로와 이동방식 지정
	private String path;
	private boolean redirect; //true:sendRedirect , false:forward
}
