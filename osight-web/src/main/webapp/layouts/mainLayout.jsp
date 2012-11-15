<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>云游四方工作室</title>
</head>
<body>
	<div class="body">
		<div class="top">
			<tiles:insertAttribute name="top"></tiles:insertAttribute>
		</div>
		<div class="main">
			<tiles:insertAttribute name="main"></tiles:insertAttribute>
		</div>
		<div class="bottom">
			<tiles:insertAttribute name="bottom"></tiles:insertAttribute>
		</div>
	</div>
</body>
</html>