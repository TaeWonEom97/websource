<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
/* String page1=request.getParameter("page");
String password = request.getParameter("password");
out.print("page1 : "+page1+"<br>");
out.print("password : "+password+"<br>"); */

/* response.sendRedirect("adminProcess.jsp");  */
pageContext.forward("adminProcess.jsp");
%>
<!-- <h2><a href="adminProcess.jsp">adminProcess</a></h2> -->
