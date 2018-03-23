<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
 <%@ include file="/common/meta.jsp"%>
	<link rel="stylesheet" href="${ctx}/css/login.css">
	<title>后台登陆</title>
<script type="text/javascript">

     $(document).ready(function(e) {
		 tt.vf.req.add("name", "pwd");
		 if($("#mesg").val()!=''){
			 alert($("#mesg").val());
		 }
	});
    function login(){
		if (tt.validate()) {
			$("#loginForm").attr("action",basepath + "/login/logining.action");
			$("#loginForm").submit();
		}else{
			$("#"+tt.vf.invalidEs[0].id).focus();
		}
	}
	function goToFor(){
		window.location.href=basepath+"/jsp/foreground/index.jsp";
	}
</script>
</head>
<body>
<div id="login_top">
	<div id="welcome">
		欢迎后台管理系统
	</div>
	<div id="back">
		<a href="javascript:void(0)" onclick="goToFor()">返回首页</a>&nbsp;&nbsp; | &nbsp;&nbsp;
		<a href="#">帮助</a>
	</div>
</div>
<div id="login_center">
	<div id="login_area">
		<div id="login_form">
			<form   method="post" id="loginForm" name="loginForm">
				<input type="hidden" id="mesg" name="mesg" value="${mesg}">
				<div id="login_tip">
					用户登录
				</div>
				<div><input type="text" id="loginName" name="loginName" class="username" value="${name}"></div>
				<div><input type="password" id="pwd" name="pwd" class="pwd"></div>
				<div id="btn_area">
					<input type="button" name="tj" id="sub_btn" onclick="login();" value="登&nbsp;&nbsp;录">&nbsp;&nbsp;
					<input type="button" name="quxiao" id="sub_btn" value="取&nbsp;&nbsp;消">&nbsp;&nbsp;
				</div>
			</form>
		</div>
	</div>
</div>
<div id="login_bottom">
	版权所有
</div>
</body>

</html>
