<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<style>
<!--
#dialog_link,#modal_link {
	padding: .4em 1em .4em 20px;
	text-decoration: none;
	position: relative;
}

#dialog_link span.ui-icon,#modal_link span.ui-icon {
	margin: 0 5px 0 0;
	position: absolute;
	left: .2em;
	top: 50%;
	margin-top: -8px;
}
-->
</style>
<script type="text/javascript">
<!--
	$(function() {
		$("#dialog-message").dialog({
			autoOpen : false,
			modal : true,
			buttons : {
				Ok : function() {
					$(this).dialog("close");
				}
			}
		});
		$('#modal_link').click(function() {
			$('#dialog-message').dialog('open');
			return false;
		});
	})
//-->
</script>
<p>
	<a href="#" id="modal_link" class="ui-state-default ui-corner-all"><span
		class="ui-icon ui-icon-newwin"></span>新建相册</a>
</p>
<table>
	<s:iterator value="albumList">
		<tr>
			<td><s:property value="name" /></td>
			<td><s:property value="description" /></td>
		</tr>
	</s:iterator>
</table>
<div id="dialog-message" title="Modal Dialog">
	<s:form namespace="/album" action="album">
		<table>
			<tr>
				<td>相册名称：</td>
				<td><s:text name="album.name"></s:text></td>
			</tr>
			<tr>
				<td>相册描述：</td>
				<td><s:text name="album.description"></s:text></td>
			</tr>
			<tr>
				<td>访问设置：</td>
				<td><s:select list="#{'0':'公开的','1':'私人的','2':'回答问题','3':'密码'}"></s:select></td>
			</tr>
			<tr>
				<td><s:submit name="method:add" value="提交"></s:submit></td>
			</tr>
		</table>
	</s:form>
</div>