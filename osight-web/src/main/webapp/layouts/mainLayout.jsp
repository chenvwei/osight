<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/css/default.css">
<title>云游四方工作室</title>
<script type="text/javascript">
	var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://"
			: " http://");
	document
			.write(unescape("%3Cscript src='"
					+ _bdhmProtocol
					+ "hm.baidu.com/h.js%3F21ed1a1b695b252d6e6476e98dfaf152' type='text/javascript'%3E%3C/script%3E"));
	var _gaq = _gaq || [];
	_gaq.push([ '_setAccount', 'UA-11982798-4' ]);
	_gaq.push([ '_trackPageview' ]);
	(function() {
		var ga = document.createElement('script');
		ga.type = 'text/javascript';
		ga.async = true;
		ga.src = ('https:' == document.location.protocol ? 'https://ssl'
				: 'http://www')
				+ '.google-analytics.com/ga.js';
		var s = document.getElementsByTagName('script')[0];
		s.parentNode.insertBefore(ga, s);
	})();
</script>
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