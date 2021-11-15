<%@page import="member.domain.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp" %>
<%
	/* MemberDTO loginDto = (MemberDTO)session.getAttribute("loginDto"); */
%>
<form id="modifyform" action="/modify.do" method="post">
	<div class="card"  style="width: 40rem;margin:40px auto;">	
		<div class="card-header">
	    	<h4>비밀번호 변경</h4>
	  	</div>
	 	<div class="card-body">	
			<div class="form-group row justify-content-center">		
				<div class="col-sm-10">	
					<input type="password" name="current_password" id="current_password" class="form-control" placeholder="현재 비밀번호" autofocus="autofocus"/>
					<small id="current_password" class="text-info"></small>	
				</div>
			</div>	
			<div class="form-group row justify-content-center">		
				<div class="col-sm-10">	
					<input type="password" name="new_password" id = "new_password" class="form-control" placeholder="새 비밀번호"/>
					<small id="new_password" class="text-info"></small>
				</div>	
			</div>	
			<div class="form-group row justify-content-center">		
				<div class="col-sm-10">	
					<input type="password" name="confirm_password" id = "confirm_password" class="form-control" placeholder="새 비밀번호 확인"/>
					<small id="confirm_password" class="text-info"></small>
				</div>	
			</div>	
			<div class="form-group text-center">		
				<button type="submit" class="btn btn-primary">수정</button>
			    <button type="reset" class="btn btn-secondary" id="modifycancel">취소</button>		
			</div>
		</div>
	</div>		
</form>
<%--로그인 후 메뉴 스크립트--%>
<script>
      <%-- let name = '<%=loginDto.getName()%>'; --%>
      let name ='${loginDto.name}';
   </script>
	<script src="../js/menu.js"></script>
	
<script>
$(function(){
	$("#modify").detach();
})
</script>
<script>
$(function(){
	//new password와 confirm password값을 가져온 후 둘의 값이 같은지 확인
	//같으면 form submit 다르면 사용자에게 경고창을 띄우고 new passowrd란에 focus주기
	$(":submit").click(function(e){
		e.preventDefault();
		let new_password=$("#new_password");
		let confirm_password=$("#confirm_password");
		let current_password=$("#current_password");
		
		if(current_password.val() ==""){
			alert("현재 비밀번호를 확인해주세요");
			current_password.focus();
			return;
		}else if(new_password.val() ==""){
			alert("새 비밀번호를 확인해주세요");
			new_password.focus();
			return;
		}else if(confirm_password.val() ==""){
			alert("새 확인비밀번호를 확인해주세요");
			confirm_password.focus();
			return;
		}
		if(new_password.val() !== confirm_password.val()){
			alert("변경을 위한 비밀번호가 일치하지 않습니다.");
			new_password.val("");
			confirm_password.val("");
			new_password.focus();
			return;
		}
		$("form").submit();
	})
})
</script>
<%--버튼 클릭 이벤트 스크립트--%>
<script src="../js/command.js"></script>
<%@ include file="../layout/footer.jsp" %>