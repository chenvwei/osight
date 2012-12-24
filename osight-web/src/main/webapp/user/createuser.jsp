<%@page import="org.apache.commons.lang.builder.ToStringStyle"%>
<%@page import="org.apache.commons.lang.builder.ToStringBuilder"%>
<%@page import="com.osight.core.pojos.UserData"%>
<%@page import="com.osight.core.service.UserServiceFactory"%>
<%@page import="com.osight.core.service.UserService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    UserService userService = UserServiceFactory.getUserService();
    UserData user = userService.createUser("云游四方", "rodneytt@sina.com", "rodneytt", "123456");
    out.println(ToStringBuilder.reflectionToString(user, ToStringStyle.NO_FIELD_NAMES_STYLE));
%>