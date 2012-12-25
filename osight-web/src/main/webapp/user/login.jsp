<%@page import="com.osight.core.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	if(session.getAttribute(Constants.SESSION_USER)!=null){
		response.sendRedirect("/");
	}
	String referer = request.getHeader("Referer");
	if(referer==null){
		referer="/";
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
<link rel="stylesheet" type="text/css" href="/css/login.css" />
<link type="text/css" rel="stylesheet" href="/css/jquery-ui.css" />
<title>登录</title>
<script type="text/javascript" src="/js/jquery.js"></script>
<script type="text/javascript">
	$(function(){
	    $(".showpassword").each(function(index,input) {
	        var $input = $(input);
	        $("<p class='opt'/>").append(
	            $("<input type='checkbox' class='showpasswordcheckbox' id='showPassword' />").click(function() {
	                var change = $(this).is(":checked") ? "text" : "password";
	                var rep = $("<input placeholder='密码' type='" + change + "' />")
	                    .attr("id", $input.attr("id"))
	                    .attr("name", $input.attr("name"))
	                    .attr('class', $input.attr('class'))
	                    .val($input.val())
	                    .insertBefore($input);
	                $input.remove();
	                $input = rep;
	             })
	        ).append($("<label for='showPassword'/>").text("显示密码")).insertAfter($input.parent());
	    });

	    $('#showPassword').click(function(){
			if($("#showPassword").is(":checked")) {
				$('.icon-lock').addClass('icon-unlock');
				$('.icon-unlock').removeClass('icon-lock');    
			} else {
				$('.icon-unlock').addClass('icon-lock');
				$('.icon-lock').removeClass('icon-unlock');
			}
	    });
	});
</script>
</head>
<body>
<div class="container">
<section class="main">
<s:if test="errorMessages!=null">
<div  style="width:340px;margin: 20px auto -20px">
<s:iterator value="errorMessages" var="msg">
<div class="ui-widget">
		<div class="ui-state-error ui-corner-all">
			<p>
				<span class="ui-icon ui-icon-alert"
					style="float: left; margin-right: .3em;"></span>
				<s:property value="msg"/>
			</p>
		</div>
</div>	
</s:iterator>
</div>
</s:if>
	<form class="form-2" action="/user/login.action" method="post">
		<h1><span class="log-in">登录</span> 或 <span class="sign-up">注册</span></h1>
		<p class="float">
			<label for="login"><i class="icon-user"></i>用户名</label>
			<input type="text" name="loginName" placeholder="用户名或电子邮箱">
		</p>
		<p class="float">
			<label for="password"><i class="icon-lock"></i>密码</label>
			<input type="password" name="password" placeholder="密码" class="showpassword">
		</p>
		<p class="clearfix"> 
			<a href="#" class="log-twitter">微博账号登录</a>    
			<input type="submit" value="登录">
		</p>
		<input type="hidden" name="from" value="<%=referer%>">
	</form>
</section>
</div>
</body>
</html>