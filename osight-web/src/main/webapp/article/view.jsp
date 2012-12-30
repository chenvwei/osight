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
			发表于 <s:date name="article.createdOn" format="yyyy年MM月dd日 HH:mm" /> |
			作者  <s:property value="article.user.nickName" escapeHtml="true" /> | 
			评论 (<s:property value="article.pv" />) | 
			阅读 (<s:property value="article.pv" />) | 
			类别 (<s:a href="/article/category/%{article.category.id}"><s:property value="article.category.name" escapeHtml="true"/></s:a>) | 
			
			<s:a href="/article/edit/%{article.id}">修改</s:a>
			<s:a href="/article/delete/%{article.id}" onclick="return confirm('确认要删除吗？')">删除</s:a>	
		</div>
		<div class="content">
			<s:property value="article.content" escapeHtml="false"></s:property>
		</div>
		<div class="under"></div>
	</div>
	<s:action name="listComment" namespace="/article" flush="true" executeResult="true">
		<s:param name="articleId" value="%{article.id}"/>
	</s:action>
</s:else>