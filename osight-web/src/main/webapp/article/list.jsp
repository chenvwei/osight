<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div>
	<s:a namespace="/article" action="article" method="add">撰写新日志</s:a>
</div>
<s:if test="articleList==null">
还没有撰写过日志！
</s:if>
<s:else>
	<div>
		<s:iterator value="articleList">
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
				</div>
				<div class="content">
					<s:property value="content" escapeHtml="false"></s:property>
				</div>
				<div class="under"></div>
			</div>
		</s:iterator>
	</div>
</s:else>