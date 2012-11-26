<%@page import="com.osight.framework.util.DigestUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传照片</title>
<script src="/js/jquery.uploadify.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="/css/uploadify.css">
<%
    long time = System.currentTimeMillis();
			String token = DigestUtil.MD5(String.valueOf(time));
%>
<script type="text/javascript">
		$(function() {
			$('#uploadify').uploadify(
			{
				'auto': false,  
				'queueID': 'fileQueue',
				'buttonText': '上传',
				'swf':'/common/uploadify.swf',
				'uploader' : '/servlet/upload.servlet'
		});
	});
</script>
</head>
<body>
	<h1>Uploadify Demo</h1>
	<div id="fileQueue"></div>
	<input type="file" name="uploadify" id="uploadify" />
	<p>
		<a href="javascript:$('#uploadify').uploadify('upload','*')">开始上传</a> <a
			href="javascript:$('#uploadify').uplaodify('cancel','*')"
		>取消上传</a>
	</p>
</body>
</html>