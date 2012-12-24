<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
<link rel="stylesheet" type="text/css" href="/css/login.css" />
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
	<form class="form-2">
		<h1><span class="log-in">登录</span> 或 <span class="sign-up">注册</span></h1>
		<p class="float">
			<label for="login"><i class="icon-user"></i>用户名</label>
			<input type="text" name="login" placeholder="用户名或电子邮箱">
		</p>
		<p class="float">
			<label for="password"><i class="icon-lock"></i>密码</label>
			<input type="password" name="password" placeholder="密码" class="showpassword">
		</p>
		<p class="clearfix"> 
			<a href="#" class="log-twitter">微博账号登录</a>    
			<input type="submit" name="submit" value="登录">
		</p>
	</form>
</section>
</div>
</body>
</html>