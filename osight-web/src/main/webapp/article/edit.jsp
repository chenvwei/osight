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
<s:form action="save" namespace="/article" theme="simple">
	<table>
		<tr>
			<td>标题:</td>
			<td><s:textfield name="article.title" cssClass="title"></s:textfield></td>
		</tr>
		<tr>
			<td>类别:</td>
			<td><s:select list="categorys" listKey="id" listValue="name" name="article.category.id"></s:select> 
			</td>
		</tr>
		<tr>
			<td>是否隐藏:</td>
			<td>
				<s:radio list="#{false:'不隐藏',true:'隐藏' }" name="article.secret"></s:radio>
			</td>
		</tr>
		<tr>
			<td colspan="2"><s:textarea name="article.content" id="content"></s:textarea></td>
		</tr>
		<tr>
			<td><s:submit value="保存" cssClass="button ui-button-success"></s:submit></td>
		</tr>
	</table>
	<s:hidden name="article.id"></s:hidden>
</s:form>
</s:else>