<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>请求出错</title>
<link type="text/css" rel="stylesheet" href="/css/jquery-ui.css" />
</head>
<body>
<div style="text-align:center;width: 800px">
	<div class="ui-widget">
		<div class="ui-state-error ui-corner-all">
			<p>
				<span class="ui-icon ui-icon-alert"
					style="float: left; margin-right: .3em;"></span>对不起，您的请求出错！<a href="/">返回首页</a>
					<p>
					<%=exception %><br/>
					<%
						exception.printStackTrace(response.getWriter());
					%>
					</p>
			</p>
		</div>
	</div>
</div>
</body>
</html>