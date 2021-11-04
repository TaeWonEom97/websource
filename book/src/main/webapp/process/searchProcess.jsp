<%@page import="java.util.List"%>
<%@page import="book.persistence.BookDAO"%>
<%@page import="java.sql.Connection"%>
<%@page import="book.persistence.JdbcUtil"%>
<%@page import="book.domain.BookDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	//search.jsp 넘긴 값 가져오기
	String criteria=request.getParameter("criteria");
	String keyword=request.getParameter("keyword");
	//db작업
	Connection con = JdbcUtil.getConnection();
	BookDAO dao = new BookDAO(con);
	List<BookDTO> list = dao.search(criteria, keyword);
	JdbcUtil.close(con);
	//페이지 이동
	request.setAttribute("list", list);
	pageContext.forward("/searchindex.jsp");
%>