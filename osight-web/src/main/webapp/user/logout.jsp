<%@page import="com.osight.core.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	session.setAttribute(Constants.SESSION_USER, null);
	session.invalidate();
	String referer = request.getHeader("Referer");
	response.sendRedirect(referer);
%>