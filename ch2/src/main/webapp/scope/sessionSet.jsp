<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	session.setAttribute("name", "session Test");
	response.sendRedirect("sessionTest1.jsp");
%>