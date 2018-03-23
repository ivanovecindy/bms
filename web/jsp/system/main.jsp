<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
	<c:set var="ctx" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}"/>
	<frameset rows="130,*" cols="*" frameborder="no" border="0" framespacing="0">
		<frame src="${ctx}/jsp/system/top.jsp" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" title="topFrame" />
		<frameset cols="187,*" frameborder="no" border="0" framespacing="0">
			<frame src="${ctx}/jsp/system/left.jsp" name="leftFrame" scrolling="Yes"   noresize="noresize" id="leftFrame" title="leftFrame" />

			<frame src="${ctx}/jsp/system/home.jsp" name="rightFrame" id="rightFrame" title="rightFrame"  />
		</frameset>
	</frameset>
	<noframes><body>
	</body></noframes>
</html>