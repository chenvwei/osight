<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
<!--
	$(function() {
		UE.getEditor('content');
	})
//-->
</script>
<s:form action="/article/article.action" theme="simple">
	<table>
		<tr>
			<td>昵称：</td>
			<td><s:textfield name="article.user.nickName"></s:textfield></td>
		</tr>
		<tr>
			<td>电子邮件:</td>
			<td><s:textfield name="article.user.email"></s:textfield>
		</tr>
		<tr>
			<td>标题:</td>
			<td><s:textfield name="article.title"></s:textfield></td>
		</tr>
		<tr>
			<td colspan="2"><s:textarea name="article.content" id="content"></s:textarea></td>
		</tr>
		<tr>
			<td><s:submit name="method:save" value="保存"></s:submit></td>
		</tr>
	</table>
	<s:hidden name="article.id"></s:hidden>
</s:form>