<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
div {
	margin-bottom: 10px;
}

label {
	display: inline-block;
	width: 150px;
}

input {
	padding: 5px;
}
</style>
</head>
<body>
	<form action="info.jsp" method="post">
		<div>
			<label for="userid">아이디</label> <input type="text" id="userid"
				name="userid" />
		</div>
		<div>
			<label for="password">비밀번호</label> <input type="text" id="password"
				name="password" />
		</div>
		<div>
			<label for="username">이름</label> <input type="text" id="username"
				name="username" />
		</div>
		<button type="submit">전송</button>
	</form>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script>
		$(function() {
			$("button").click(function(e) {
				e.preventDefault();

				let userid = $("#userid");
				let password = $("#password");
				let username = $("#username");

				if (userid.val() == "") {
					alert("아이디를 확인해주세요");
					userid.focus();
					return;
				} else if (password.val() == "") {
					alert("비밀번호를 확인해주세요");
					password.focus();
					return;
				} else if (username.val() == "") {
					alert("이름을 확인해주세요");
					username.focus();
					return;
				}
				$("form").submit();
			});
		});
	</script>
</body>
</html>