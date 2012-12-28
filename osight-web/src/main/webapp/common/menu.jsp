<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="my_menu">
	<div style="float: left">
		<table cellpadding="0" cellspacing="0" border="0">
			<tbody>
				<tr>
					<td><table cellpadding="0" cellspacing="0" border="0"
							width="100%">
							<tbody>
								<tr>
									<td style="white-space: nowrap;"><a href="/">首页</a></td>
									<td style="white-space: nowrap;"><a href="/article/">博客</a></td>
									<td style="white-space: nowrap;"><a href="/album/upload.jsp">相册</a></td>
								</tr>
							</tbody>
						</table></td>
				</tr>
			</tbody>
		</table>
	</div>
	<div style="float:right;">
	<table cellpadding="0" cellspacing="0" border="0" width="100%">
		<tbody>
			<tr>
			<s:if test="#session.s_user==null">
				<td style="white-space: nowrap;">
					<a href="/user/login.jsp" class="button ui-button-success">登录</a>
				</td>
			</s:if>
			<s:else>
				<td style="white-space: nowrap;">
				欢迎您：<s:property value="#session.s_user.nickName"/> | </td>
				<td><a href="/user/logout.jsp" class="button ui-button-primary">退出</a></span>
				</td>
			</s:else>
			</tr>
			</tbody>
		</table>
	</div>
</div>