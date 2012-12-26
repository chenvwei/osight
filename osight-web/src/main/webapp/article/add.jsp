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
<s:form action="save" namespace="/article" theme="simple">
	<table>
		<tr>
			<td>标题:</td>
			<td><s:textfield placeholder="请输入博客标题" name="article.title" cssClass="title"></s:textfield></td>
		</tr>
		<tr>
			<td colspan="2"><s:textarea name="article.content" id="content"></s:textarea></td>
		</tr>
		<tr>
			<td><s:submit value="保存"></s:submit></td>
		</tr>
	</table>
</s:form>