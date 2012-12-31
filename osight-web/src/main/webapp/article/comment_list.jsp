<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div id="comments">
<s:if test="page!=null&&page.list!=null">
	<div id="commentlist">
		<ol id="thecomments">
			<s:iterator value="page.list">
				<li class="comment">
					<div class="author">
						<div class="pic">
							<img alt="" src="" height="32" width="32">
						</div>
						<div class="name">
							<span><s:property value="userName" escapeHtml="true"/> </span>
						</div>
					</div>
					<div class="info">
						<div class="date"><s:date name="createdOn" format="yyyy年MM月dd日 HH:mm"/></div>
						<div class="act"><a>回复</a> | <a>引用</a></div>
						<div class="fixed"></div>
						<div class="detail">
						<p><s:property value="content" escapeHtml="true"/></p> </div>
					</div>
				</li>
			</s:iterator>
		</ol>
		<div class="fixed"></div>
	</div>
</s:if>
</div>
<s:form action="newComment" namespace="/article" method="post">
<s:hidden name="comment.articleId" value="%{articleId}"></s:hidden>
<table>
	<tr>
		<td><s:textfield name="comment.userName" cssClass="textfield" placeholder="请输入昵称"></s:textfield> 昵称</td>
	</tr>	
	<tr>
		<td><s:textfield name="comment.email" cssClass="textfield" placeholder="请输入电子邮箱"></s:textfield> 电子邮箱</td>
	</tr>
	<tr>
		<td><s:textarea name="comment.content" rows="5" cols="150"  placeholder="请输入评论"></s:textarea></td>
	</tr>
	<tr>
		<td><s:submit value="提交评论" cssClass="button"></s:submit> </td>
	</tr>
</table>
</s:form>