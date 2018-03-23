<%@page import="java.util.Date"%>
<%@ page import="com.util.DateUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
	String dates = DateUtil.formatDate(new Date(), "yyyy-MM-dd");
%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>西部产权交易所管理平台系统</title>
	<%@ include file="/common/meta.jsp"%>

	<script type="text/javascript">
		var baseIP='${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}';
		$(function(){

		});

		function loginout(){
			if(confirm('确定要退出当前系统吗？')){
				$('#site_login').attr('src',baseIP+'/login/logout.action');
				$('#site_login')[0].onload=$('#site_login')[0].onreadystatechange=function(){
					top.location.href='${ctx}/login/logout.action';
				}
			}
		}
       function upPwdS() {
           window.location.target="rightFrame";
           window.location.href=basepath + '/jsp/system/user_up_pwd.jsp';

       }
	</script>
	<style type="text/css">
		.color{color: #3db9f6; text-decoration:none;font-weight:bold;}
	</style>

</head>

<body  >

<div class="topleft">
	<a href="main.html" target="_parent"></a>
</div>


<div class="top">
	<div id="top_t">
		<div id="logo" class="fl"></div>
		<div id="photo_info" class="fr">
			<div id="photo" class="fl">
				<a href="#"><img src="${ctx}/images/a.png" alt="" width="60" height="60"></a>
			</div>
			<div id="base_info" class="fr">
				<div class="help_info">
					<a href="1" id="hp">&nbsp;</a>
					<a href="2" id="gy">&nbsp;</a>
					<a href="javacript:void(0)" id="out"  onclick="loginout();">&nbsp;</a>
				</div>
				<div class="info_center">
					${sessionScope.sessionUser.loginName}
					&nbsp;&nbsp;&nbsp;
						<a href="${ctx}/jsp/system/user_up_pwd.jsp" target="rightFrame" class="color">修改密码</a>
				</div>
			</div>
		</div>
	</div>

</div>
<iframe id="site_login" style="display:none;"></iframe>
</body>
</html>
