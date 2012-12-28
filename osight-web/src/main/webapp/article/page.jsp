<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="com.opensymphony.xwork2.util.ValueStack"%>
<%@page import="com.osight.framework.page.Page"%>
<%@page import="com.osight.core.util.WebAppUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
    ValueStack vs = (ValueStack)request.getAttribute("struts.valueStack");
    Page<?> pageRs = (Page<?>)vs.findValue("page");
%>
<div class="page">
第<span style="color: #F00;"> <s:property value="page.curPage" /></span>页&nbsp;&nbsp;
<s:property value="page.size" />条记录&nbsp;&nbsp;
共<span style="color: #F00;"><s:property value="page.totalPage"/></span>页&nbsp;&nbsp;
共<s:property value="page.totalCount"/>条记录
<%
	String url = "/article/page/";
	for(int i=1;i<=pageRs.getTotalPage();i++){
	    if(i==pageRs.getCurPage()){
	        %>
	        <span><%=i %></span>
	        <%
	    }else{
	        String nextURL= url+i;
	        %>
		    <a href='<%=nextURL%>' style='color: #06C;'><%=i %> </a>
		    <%
	    }
	}
%>
</div>