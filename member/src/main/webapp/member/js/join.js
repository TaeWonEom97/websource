/**
 * joinForm.jsp 회원가입 유효성 검증
 */
$(function(){
	$("#joinform").validate({
		rules:{
			userid:{
				required:true,
				validId:true
			},
			password:{
				required:true,
				validPwd:true
			},
			confirm_password:{
				required:true,
				validPwd:true,
				equalTo:"#password"

			},
			name:{
				required:true
			},
			gender:{
				required:true
			},
			email:{
				required:true,
				email:true
			}
		},
		messages:{
			userid:{
				required:"아이디는 필수 입력 요소입니다."
			},
			password:{
				required:"비밀번호는 필수 입력 요소입니다."
			},
			confirm_password:{
				required:"비밀번호는 필수 입력 요소입니다.",
				equalTo:"이전 비밀번호와 다릅니다."
			},
			name:{
				required:"이름은 필수 입력 요소입니다."
			},
			gender:{
				required:"성별은 필수 입력 요소입니다."
			},
			email:{
				required:"이메일은 필수 입력 요소입니다.",
				email:"이메일 주소를 확인해주세요"
			}
		},
		errorPlacement:function(error,element){
			$(element).closest("form").find("small[id='"+element.attr("id") +"']")
					  .append(error);
		}
	})
})

//사이트 규칙 추가
$.validator.addMethod("validId",function(value){
	let regId = /^(?=.*[A-Za-z])(?=.*[\d])[A-Za-z\d]{6,12}$/;
	return regId.test(value);
},"아이디는 대소문자,특수문자를 포함하여 6에서12자리입니다.")

$.validator.addMethod("validPwd",function(value){
	let regPwd = /^(?=.*[A-Za-z])(?=.*[\d])(?=.*[!$#@*])[A-Za-z\d!$#@*]{8,15}$/;
	return regPwd.test(value);
},"비밀번호는 대소문자,특수문자를 포함하여 8에서15자리입니다.")