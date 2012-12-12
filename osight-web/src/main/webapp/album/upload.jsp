<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<tiles:insertDefinition name="album" flush="true">
	<tiles:putAttribute name="main" value="/album/upload_content.jsp" />
</tiles:insertDefinition>