<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<style>
<!--
#dialog_link,#modal_link {
	padding: .4em 1em .4em 20px;
	text-decoration: none;
	position: relative;
}

#dialog_link span.ui-icon, #modal_link span.ui-icon {
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
	<a href="#" id="modal_link" class="ui-state-default ui-corner-all"><span class="ui-icon ui-icon-newwin"></span>Open 新建相册</a>
</p>
<div id="dialog-message" title="Modal Dialog">
	<p>
		<span class="ui-icon ui-icon-circle-check" style="float: left; margin: 0 7px 50px 0;"></span> Your files have downloaded
		successfully into the My Downloads folder.
	</p>
	<p>
		Currently using <b>36% of your storage space</b>.
	</p>
</div>