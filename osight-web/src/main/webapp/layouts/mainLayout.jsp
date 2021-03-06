<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta property="wb:webmaster" content="e4fb9c42ef408546" />
<link href="/favicon.ico" type="image/x-icon" rel=icon> 
<link rel="stylesheet" href="/css/default.css">
<link type="text/css" rel="stylesheet" href="/css/default.css">
<link type="text/css" rel="stylesheet" href="/css/jquery-ui.css" />
<title>云游四方工作室</title>
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript" src="/js/jquery-ui.js"></script>
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