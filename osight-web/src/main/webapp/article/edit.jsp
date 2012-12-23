<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="article==null">
没有该日志！
</s:if>
<s:else>
<script type="text/javascript">
<!--
	$(function() {
		UE.getEditor('content');
	})
//-->
</script>
<s:form action="/article/save.action" theme="simple">
	<table>
		<tr>
			<td>标题:</td>
			<td><s:textfield name="article.title"></s:textfield></td>
		</tr>
		<tr>
			<td colspan="2"><s:textarea name="article.content" id="content"></s:textarea></td>
		</tr>
		<tr>
			<td><s:submit value="保存"></s:submit></td>
		</tr>
	</table>
	<s:hidden name="article.id"></s:hidden>
</s:form>
</s:else>