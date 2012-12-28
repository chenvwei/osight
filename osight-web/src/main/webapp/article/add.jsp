<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
<!--
	$(function() {
		UE.getEditor('content');
		$("#newCategory").click(function(){
			if($("#categoryname").val()==""){
				alert("分类名称不能为空");
				$("#categoryname").focus();
				return false;
			}
			$.ajax({
				url:"/article/newCategory.action",
				type:"POST",
				data:{name:$("#categoryname").val()},
				dataType:"json",
				success:function(data){
					alert(data.id+":"+data.name);
				}
			})
		});
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
			<td>类别:</td>
			<td><s:select list="categorys" listKey="id" listValue="name" name="article.category.id"></s:select> 
				<input type="text" name="name" id="categoryname"> <input type="button" value="新增" class="button" id="newCategory">
			</td>
		</tr>
		<tr>
			<td colspan="2"><s:textarea name="article.content" id="content"></s:textarea></td>
		</tr>
		<tr>
			<td><s:submit value="保存" cssClass="button ui-button-success"></s:submit></td>
		</tr>
	</table>
</s:form>