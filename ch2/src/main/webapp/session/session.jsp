<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
session.setMaxInactiveInterval(10);
%>
<h3>세션 테스트</h3>
<h2>세션 객체에 담긴 값 : <%=session.getAttribute("name") %></h2>
<h2>세션 객체에 담긴 값 : <%=session.getAttribute("userid") %></h2>
<p>isNew : <%=session.isNew() %></p>
<p>생성시간 : <%=session.getCreationTime() %></p>
<p>최종접속시간 : <%=session.getLastAccessedTime() %></p>
<p>세션 ID : <%=session.getId() %></p>
<div>
	<a href="main.jsp">메인 페이지</a>
</div>
<div>
	 <a href="user.jsp">user 페이지</a>
</div>
<div>
	<a href="session.jsp">세션 페이지</a>
</div>
</body>
</html>