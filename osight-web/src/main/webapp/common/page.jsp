<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="com.opensymphony.xwork2.util.ValueStack"%>
<%@page import="com.osight.framework.page.Page"%>
<%@page import="com.osight.core.util.WebAppUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
    String[] excludeParam = {"start"};
    String strUrl = WebAppUtil.getRequestUrl(request, excludeParam);
    if(StringUtils.contains(strUrl, "&")){
        strUrl=strUrl.concat("&");
    }else{
        strUrl=strUrl.concat("?");
    }
    ValueStack vs = (ValueStack)request.getAttribute("struts.valueStack");
    Page<?> pageRs = (Page<?>)vs.findValue("page");
%>
<s:if test="page.previousPageAvailable">
	<%
	    StringBuffer firstURL = new StringBuffer();
        firstURL.append(strUrl);
        firstURL.append("start=0");
        StringBuffer previousURL = new StringBuffer();
        previousURL.append(strUrl);
        previousURL.append("start=" + pageRs.getStartOfPreviousPage());
	%>
	<a href='<%=firstURL%>' style='color: #06C;'>首页</a>
	<a href='<%=previousURL%>' style='color: #06C;'>&lt;&lt;前一页</a>
</s:if>
第<span style="color: #F00;"> <s:property value="page.curPage" /></span>页&nbsp;&nbsp;
<s:property value="page.size" />条记录&nbsp;&nbsp;
共<span style="color: #F00;"><s:property value="page.totalPage"/></span>页&nbsp;&nbsp;
共<s:property value="page.totalCount"/>条记录
<s:if test="page.nextPageAvailable">
	<%
	    StringBuffer nextURL = new StringBuffer();
        nextURL.append(strUrl);
        nextURL.append("start=" + pageRs.getStartOfNextPage());
        StringBuffer lastURL = new StringBuffer();
        lastURL.append(strUrl);
        lastURL.append("start=" + pageRs.getStartOfLastPage());
	%>
	<a href='<%=nextURL%>' style='color: #06C;'>下一页 &gt;&gt;</a>
	<a href='<%=lastURL%>' style='color: #06C;'>最后一页</a>
</s:if>
