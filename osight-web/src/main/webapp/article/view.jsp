<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test="article==null">
没有找到该日志!
</s:if>
<s:else>
	<div class="item">
		<h2>
			<s:property value="article.title" escapeHtml="true" />
		</h2>
		<div class="info">
			<span><s:date name="article.createdOn" format="yyyy年MM月dd日" /></span>
			<span class="author"><s:property value="article.user.nickName"
					escapeHtml="true" /></span> <span class="comments"><s:property
					value="article.pv" /></span> <span class="comments"><s:property
					value="article.pv" /></span>
					
					<span class="comments">
					<s:a href="/article/edit.action?id=%{id}">修改
					</s:a></span>
		</div>
		<div class="content">
			<s:property value="article.content" escapeHtml="false"></s:property>
		</div>
		<div class="under"></div>
	</div>
</s:else>