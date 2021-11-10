package member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {

	private String userid;
	private String password;
	private String changePassword;
	private String name;
	private String gender;
	private String email;
	public MemberDTO(String userid, String password) {
		super();
		this.userid = userid;
		this.password = password;
	}
	public MemberDTO(String userid, String password, String name, String gender, String email) {
		super();
		this.userid = userid;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.email = email;
	}

}
