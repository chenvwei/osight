<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div>
	<s:a namespace="/article" action="add" cssClass="button ui-button-success">撰写新日志</s:a>
</div>
<s:if test="page.list==null">
还没有撰写过日志！
</s:if>
<s:else>
	<div>
		<s:iterator value="page.list">
			<div class="item">
				<h2>
					<s:a href="/article/%{id}.html">
						<s:property value="title" escapeHtml="true" />
					</s:a>
				</h2>
				<div class="info">
					<span><s:date name="createdOn" format="yyyy年MM月dd日" /></span> <span
						class="author"><s:property value="user.nickName"
							escapeHtml="true" /></span> <span class="comments"><s:property
							value="pv" /></span> <span class="comments"><s:property
							value="pv" /></span>
							<span class="comments">
					<s:a href="/article/edit/%{id}">修改</s:a>
					<s:a href="/article/delete/%{id}">删除</s:a></span>
				</div>
				<div class="content">
					<s:property value="content" escapeHtml="false"></s:property>
				</div>
				<div class="under"></div>
			</div>
		</s:iterator>
		<jsp:include page="/common/page.jsp"></jsp:include>
	</div>
</s:else>