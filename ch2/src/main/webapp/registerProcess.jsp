<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	System.out.print(request.getParameter("userid"));

	//db작업 결과 화면에 기록
	//사용 가능 true 사용 불가 false
	out.print("true");
%>