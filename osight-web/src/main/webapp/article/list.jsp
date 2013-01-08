<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div>
	<s:a href="/article/new"  cssClass="button ui-button-success">撰写新日志</s:a>
</div>
<s:if test="page.list==null">
还没有撰写过日志！
</s:if>
<s:else>
	<div>
		<s:iterator value="page.list">
			<s:if test="!secret || #session.s_user!=null">
			<div class="item">
				<h2>
					<s:a href="/article/%{id}.html">
						<s:property value="title" escapeHtml="true" />
					</s:a>
				</h2>
				<div class="info">
					发表于 <s:date name="createdOn" format="yyyy年MM月dd日 HH:mm" /> | 
					作者  <s:property value="user.nickName" escapeHtml="true" /> | 
					评论 (<s:property value="pv" />) | 
					阅读 (<s:property value="pv" />) | 
					类别 (<s:a href="/article/category/%{category.id}"> <s:property value="category.name" escapeHtml="true"/></s:a>) | 
					
					<s:a href="/article/edit/%{id}">修改</s:a>
					<s:a href="/article/delete/%{id}" onclick="return confirm('确认要删除吗？')">删除</s:a>
				</div>
				<div class="content">
					<s:property value="content" escapeHtml="false"></s:property>
				</div>
				<div class="under"></div>
			</div>
			</s:if>
		</s:iterator>
		<jsp:include page="page.jsp"></jsp:include>
	</div>
</s:else>