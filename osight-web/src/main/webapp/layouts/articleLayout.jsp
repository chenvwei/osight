<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>博客-<tiles:getAsString name="title" /><s:if test="article!=null">-<s:property value="article.title" /></s:if>
</title>
<link href="/favicon.ico" type="image/x-icon" rel=icon>
<link type="text/css" rel="stylesheet" href="/css/default.css">
<link type="text/css" rel="stylesheet" href="/css/article.css">
<link type="text/css" rel="stylesheet" href="/css/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="/ueditor/third-party/SyntaxHighlighter/shCoreDefault.css" />
<!--[if IE]>
  <link rel="stylesheet" type="text/css" href="/css/jquery.ui.ie.css"/>
<![endif]-->
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/jquery-ui.js"></script>
<script type="text/javascript" src="/ueditor/editor_config.js"></script>
<script type="text/javascript" src="/ueditor/third-party/SyntaxHighlighter/shCore.js"></script>
<script type="text/javascript" src="/ueditor/editor_all.js"></script>
<script type="text/javascript" src="http://static.bshare.cn/b/buttonLite.js#style=-1&amp;uuid=29edc921-b221-4de4-886a-75383b7ae344&amp;pophcol=2&amp;lang=zh"></script>
<script type="text/javascript" src="http://static.bshare.cn/b/bshareC0.js"></script>
<script type="text/javascript">
	$(function() {
		SyntaxHighlighter.highlight();
	})
</script>
<jsp:include page="/common/ga.jsp"></jsp:include>
</head>
<body>
	<div class="body">
		<div class="container">
			<div class="top">
				<tiles:insertAttribute name="top"></tiles:insertAttribute>
			</div>
			<div class="menu">
				<tiles:insertAttribute name="menu"></tiles:insertAttribute>
			</div>
			<div class="content">
				<tiles:insertAttribute name="main"></tiles:insertAttribute>
			</div>
			<div class="bottom">
				<tiles:insertAttribute name="bottom"></tiles:insertAttribute>
			</div>
		</div>
	</div>
</body>
</html>